package rebue.suc.svc.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rebue.robotech.svc.impl.MybatisBaseSvcImpl;
import rebue.sbs.redis.RedisClient;
import rebue.sbs.redis.RedisSetException;
import rebue.suc.dic.BindWxResultDic;
import rebue.suc.dic.LoginResultDic;
import rebue.suc.dic.RegAndLoginTypeDic;
import rebue.suc.dic.RegResultDic;
import rebue.suc.dic.SucOpTypeDic;
import rebue.suc.dic.VerifyPayPswdResultDic;
import rebue.suc.mapper.SucUserMapper;
import rebue.suc.mo.SucLockLogMo;
import rebue.suc.mo.SucLoginLogMo;
import rebue.suc.mo.SucOpLogMo;
import rebue.suc.mo.SucRegMo;
import rebue.suc.mo.SucUserMo;
import rebue.suc.msg.SucUserAddMsg;
import rebue.suc.pub.SucUserAddPub;
import rebue.suc.ro.BindWxRo;
import rebue.suc.ro.PayPswdVerifyRo;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.ro.UserRegRo;
import rebue.suc.svc.SucLockLogSvc;
import rebue.suc.svc.SucLoginLogSvc;
import rebue.suc.svc.SucOpLogSvc;
import rebue.suc.svc.SucRegSvc;
import rebue.suc.svc.SucUserSvc;
import rebue.suc.to.BindWxTo;
import rebue.suc.to.LoginByLoginNameTo;
import rebue.suc.to.LoginByQqTo;
import rebue.suc.to.LoginByUserNameTo;
import rebue.suc.to.LoginByWxTo;
import rebue.suc.to.RegAndLoginBaseTo;
import rebue.suc.to.RegBaseTo;
import rebue.suc.to.RegByLoginNameTo;
import rebue.suc.to.RegByQqTo;
import rebue.suc.to.RegByWxTo;
import rebue.wheel.DateUtils;
import rebue.wheel.IdCardValidator;
import rebue.wheel.RandomEx;
import rebue.wheel.RegexUtils;
import rebue.wheel.turing.DigestUtils;

@Service
/**
 * <pre>
 * 在单独使用不带任何参数 的 @Transactional 注释时，
 * propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED，
 * 而且事务不会针对受控异常（checked exception）回滚。
 * 注意：
 * 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * </pre>
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SucUserSvcImpl extends MybatisBaseSvcImpl<SucUserMo, java.lang.Long, SucUserMapper> implements SucUserSvc {

    /**
     */
    private final static Logger _log                                = LoggerFactory.getLogger(SucUserSvcImpl.class);
    /**
     * 缓存当天连续输入登录密码错误的次数的Key的前缀 后面跟用户的用户id拼接成Key Value为失败次数
     */
    private static final String REDIS_KEY_LOGINPSWD_ERRCOUNT_PREFIX = "rebue.suc.svc.user.loginpswd.errcount.";
    /**
     * 缓存当天连续输入支付密码错误的次数的Key的前缀 后面跟用户的用户id拼接成Key Value为失败次数
     */
    private static final String REDIS_KEY_PAYPSWD_ERRCOUNT_PREFIX   = "rebue.suc.svc.user.paypswd.errcount.";
    /**
     * 用户账号的黑名单的前缀 后面跟用户的用户id拼接成Key Value为空值
     */
    private static final String REDIS_KEY_USER_BLACKLIST_PREFIX     = "rebue.suc.svc.user.blacklist.";

    @Resource
    private SucLoginLogSvc      loginLogSvc;
    @Resource
    private SucLockLogSvc       lockLogSvc;
    @Resource
    private SucRegSvc           regSvc;
    @Resource
    private SucOpLogSvc         opLogSvc;

    @Resource
    private RedisClient         redisClient;
    @Resource
    private Mapper              dozerMapper;

    @Resource
    private SucUserAddPub       userAddPub;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(SucUserMo mo) {
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        if (mo.getModifiedTimestamp() == null) {
            mo.setModifiedTimestamp(System.currentTimeMillis());
        }
        int result = super.add(mo);

        // 发布添加用户的消息
        SucUserAddMsg msg = dozerMapper.map(mo, SucUserAddMsg.class);
        userAddPub.send(msg);

        return result;
    }

    /**
     * 用户注册(通过登录名称)
     * TODO SUC : 用户注册(通过Email/Mobile)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserRegRo regByLoginName(RegByLoginNameTo to) {
        if (StringUtils.isAnyBlank(to.getLoginName(), to.getLoginPswd(), to.getUserAgent(), to.getMac(), to.getIp())
                || to.getAppId() == null) {
            _log.warn("没有填写用户登录名称/登录密码/应用ID/浏览器类型/MAC/IP: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        if (to.getLoginName().length() < 3 || to.getLoginName().length() > 20
                || RegexUtils.matchEmail(to.getLoginName()) || RegexUtils.matchMobile(to.getLoginName())) {
            _log.warn("登录名称格式不正确: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        if (to.getLoginPswd().length() != 32) {
            _log.warn("登录密码经过MD5后应该为32个字符: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        if (!StringUtils.isBlank(to.getEmail()) && !RegexUtils.matchEmail(to.getEmail())) {
            _log.warn("电子邮箱格式不正确: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        if (!StringUtils.isBlank(to.getMobile()) && !RegexUtils.matchMobile(to.getMobile())) {
            _log.warn("手机号码格式不正确: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        if (!StringUtils.isBlank(to.getIdcard()) && !IdCardValidator.validate(to.getIdcard())) {
            _log.warn("身份证号码格式不正确: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        SucUserMo condition = new SucUserMo();
        condition.setLoginName(to.getLoginName());
        if (_mapper.existSelective(condition)) {
            _log.warn("用户登录名称已存在: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.LOGIN_NAME_EXIST);
            return regRo;
        }
        if (!StringUtils.isBlank(to.getEmail())) {
            condition = new SucUserMo();
            condition.setEmail(to.getEmail());
            if (_mapper.existSelective(condition)) {
                _log.warn("电子邮箱已存在: {}", to);
                UserRegRo regRo = new UserRegRo();
                regRo.setResult(RegResultDic.EMAIL_EXIST);
                return regRo;
            }
        }
        if (!StringUtils.isBlank(to.getMobile())) {
            condition = new SucUserMo();
            condition.setMobile(to.getMobile());
            if (_mapper.existSelective(condition)) {
                _log.warn("手机号码已存在: {}", to);
                UserRegRo regRo = new UserRegRo();
                regRo.setResult(RegResultDic.MOBILE_EXIST);
                return regRo;
            }
        }
        if (!StringUtils.isBlank(to.getIdcard())) {
            condition = new SucUserMo();
            condition.setIdcard(to.getIdcard());
            if (_mapper.existSelective(condition)) {
                _log.warn("身份证号码已存在: {}", to);
                UserRegRo regRo = new UserRegRo();
                regRo.setResult(RegResultDic.IDCARD_EXIST);
                return regRo;
            }
        }
        SucUserMo userMo = dozerMapper.map(to, SucUserMo.class);
        String salt = RandomEx.random1(6);
        userMo.setSalt(salt);
        userMo.setLoginPswd(saltPswd(to.getLoginPswd(), salt));
        userMo.setPayPswd(userMo.getLoginPswd());
        add(userMo);
        return returnSuccessReg(to, RegAndLoginTypeDic.LOGIN_NAME, userMo);
    }

    /**
     * 用户注册(通过QQ)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserRegRo regByQq(RegByQqTo to) {
        if (StringUtils.isAnyBlank(to.getQqId(), to.getQqNickname(), to.getQqFace(), to.getUserAgent(), to.getMac(),
                to.getIp()) || to.getAppId() == null) {
            _log.warn("没有填写用户QQ的ID/QQ昵称/QQ头像/应用ID/浏览器类型/MAC/IP: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        SucUserMo condition = new SucUserMo();
        condition.setQqId(to.getQqId());
        if (_mapper.existSelective(condition)) {
            _log.warn("QQ的ID已存在: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.QQ_ID_EXIST);
            return regRo;
        }
        SucUserMo userMo = dozerMapper.map(to, SucUserMo.class);
        add(userMo);
        return returnSuccessReg(to, RegAndLoginTypeDic.QQ, userMo);
    }

    /**
     * 用户注册(通过微信)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserRegRo regByWx(RegByWxTo to) {
        if (StringUtils.isAnyBlank(to.getWxId(), to.getWxNickname(), to.getUserAgent(), to.getMac(), to.getIp())
                || to.getAppId() == null) {
            _log.warn("没有填写用户微信的ID/微信昵称/应用ID/浏览器类型/MAC/IP: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        SucUserMo condition = new SucUserMo();
        condition.setWxId(to.getWxId());
        if (_mapper.existSelective(condition)) {
            _log.warn("微信的ID已存在: {}", to);
            UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.WX_ID_EXIST);
            return regRo;
        }
        SucUserMo userMo = dozerMapper.map(to, SucUserMo.class);
        add(userMo);
        return returnSuccessReg(to, RegAndLoginTypeDic.WX, userMo);
    }

    /**
     * 返回成功注册
     * 
     * @param regTo
     *            登录参数
     * @param regType
     *            登录类型
     * @param userMo
     *            获取到的用户信息
     * @return
     */
    private UserRegRo returnSuccessReg(RegBaseTo regTo, RegAndLoginTypeDic regType, SucUserMo userMo) {
        SucRegMo regMo = dozerMapper.map(regTo, SucRegMo.class);
        regMo.setId(userMo.getId());
        regMo.setRegTime(new Date(userMo.getModifiedTimestamp()));
        regMo.setRegType((byte) regType.getCode());
        regSvc.add(regMo);
        UserRegRo regRs = new UserRegRo();
        regRs.setUserId(userMo.getId());
        regRs.setResult(RegResultDic.SUCCESS);
        _log.info("用户注册成功: {} {} {}", regTo, regType, userMo);
        return regRs;
    }

    /**
     * 用户登录(通过登录名称)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserLoginRo loginByLoginName(LoginByLoginNameTo to) {
        if (StringUtils.isAnyBlank(to.getLoginName(), to.getLoginPswd(), to.getUserAgent(), to.getMac(), to.getIp())
                || to.getAppId() == null) {
            _log.warn("没有填写用户登录名称/密码/应用ID/浏览器类型/MAC/IP: {}", to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            return ro;
        }
        SucUserMo userMo = _mapper.selectByLoginName(to.getLoginName());
        if (userMo == null) {
            _log.warn("找不到此用户: {}", to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.NOT_FOUND_USER);
            return ro;
        }
        UserLoginRo ro = verifyLogin(userMo, to.getLoginPswd());
        if (ro != null)
            return ro;
        return returnSuccessLogin(to, RegAndLoginTypeDic.LOGIN_NAME, userMo);
    }

    /**
     * 用户登录(通过用户名称登录，按照 邮箱->手机->登录名 的顺序查找用户)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserLoginRo loginByUserName(LoginByUserNameTo to) {
        if (StringUtils.isAnyBlank(to.getUserName(), to.getLoginPswd(), to.getUserAgent(), to.getMac(), to.getIp())
                || to.getAppId() == null) {
            _log.warn("没有填写用户名/密码/应用ID/浏览器类型/MAC/IP: {}", to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            return ro;
        }
        RegAndLoginTypeDic loginType = null;
        SucUserMo userMo = null;
        if (RegexUtils.matchEmail(to.getUserName())) {
            userMo = _mapper.selectByEmail(to.getUserName());
            if (userMo != null) {
                if (!userMo.getIsVerifiedEmail()) {
                    _log.warn("用户用邮箱登录，但邮箱尚未通过验证: {}", to);
                    UserLoginRo ro = new UserLoginRo();
                    ro.setResult(LoginResultDic.NO_VERITY_EMAIL);
                    return ro;
                }
                loginType = RegAndLoginTypeDic.EMAIL;
            }
        } else if (RegexUtils.matchMobile(to.getUserName())) {
            userMo = _mapper.selectByMobile(to.getUserName());
            if (userMo != null) {
                if (!userMo.getIsVerifiedMobile()) {
                    _log.warn("用户用手机号登录，但手机号尚未通过验证: {}", to);
                    UserLoginRo ro = new UserLoginRo();
                    ro.setResult(LoginResultDic.NO_VERITY_MOBILE);
                    return ro;
                }
                loginType = RegAndLoginTypeDic.MOBILE;
            }
        }
        if (userMo == null) {
            userMo = _mapper.selectByLoginName(to.getUserName());
            if (userMo != null) {
                loginType = RegAndLoginTypeDic.LOGIN_NAME;
            }
        }
        if (userMo == null) {
            _log.warn("找不到此用户:" + to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.NOT_FOUND_USER);
            return ro;
        }
        UserLoginRo ro = verifyLogin(userMo, to.getLoginPswd());
        if (ro != null)
            return ro;
        return returnSuccessLogin(to, loginType, userMo);
    }

    /**
     * 用户登录(通过QQ)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserLoginRo loginByQq(LoginByQqTo to) {
        if (StringUtils.isAnyBlank(to.getQqId(), to.getQqNickname(), to.getQqFace(), to.getUserAgent(), to.getMac(),
                to.getIp()) || to.getAppId() == null) {
            _log.warn("没有填写用户QQ的ID/QQ昵称/QQ头像/应用ID/浏览器类型/MAC/IP: {}", to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            return ro;
        }
        SucUserMo userMo = _mapper.selectByQq(to.getQqId());
        if (userMo == null) {
            _log.warn("找不到此用户: {}", to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.NOT_FOUND_USER);
            return ro;
        }
        UserLoginRo ro = verifyLogin(userMo, null);
        if (ro != null)
            return ro;
        // 更新QQ的昵称和头像
        if (!StringUtils.isAnyBlank(to.getQqNickname(), to.getQqFace())) {
            if (!userMo.getQqNickname().equals(to.getQqNickname()) || !userMo.getQqFace().equals(to.getQqFace())) {
                _log.info("更新QQ的昵称和头像: {} {} -> {}", userMo.getQqNickname(), userMo.getQqFace(), to);
                Date now = new Date();
                SucUserMo modifyUserMo = new SucUserMo();
                modifyUserMo.setId(userMo.getId());
                modifyUserMo.setQqNickname(to.getQqNickname());
                modifyUserMo.setQqFace(to.getQqFace());
                modifyUserMo.setModifiedTimestamp(now.getTime());
                modify(modifyUserMo);

                // 记录用户操作日志
                SucOpLogMo opLogMo = new SucOpLogMo();
                opLogMo.setUserId(userMo.getId());
                opLogMo.setOpType((byte) SucOpTypeDic.MODIFY_QQ_INFO.getCode());
                opLogMo.setOpTime(now);
                opLogMo.setOpDetail(userMo.getQqNickname() + " " + userMo.getQqFace() //
                        + " ---> " + to.getQqNickname() + to.getQqFace());
                opLogMo.setAppId(to.getAppId());
                opLogMo.setUserAgent(to.getUserAgent());
                opLogMo.setMac(to.getMac());
                opLogMo.setIp(to.getIp());
                opLogSvc.add(opLogMo);
            }
        }
        return returnSuccessLogin(to, RegAndLoginTypeDic.QQ, userMo);
    }

    /**
     * 用户登录(通过微信)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserLoginRo loginByWx(LoginByWxTo to) {
        if (StringUtils.isAnyBlank(to.getWxId(), to.getWxNickname(), to.getUserAgent(), to.getMac(), to.getIp())
                || to.getAppId() == null) {
            _log.warn("没有填写用户微信的ID/微信昵称/应用ID/浏览器类型/MAC/IP: {}", to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            return ro;
        }
        SucUserMo userMo = _mapper.selectByWx(to.getWxId());
        if (userMo == null) {
            _log.warn("找不到此用户: {}", to);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.NOT_FOUND_USER);
            return ro;
        }
        UserLoginRo ro = verifyLogin(userMo, null);
        if (ro != null)
            return ro;
        // 更新微信的昵称和头像
        if (!StringUtils.isAnyBlank(to.getWxNickname(), to.getWxFace())) {
            if (!userMo.getWxNickname().equals(to.getWxNickname())
                    || (userMo.getWxFace() != null && !userMo.getWxFace().equals(to.getWxFace()))) {
                _log.info("更新微信的昵称和头像: {} {} -> {}", userMo.getWxNickname(), userMo.getWxFace(), to);
                Date now = new Date();
                SucUserMo modifyUserMo = new SucUserMo();
                modifyUserMo.setId(userMo.getId());
                modifyUserMo.setWxNickname(to.getWxNickname());
                modifyUserMo.setWxFace(to.getWxFace());
                modifyUserMo.setModifiedTimestamp(now.getTime());
                modify(modifyUserMo);

                // 记录用户操作日志
                SucOpLogMo opLogMo = new SucOpLogMo();
                opLogMo.setUserId(userMo.getId());
                opLogMo.setOpType((byte) SucOpTypeDic.MODIFY_WX_INFO.getCode());
                opLogMo.setOpTime(now);
                opLogMo.setOpDetail(userMo.getWxNickname() + " " + userMo.getWxFace() //
                        + " ---> " + to.getWxNickname() + to.getWxFace());
                opLogMo.setAppId(to.getAppId());
                opLogMo.setUserAgent(to.getUserAgent());
                opLogMo.setMac(to.getMac());
                opLogMo.setIp(to.getIp());
                opLogSvc.add(opLogMo);
            }
        }
        return returnSuccessLogin(to, RegAndLoginTypeDic.WX, userMo);
    }

    /**
     * 返回成功登录
     * 
     * @param loginTo
     *            登录参数
     * @param loginType
     *            登录类型
     * @param userMo
     *            获取到的用户信息
     * @return
     */
    private UserLoginRo returnSuccessLogin(RegAndLoginBaseTo loginTo, RegAndLoginTypeDic loginType, SucUserMo userMo) {
        SucLoginLogMo loginLogMo = dozerMapper.map(loginTo, SucLoginLogMo.class);
        loginLogMo.setUserId(userMo.getId());
        loginLogMo.setOpTime(new Date());
        loginLogMo.setLoginType((byte) loginType.getCode());
        loginLogSvc.add(loginLogMo);
        UserLoginRo ro = new UserLoginRo();
        ro.setUserId(userMo.getId());
        ro.setResult(LoginResultDic.SUCCESS);
        if (loginType == RegAndLoginTypeDic.EMAIL || loginType == RegAndLoginTypeDic.MOBILE
                || loginType == RegAndLoginTypeDic.LOGIN_NAME) {
            if (!StringUtils.isBlank(userMo.getNickname())) {
                ro.setNickname(userMo.getNickname());
            } else if (!StringUtils.isBlank(userMo.getWxNickname())) {
                ro.setNickname(userMo.getWxNickname());
            } else if (!StringUtils.isBlank(userMo.getQqNickname())) {
                ro.setNickname(userMo.getQqNickname());
            }
            if (!StringUtils.isBlank(userMo.getFace())) {
                ro.setFace(userMo.getFace());
            } else if (!StringUtils.isBlank(userMo.getWxFace())) {
                ro.setFace(userMo.getWxFace());
            } else if (!StringUtils.isBlank(userMo.getQqFace())) {
                ro.setFace(userMo.getQqFace());
            }
        }
        _log.info("用户登录成功: {} {} {}", loginTo, loginType, userMo);
        return ro;
    }

    /**
     * 找到用户后，校验用户是否允许登录
     * 
     * @param userMo
     *            查找到的用户
     * @return 如果允许，返回null；值不为null，表示不允许
     */
    private UserLoginRo verifyLogin(SucUserMo userMo, String loginPswd) {
        if (existBlacklist(userMo.getId())) {
            _log.warn("发现尝试使用黑名单中的用户: {}", userMo);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.LOCKED);
            return ro;
        }
        if (userMo.getIsLock()) {
            _log.warn("账号已被锁定，不允许使用: {}", userMo);
            UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.LOCKED);
            return ro;
        }
        if (loginPswd != null) {
            if (!userMo.getLoginPswd().equals(saltPswd(loginPswd, userMo.getSalt()))) {
                Long errorCount;
                try {
                    errorCount = redisClient.incr(REDIS_KEY_LOGINPSWD_ERRCOUNT_PREFIX + userMo.getId(),
                            DateUtils.getSecondUtilTomorrow());
                } catch (RedisSetException e) {
                    _log.error("操作当天连续输入登录密码错误的次数的缓存失败: {}", userMo);
                    UserLoginRo ro = new UserLoginRo();
                    ro.setResult(LoginResultDic.CACHE_FAIL);
                    return ro;
                }
                if (errorCount >= 5) {
                    try {
                        addBlacklistUtilTomorrow(userMo.getId());
                        appendLockLog(userMo.getId(), "用户登录系统多次输入密码错误被临时锁定，明日零时会自动解锁");
                    } catch (RedisSetException e) {
                        _log.error("添加黑名单缓存失败:{}", userMo);
                        UserLoginRo ro = new UserLoginRo();
                        ro.setResult(LoginResultDic.CACHE_FAIL);
                        return ro;
                    }
                }
                _log.warn("密码错误: {}", loginPswd);
                UserLoginRo ro = new UserLoginRo();
                ro.setResult(LoginResultDic.PASSWORD_ERROR);
                return ro;
            }
            redisClient.del(REDIS_KEY_LOGINPSWD_ERRCOUNT_PREFIX + userMo.getId());
        }
        return null;
    }

    /**
     * 加盐摘要密码
     * 
     * @param pswd
     *            登录密码(不是明文，而是将明文MD5传过来)
     * @param salt
     *            盐值
     * @return
     */
    private String saltPswd(String pswd, String salt) {
        return DigestUtils.md5AsHexStr((pswd + salt).toLowerCase().getBytes());
    }

    /**
     * 检查用户是否存在黑名单中
     */
    private Boolean existBlacklist(Long userId) {
        return redisClient.exists(REDIS_KEY_USER_BLACKLIST_PREFIX + userId);
    }

    /**
     * 将用户ID加入黑名单，直到明天
     */
    private void addBlacklistUtilTomorrow(Long userId) throws RedisSetException {
        redisClient.set(REDIS_KEY_USER_BLACKLIST_PREFIX + userId, "", DateUtils.getSecondUtilTomorrow());
    }

    /**
     * 添加锁定日志(加入黑名单后等操作后)
     */
    private void appendLockLog(Long userId, String lockReason) {
        SucLockLogMo lockMo = new SucLockLogMo();
        lockMo.setUserId(userId);
        lockMo.setLockReason("系统锁定：" + lockReason);
        lockMo.setLockTime(new Date());
        lockMo.setLockOpId(0L);
        lockLogSvc.add(lockMo);
    }

    /**
     * 判断支付时是否需要输入密码
     * 
     * @param userId
     *            用户ID
     * @param amount
     *            金额
     */
    @Override
    public Boolean requirePayPswd(Long userId, Double amount) {
        return amount != null && amount > 200;
    }

    /**
     * 校验支付密码
     * 
     * @param userId
     *            用户ID
     * @param payPswd
     *            支付密码
     * @param amount
     *            支付金额(判断金额在一定数量下可以免密码输入)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PayPswdVerifyRo verifyPayPswd(Long userId, String payPswd, Double amount) {
        if (userId == null || userId == 0) {
            _log.warn("没有填写用户ID");
            PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.PARAM_ERROR);
            return ro;
        }
        SucUserMo userMo = _mapper.selectByPrimaryKey(userId);
        if (userMo == null) {
            _log.warn("找不到此用户: {}", userId);
            PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.NOT_FOUND_USER);
            return ro;
        }
        if (existBlacklist(userMo.getId())) {
            _log.warn("发现尝试使用黑名单中的用户: {}", userMo);
            PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.LOCKED);
            return ro;
        }
        if (userMo.getIsLock()) {
            _log.warn("账号已被锁定，不允许使用: {}", userMo);
            PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.LOCKED);
            return ro;
        }
        if (requirePayPswd(userId, amount)) {
            if (StringUtils.isBlank(userMo.getPayPswd())) {
                _log.warn("用户没有设置支付密码: {}", userMo);
                PayPswdVerifyRo ro = new PayPswdVerifyRo();
                ro.setResult(VerifyPayPswdResultDic.NOT_SET_PASSWORD);
                return ro;
            }
            if (StringUtils.isBlank(payPswd)) {
                _log.warn("没有填写支付密码");
                PayPswdVerifyRo ro = new PayPswdVerifyRo();
                ro.setResult(VerifyPayPswdResultDic.PARAM_ERROR);
                return ro;
            }
            _log.info("校验支付密码: {}-{}", payPswd, userMo.getPayPswd());
            if (!userMo.getPayPswd().equals(saltPswd(payPswd, userMo.getSalt()))) {
                Long errorCount;
                try {
                    errorCount = redisClient.incr(REDIS_KEY_PAYPSWD_ERRCOUNT_PREFIX + userMo.getId(),
                            DateUtils.getSecondUtilTomorrow());
                } catch (RedisSetException e) {
                    _log.error("操作当天连续输入密码错误的次数的缓存失败: {}", userMo);
                    PayPswdVerifyRo ro = new PayPswdVerifyRo();
                    ro.setResult(VerifyPayPswdResultDic.CACHE_FAIL);
                    return ro;
                }
                if (errorCount >= 5) {
                    try {
                        addBlacklistUtilTomorrow(userMo.getId());
                        appendLockLog(userMo.getId(), "用户多次输入支付密码错误被临时锁定，明日零时会自动解锁");
                    } catch (RedisSetException e) {
                        _log.error("添加黑名单缓存失败:{}", userMo);
                        PayPswdVerifyRo ro = new PayPswdVerifyRo();
                        ro.setResult(VerifyPayPswdResultDic.CACHE_FAIL);
                        return ro;
                    }
                }
                _log.warn("密码错误: {}", payPswd);
                PayPswdVerifyRo ro = new PayPswdVerifyRo();
                ro.setResult(VerifyPayPswdResultDic.PASSWORD_ERROR);
                return ro;
            }
            redisClient.del(REDIS_KEY_PAYPSWD_ERRCOUNT_PREFIX + userMo.getId());
        }
        PayPswdVerifyRo ro = new PayPswdVerifyRo();
        ro.setResult(VerifyPayPswdResultDic.SUCCESS);
        return ro;
    }

    /**
     * 判断用户是否被锁定
     */
    @Override
    public Boolean isLocked(Long id) {
        SucUserMo condition = new SucUserMo();
        condition.setId(id);
        condition.setIsLock(true);
        return _mapper.existSelective(condition);
    }

    /**
     * 用户绑定微信
     */
    @Override
    public BindWxRo bindWx(BindWxTo to) {
        if (to.getUserId() == null || to.getAppId() == null || StringUtils.isAnyBlank(to.getWxId(), to.getWxNickname(),
                to.getUserAgent(), to.getMac(), to.getIp())) {
            _log.warn("没有填写用户ID/微信ID/微信昵称/应用ID/浏览器类型/MAC/IP: {}", to);
            BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.PARAM_ERROR);
            return ro;
        }

        SucUserMo userMo = getById(to.getUserId());
        if (userMo == null) {
            _log.warn("用户绑定微信发现没有此用户: " + to.getUserId());
            BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.NOT_FOUND_USER);
            return ro;
        }
        if (userMo.getIsLock()) {
            _log.warn("用户绑定微信发现用户被锁定: " + userMo);
            BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.USER_LOCKED);
            return ro;
        }

        SucUserMo condition = new SucUserMo();
        condition.setWxId(to.getWxId());
        if (_mapper.existSelective(condition)) {
            _log.warn("微信的ID已存在: {}", to);
            BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.WX_ID_EXIST);
            return ro;
        }

        // 当前时间
        Date now = new Date();

        // 绑定微信
        SucUserMo modifyUserMo = new SucUserMo();
        modifyUserMo.setId(to.getUserId());
        modifyUserMo.setWxId(to.getWxId());
        modifyUserMo.setWxNickname(to.getWxNickname());
        modifyUserMo.setWxFace(to.getWxFace());
        modifyUserMo.setModifiedTimestamp(now.getTime());
        modify(modifyUserMo);

        // 记录用户操作日志
        SucOpLogMo opLogMo = new SucOpLogMo();
        opLogMo.setUserId(to.getUserId());
        opLogMo.setOpType((byte) SucOpTypeDic.BIND_WX.getCode());
        opLogMo.setOpTime(now);
        opLogMo.setOpDetail(userMo.getWxNickname() + " " + userMo.getWxFace() //
                + " ---> " + to.getWxNickname() + to.getWxFace());
        opLogMo.setAppId(to.getAppId());
        opLogMo.setUserAgent(to.getUserAgent());
        opLogMo.setMac(to.getMac());
        opLogMo.setIp(to.getIp());
        opLogSvc.add(opLogMo);

        // 返回成功
        _log.info("用户绑定微信成功: {}", to);
        BindWxRo ro = new BindWxRo();
        ro.setResult(BindWxResultDic.SUCCESS);
        return ro;
    }

    /**
     * 获取用户ID(通过用户名称)
     */
    @Override
    public Long getIdByUserName(String userName) {
        if (userName == null) {
            _log.warn("没有填写用户名称: {}", userName);
            return null;
        }
        SucUserMo userMo = null;
        if (RegexUtils.matchEmail(userName)) {
            userMo = _mapper.selectByEmail(userName);
            if (userMo != null) {
                if (!userMo.getIsVerifiedEmail()) {
                    _log.warn("用户用邮箱登录，但邮箱尚未通过验证: {}", userName);
                    return null;
                }
            }
        } else if (RegexUtils.matchMobile(userName)) {
            userMo = _mapper.selectByMobile(userName);
            if (userMo != null) {
                if (!userMo.getIsVerifiedMobile()) {
                    _log.warn("用户用手机号登录，但手机号尚未通过验证: {}", userName);
                    return null;
                }
            }
        }
        if (userMo == null) {
            userMo = _mapper.selectByLoginName(userName);
        }
        if (userMo == null) {
            return null;
        }
        return userMo.getId();
    }

    /**
     * 获取用户ID(通过微信ID)
     */
    @Override
    public Long getIdByWxId(String wxId) {
        if (wxId == null) {
            _log.warn("没有填写微信ID: {}", wxId);
            return null;
        }
        SucUserMo userMo = _mapper.selectByWx(wxId);
        if (userMo == null) {
            return null;
        }
        return userMo.getId();
    }

}
