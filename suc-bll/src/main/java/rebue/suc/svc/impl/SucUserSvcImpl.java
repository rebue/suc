package rebue.suc.svc.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import rebue.pnt.mo.PntAccountMo;
import rebue.pnt.svr.feign.PntAccountSvc;
import rebue.robotech.svc.impl.MybatisBaseSvcImpl;
import rebue.sbs.redis.RedisClient;
import rebue.sbs.redis.RedisSetException;
import rebue.suc.dic.BindWxResultDic;
import rebue.suc.dic.GetLoginNameDic;
import rebue.suc.dic.LoginPswdModifyDic;
import rebue.suc.dic.LoginPswdSetDic;
import rebue.suc.dic.LoginResultDic;
import rebue.suc.dic.PayPswdModifyDic;
import rebue.suc.dic.PayPswdSetDic;
import rebue.suc.dic.RegAndLoginTypeDic;
import rebue.suc.dic.RegResultDic;
import rebue.suc.dic.SetLoginNameDic;
import rebue.suc.dic.SucOpTypeDic;
import rebue.suc.dic.VerifyPayPswdResultDic;
import rebue.suc.mapper.SucUserMapper;
import rebue.suc.mo.SucLockLogMo;
import rebue.suc.mo.SucLoginLogMo;
import rebue.suc.mo.SucOpLogMo;
import rebue.suc.mo.SucRegMo;
import rebue.suc.mo.SucUserMo;
import rebue.suc.msg.SucAddUserDoneMsg;
import rebue.suc.pub.SucAddUserDonePub;
import rebue.suc.ro.BindWxRo;
import rebue.suc.ro.CurrentUserRo;
import rebue.suc.ro.GetLoginNameRo;
import rebue.suc.ro.LoginPswdModifyRo;
import rebue.suc.ro.LoginPswdSetRo;
import rebue.suc.ro.PayPswdModifyRo;
import rebue.suc.ro.PayPswdSetRo;
import rebue.suc.ro.PayPswdVerifyRo;
import rebue.suc.ro.SetLoginNameRo;
import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.SucUserRo;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.ro.UserPointRo;
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
    private static final Logger _log                                = LoggerFactory.getLogger(SucUserSvcImpl.class);

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

    /**
     * 用户购买关系生效时间（以小时计）
     */
    @Value("${suc.buyRelationHoldHours}")
    private Integer             buyRelationHoldHours;

    /**
     * 免密支付额度
     */
    @Value("${suc.notPwdPayLimit}")
    private Double              notPwdPayLimit;
    
    @Resource
    private PntAccountSvc       pntAccountSvc;
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
    private SucAddUserDonePub   userAddPub;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(final SucUserMo mo) {
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        if (mo.getModifiedTimestamp() == null) {
            mo.setModifiedTimestamp(System.currentTimeMillis());
        }
        final int result = super.add(mo);
        // 发布添加用户的消息
        final SucAddUserDoneMsg msg = dozerMapper.map(mo, SucAddUserDoneMsg.class);
        userAddPub.send(msg);
        return result;
    }

    /**
     * 用户注册(通过登录名称) TODO SUC : 用户注册(通过Email/Mobile)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserRegRo regByLoginName(final RegByLoginNameTo to) {
        if (StringUtils.isAnyBlank(to.getLoginName(), to.getLoginPswd(), to.getUserAgent(), to.getMac(), to.getIp()) || to.getSysId() == null) {
            _log.warn("没有填写用户登录名称/登录密码/应用ID/浏览器类型/MAC/IP: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            regRo.setMsg("参数不正确");
            return regRo;
        }
        if (to.getLoginName().length() < 3 || to.getLoginName().length() > 20 || RegexUtils.matchEmail(to.getLoginName()) || RegexUtils.matchMobile(to.getLoginName())) {
            _log.warn("登录名称格式不正确: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            regRo.setMsg("登录名称格式不正确");
            return regRo;
        }
        if (to.getLoginPswd().length() != 32) {
            _log.warn("登录密码经过MD5后应该为32个字符: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            regRo.setMsg("参数不正确");
            return regRo;
        }
        if (!StringUtils.isBlank(to.getEmail()) && !RegexUtils.matchEmail(to.getEmail())) {
            _log.warn("电子邮箱格式不正确: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            regRo.setMsg("电子邮箱格式不正确");
            return regRo;
        }
        if (!StringUtils.isBlank(to.getMobile()) && !RegexUtils.matchMobile(to.getMobile())) {
            _log.warn("手机号码格式不正确: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            regRo.setMsg("手机号码格式不正确");
            return regRo;
        }
        if (!StringUtils.isBlank(to.getIdcard()) && !IdCardValidator.validate(to.getIdcard())) {
            _log.warn("身份证号码格式不正确: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            regRo.setMsg("身份证号码格式不正确");
            return regRo;
        }
        SucUserMo condition = new SucUserMo();
        condition.setLoginName(to.getLoginName());
        if (_mapper.existSelective(condition)) {
            _log.warn("用户登录名称已存在: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.LOGIN_NAME_EXIST);
            regRo.setMsg("用户登录名称已存在");
            return regRo;
        }
        if (!StringUtils.isBlank(to.getEmail())) {
            condition = new SucUserMo();
            condition.setEmail(to.getEmail());
            if (_mapper.existSelective(condition)) {
                _log.warn("电子邮箱已存在: {}", to);
                final UserRegRo regRo = new UserRegRo();
                regRo.setResult(RegResultDic.EMAIL_EXIST);
                regRo.setMsg("电子邮箱已存在");
                return regRo;
            }
        }
        if (!StringUtils.isBlank(to.getMobile())) {
            condition = new SucUserMo();
            condition.setMobile(to.getMobile());
            if (_mapper.existSelective(condition)) {
                _log.warn("手机号码已存在: {}", to);
                final UserRegRo regRo = new UserRegRo();
                regRo.setResult(RegResultDic.MOBILE_EXIST);
                regRo.setMsg("手机号码已存在");
                return regRo;
            }
        }
        if (!StringUtils.isBlank(to.getIdcard())) {
            condition = new SucUserMo();
            condition.setIdcard(to.getIdcard());
            if (_mapper.existSelective(condition)) {
                _log.warn("身份证号码已存在: {}", to);
                final UserRegRo regRo = new UserRegRo();
                regRo.setResult(RegResultDic.IDCARD_EXIST);
                regRo.setMsg("身份证号码已存在");
                return regRo;
            }
        }
        final SucUserMo userMo = dozerMapper.map(to, SucUserMo.class);
        final String salt = RandomEx.random1(6);
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
    public UserRegRo regByQq(final RegByQqTo to) {
        if (StringUtils.isAnyBlank(to.getQqId(), to.getQqNickname(), to.getQqFace(), to.getUserAgent(), to.getMac(), to.getIp()) || to.getSysId() == null) {
            _log.warn("没有填写用户QQ的ID/QQ昵称/QQ头像/应用ID/浏览器类型/MAC/IP: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        final SucUserMo condition = new SucUserMo();
        condition.setQqId(to.getQqId());
        if (_mapper.existSelective(condition)) {
            _log.warn("QQ的ID已存在: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.QQ_ID_EXIST);
            return regRo;
        }
        final SucUserMo userMo = dozerMapper.map(to, SucUserMo.class);
        add(userMo);
        return returnSuccessReg(to, RegAndLoginTypeDic.QQ, userMo);
    }

    /**
     * 用户注册(通过微信)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserRegRo regByWx(final RegByWxTo to) {
        if (StringUtils.isAllBlank(to.getWxId(), to.getWxOpenid()) || StringUtils.isAnyBlank(to.getWxNickname(), to.getUserAgent(), to.getMac(), to.getIp())
                || to.getSysId() == null) {
            _log.warn("没有填写用户微信的OpenID或UnionID/微信昵称/应用ID/浏览器类型/MAC/IP: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.PARAM_ERROR);
            return regRo;
        }
        SucUserMo condition = new SucUserMo();
        if (to.getWxId() != null) {
            condition.setWxId(to.getWxId());
        }
        if (to.getWxOpenid() != null) {
            condition.setWxOpenid(to.getWxOpenid());
        }
        if (_mapper.existSelective(condition)) {
            _log.warn("微信的UnionID或OpenID已存在: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.WX_ID_EXIST);
            return regRo;
        }
        condition = new SucUserMo();
        condition.setWxOpenid(to.getWxOpenid());
        if (_mapper.existSelective(condition)) {
            _log.warn("微信的ID已存在: {}", to);
            final UserRegRo regRo = new UserRegRo();
            regRo.setResult(RegResultDic.WX_ID_EXIST);
            return regRo;
        }
        final SucUserMo userMo = dozerMapper.map(to, SucUserMo.class);
        add(userMo);
        final UserRegRo ro = returnSuccessReg(to, RegAndLoginTypeDic.WX, userMo);
        ro.setUserWxUnionId(to.getWxId());
        ro.setUserWxOpenId(to.getWxOpenid());
        return ro;
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
    private UserRegRo returnSuccessReg(final RegBaseTo regTo, final RegAndLoginTypeDic regType, final SucUserMo userMo) {
        final SucRegMo regMo = dozerMapper.map(regTo, SucRegMo.class);
        regMo.setId(userMo.getId());
        regMo.setRegTime(new Date(userMo.getModifiedTimestamp()));
        regMo.setRegType((byte) regType.getCode());
        regSvc.add(regMo);
        final UserRegRo regRo = new UserRegRo();
        regRo.setUserId(userMo.getId());
        regRo.setResult(RegResultDic.SUCCESS);
        regRo.setMsg("用户注册成功");
        _log.info("用户注册成功: {} {} {}", regTo, regType, userMo);
        return regRo;
    }

    /**
     * 用户登录(通过登录名称)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserLoginRo loginByLoginName(final LoginByLoginNameTo to) {
        if (StringUtils.isAnyBlank(to.getLoginName(), to.getLoginPswd(), to.getUserAgent(), to.getMac(), to.getIp()) || to.getSysId() == null) {
            _log.warn("没有填写用户登录名称/密码/应用ID/浏览器类型/MAC/IP: {}", to);
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            ro.setMsg("参数错误");
            return ro;
        }
        final SucUserMo userMo = _mapper.selectByLoginName(to.getLoginName());
        if (userMo == null) {
            _log.warn("找不到此用户: {}", to);
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.NOT_FOUND_USER);
            ro.setMsg("找不到此用户");
            return ro;
        }
        if (userMo.getLoginPswd() == null) {
            final UserLoginRo ro = new UserLoginRo();
            ro.setMsg("该用户没有设置登录密码，请设置好登录密码才能登录");
            ro.setResult(LoginResultDic.PASSWORD_ERROR);
            ro.setMsg("该用户没有设置登录密码，请设置好登录密码才能登录");
            return ro;
        }
        final UserLoginRo ro = verifyLogin(userMo, to.getLoginPswd());
        if (ro != null) {
            return ro;
        }
        return returnSuccessLogin(to, RegAndLoginTypeDic.LOGIN_NAME, userMo);
    }

    /**
     * 用户登录(通过用户名称登录，按照 邮箱->手机->登录名 的顺序查找用户)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserLoginRo loginByUserName(final LoginByUserNameTo to) {
        if (StringUtils.isAnyBlank(to.getUserName(), to.getLoginPswd(), to.getUserAgent(), to.getMac(), to.getIp()) || to.getSysId() == null) {
            _log.warn("没有填写用户名/密码/应用ID/浏览器类型/MAC/IP: {}", to);
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            ro.setMsg("参数错误");
            return ro;
        }
        RegAndLoginTypeDic loginType = null;
        SucUserMo userMo = null;
        if (RegexUtils.matchEmail(to.getUserName())) {
            userMo = _mapper.selectByEmail(to.getUserName());
            if (userMo != null) {
                if (!userMo.getIsVerifiedEmail()) {
                    _log.warn("用户用邮箱登录，但邮箱尚未通过验证: {}", to);
                    final UserLoginRo ro = new UserLoginRo();
                    ro.setResult(LoginResultDic.NO_VERITY_EMAIL);
                    ro.setMsg("该邮箱未认证");
                    return ro;
                }
                loginType = RegAndLoginTypeDic.EMAIL;
            }
        } else if (RegexUtils.matchMobile(to.getUserName())) {
            userMo = _mapper.selectByMobile(to.getUserName());
            if (userMo != null) {
                if (!userMo.getIsVerifiedMobile()) {
                    _log.warn("用户用手机号登录，但手机号尚未通过验证: {}", to);
                    final UserLoginRo ro = new UserLoginRo();
                    ro.setResult(LoginResultDic.NO_VERITY_MOBILE);
                    ro.setMsg("该手机号码未认证");
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
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.NOT_FOUND_USER);
            ro.setMsg("找不到此用户");
            return ro;
        }
        if (userMo.getLoginPswd() == null) {
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PASSWORD_ERROR);
            ro.setMsg("该用户没有设置登录密码，请设置好登录密码才能登录");
            return ro;
        }
        final UserLoginRo ro = verifyLogin(userMo, to.getLoginPswd());
        if (ro != null) {
            return ro;
        }
        return returnSuccessLogin(to, loginType, userMo);
    }

    /**
     * 用户登录(通过QQ)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserLoginRo loginByQq(final LoginByQqTo to) {
        if (StringUtils.isAnyBlank(to.getQqId(), to.getQqOpenid(), to.getQqNickname(), to.getQqFace(), to.getUserAgent(), to.getMac(), to.getIp()) || to.getSysId() == null) {
            _log.warn("没有填写用户QQ的ID/QQ昵称/QQ头像/应用ID/浏览器类型/MAC/IP: {}", to);
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            return ro;
        }
        SucUserMo userMo = _mapper.selectByQq(to.getQqId());
        if (userMo == null) {
            _log.warn("根据QQId找不到此用户: {}", to);
            userMo = _mapper.selectByQqopenId(to.getQqOpenid());
            if (userMo == null) {
                _log.warn("根据QQopenid找不到此用户: {}", to);
                final UserLoginRo ro = new UserLoginRo();
                ro.setResult(LoginResultDic.NOT_FOUND_USER);
                return ro;
            }
        }
        final UserLoginRo ro = verifyLogin(userMo, null);
        if (ro != null) {
            return ro;
        }
        // 更新QQ的昵称和头像
        if (!StringUtils.isAnyBlank(to.getQqNickname(), to.getQqFace())) {
            if (!userMo.getQqNickname().equals(to.getQqNickname()) || !userMo.getQqFace().equals(to.getQqFace())) {
                _log.info("更新QQ的昵称和头像: {} {} -> {}", userMo.getQqNickname(), userMo.getQqFace(), to);
                final Date now = new Date();
                final SucUserMo modifyUserMo = new SucUserMo();
                modifyUserMo.setId(userMo.getId());
                modifyUserMo.setQqNickname(to.getQqNickname());
                modifyUserMo.setQqFace(to.getQqFace());
                modifyUserMo.setModifiedTimestamp(now.getTime());
                modify(modifyUserMo);
                // 记录用户操作日志
                final SucOpLogMo opLogMo = new SucOpLogMo();
                opLogMo.setUserId(userMo.getId());
                opLogMo.setOpType((byte) SucOpTypeDic.MODIFY_QQ_INFO.getCode());
                opLogMo.setOpTime(now);
                //
                opLogMo.setOpDetail(userMo.getQqNickname() + " " + userMo.getQqFace() + " ---> " + to.getQqNickname() + to.getQqFace());
                opLogMo.setSysId(to.getSysId());
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
    public UserLoginRo loginByWx(final LoginByWxTo to) {
        if (StringUtils.isAllBlank(to.getWxId(), to.getWxOpenid()) || StringUtils.isAnyBlank(to.getWxNickname(), to.getUserAgent(), to.getMac(), to.getIp())
                || to.getSysId() == null) {
            _log.warn("没有填写用户微信的OpenID或UnionID/微信昵称/应用ID/浏览器类型/MAC/IP: {}", to);
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.PARAM_ERROR);
            return ro;
        }
        SucUserMo userMo = null;
        if (to.getWxId() != null) {
            _log.info("根据微信UnionID查找用户: {}", to);
            userMo = _mapper.selectByWx(to.getWxId());
        }
        if (userMo == null) {
            _log.info("根据微信OpenId查找用户: {}", to);
            userMo = _mapper.selectByWxOpenid(to.getWxOpenid());
            if (userMo == null) {
                _log.info("未找到用户: {}", to);
                final UserLoginRo ro = new UserLoginRo();
                ro.setResult(LoginResultDic.NOT_FOUND_USER);
                return ro;
            }
        }
        UserLoginRo ro = verifyLogin(userMo, null);
        if (ro != null) {
            return ro;
        }
        // 更新微信的昵称和头像
        if (!StringUtils.isAnyBlank(to.getWxNickname(), to.getWxFace())) {
            if (!userMo.getWxNickname().equals(to.getWxNickname()) || (userMo.getWxFace() != null && !userMo.getWxFace().equals(to.getWxFace()))) {
                _log.info("更新微信的昵称和头像: {} {} -> {}", userMo.getWxNickname(), userMo.getWxFace(), to);
                final Date now = new Date();
                final SucUserMo modifyUserMo = new SucUserMo();
                modifyUserMo.setId(userMo.getId());
                modifyUserMo.setWxNickname(to.getWxNickname());
                modifyUserMo.setWxFace(to.getWxFace());
                modifyUserMo.setModifiedTimestamp(now.getTime());
                modify(modifyUserMo);
                // 记录用户操作日志
                final SucOpLogMo opLogMo = new SucOpLogMo();
                opLogMo.setUserId(userMo.getId());
                opLogMo.setOpType((byte) SucOpTypeDic.MODIFY_WX_INFO.getCode());
                opLogMo.setOpTime(now);
                //
                opLogMo.setOpDetail(userMo.getWxNickname() + " " + userMo.getWxFace() + " ---> " + to.getWxNickname() + to.getWxFace());
                opLogMo.setSysId(to.getSysId());
                opLogMo.setUserAgent(to.getUserAgent());
                opLogMo.setMac(to.getMac());
                opLogMo.setIp(to.getIp());
                opLogSvc.add(opLogMo);
            }
        }
        ro = returnSuccessLogin(to, RegAndLoginTypeDic.WX, userMo);
        ro.setUserWxUnionId(to.getWxId());
        ro.setUserWxOpenId(to.getWxOpenid());
        return ro;
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
    private UserLoginRo returnSuccessLogin(final RegAndLoginBaseTo loginTo, final RegAndLoginTypeDic loginType, final SucUserMo userMo) {
        _log.info("成功登录获取到的登录参数为：{}", loginTo);
        final SucLoginLogMo loginLogMo = dozerMapper.map(loginTo, SucLoginLogMo.class);
        loginLogMo.setUserId(userMo.getId());
        loginLogMo.setOpTime(new Date());
        loginLogMo.setLoginType((byte) loginType.getCode());
        loginLogSvc.add(loginLogMo);
        final UserLoginRo ro = new UserLoginRo();
        ro.setUserId(userMo.getId());
        ro.setIsTester(userMo.getIsTester());
        ro.setOrgId(userMo.getOrgId());
        ro.setResult(LoginResultDic.SUCCESS);
        ro.setMsg("用户登录成功");

        // 判断应该返回的昵称
        if (!StringUtils.isBlank(userMo.getNickname())) {
            ro.setNickname(userMo.getNickname());
        } else if (!StringUtils.isBlank(userMo.getWxNickname())) {
            ro.setNickname(userMo.getWxNickname());
        } else if (!StringUtils.isBlank(userMo.getQqNickname())) {
            ro.setNickname(userMo.getQqNickname());
        } else if (!StringUtils.isBlank(userMo.getLoginName())) {
            ro.setNickname(userMo.getLoginName());
        }
        // 判断应该返回的头像
        if (!StringUtils.isBlank(userMo.getFace())) {
            ro.setFace(userMo.getFace());
        } else if (!StringUtils.isBlank(userMo.getWxFace())) {
            ro.setFace(userMo.getWxFace());
        } else if (!StringUtils.isBlank(userMo.getQqFace())) {
            ro.setFace(userMo.getQqFace());
        }

        return ro;
    }

    /**
     * 找到用户后，校验用户是否允许登录
     *
     * @param userMo
     *            查找到的用户
     * @return 如果允许，返回null；值不为null，表示不允许
     */
    private UserLoginRo verifyLogin(final SucUserMo userMo, final String loginPswd) {
        if (existBlacklist(userMo.getId())) {
            _log.warn("发现尝试使用黑名单中的用户: {}", userMo);
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.LOCKED);
            ro.setMsg("该用户已列入黑名单");
            return ro;
        }
        if (userMo.getIsLock()) {
            _log.warn("用户已被锁定，不允许使用: {}", userMo);
            final UserLoginRo ro = new UserLoginRo();
            ro.setResult(LoginResultDic.LOCKED);
            ro.setMsg("该用户已被锁定");
            return ro;
        }
        if (loginPswd != null) {
            if (!userMo.getLoginPswd().equals(saltPswd(loginPswd, userMo.getSalt()))) {
                Long errorCount;
                try {
                    errorCount = redisClient.incr(REDIS_KEY_LOGINPSWD_ERRCOUNT_PREFIX + userMo.getId(), DateUtils.getSecondUtilTomorrow());
                } catch (final RedisSetException e) {
                    _log.error("操作当天连续输入登录密码错误的次数的缓存失败: {}", userMo);
                    final UserLoginRo ro = new UserLoginRo();
                    ro.setResult(LoginResultDic.CACHE_FAIL);
                    ro.setMsg("缓存失败");
                    return ro;
                }
                if (errorCount >= 5) {
                    try {
                        addBlacklistUtilTomorrow(userMo.getId());
                        appendLockLog(userMo.getId(), "用户登录系统多次输入密码错误被临时锁定，明日零时会自动解锁");
                    } catch (final RedisSetException e) {
                        _log.error("添加黑名单缓存失败:{}", userMo);
                        final UserLoginRo ro = new UserLoginRo();
                        ro.setResult(LoginResultDic.CACHE_FAIL);
                        ro.setMsg("缓存失败");
                        return ro;
                    }
                }
                _log.warn("密码错误: {}", loginPswd);
                final UserLoginRo ro = new UserLoginRo();
                ro.setResult(LoginResultDic.PASSWORD_ERROR);
                ro.setMsg("密码错误");
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
    private String saltPswd(final String pswd, final String salt) {
        return DigestUtils.md5AsHexStr((pswd + salt).toLowerCase().getBytes());
    }

    /**
     * 检查用户是否存在黑名单中
     */
    private Boolean existBlacklist(final Long userId) {
        return redisClient.exists(REDIS_KEY_USER_BLACKLIST_PREFIX + userId);
    }

    /**
     * 将用户ID加入黑名单，直到明天
     */
    private void addBlacklistUtilTomorrow(final Long userId) throws RedisSetException {
        redisClient.set(REDIS_KEY_USER_BLACKLIST_PREFIX + userId, "", DateUtils.getSecondUtilTomorrow());
    }

    /**
     * 添加锁定日志(加入黑名单后等操作后)
     */
    private void appendLockLog(final Long userId, final String lockReason) {
        final SucLockLogMo lockMo = new SucLockLogMo();
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
    public Boolean requirePayPswd(final Long userId, final Double amount) {
        return amount != null && amount > notPwdPayLimit;
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
    public PayPswdVerifyRo verifyPayPswd(final Long userId, final String payPswd, final Double amount) {
        if (userId == null || userId == 0) {
            _log.warn("没有填写用户ID");
            final PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.PARAM_ERROR);
            return ro;
        }
        final SucUserMo userMo = _mapper.selectByPrimaryKey(userId);
        if (userMo == null) {
            _log.warn("找不到此用户: {}", userId);
            final PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.NOT_FOUND_USER);
            return ro;
        }
        if (existBlacklist(userMo.getId())) {
            _log.warn("发现尝试使用黑名单中的用户: {}", userMo);
            final PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.LOCKED);
            return ro;
        }
        if (userMo.getIsLock()) {
            _log.warn("账号已被锁定，不允许使用: {}", userMo);
            final PayPswdVerifyRo ro = new PayPswdVerifyRo();
            ro.setResult(VerifyPayPswdResultDic.LOCKED);
            return ro;
        }
        if (requirePayPswd(userId, amount)) {
            if (StringUtils.isBlank(userMo.getPayPswd())) {
                _log.warn("用户没有设置支付密码: {}", userMo);
                final PayPswdVerifyRo ro = new PayPswdVerifyRo();
                ro.setResult(VerifyPayPswdResultDic.NOT_SET_PASSWORD);
                return ro;
            }
            if (StringUtils.isBlank(payPswd)) {
                _log.warn("没有填写支付密码");
                final PayPswdVerifyRo ro = new PayPswdVerifyRo();
                ro.setResult(VerifyPayPswdResultDic.PARAM_ERROR);
                return ro;
            }
            _log.info("校验支付密码: {}-{}", payPswd, userMo.getPayPswd());
            if (!userMo.getPayPswd().equals(saltPswd(payPswd, userMo.getSalt()))) {
                Long errorCount;
                try {
                    errorCount = redisClient.incr(REDIS_KEY_PAYPSWD_ERRCOUNT_PREFIX + userMo.getId(), DateUtils.getSecondUtilTomorrow());
                } catch (final RedisSetException e) {
                    _log.error("操作当天连续输入密码错误的次数的缓存失败: {}", userMo);
                    final PayPswdVerifyRo ro = new PayPswdVerifyRo();
                    ro.setResult(VerifyPayPswdResultDic.CACHE_FAIL);
                    return ro;
                }
                if (errorCount >= 5) {
                    try {
                        addBlacklistUtilTomorrow(userMo.getId());
                        appendLockLog(userMo.getId(), "用户多次输入支付密码错误被临时锁定，明日零时会自动解锁");
                    } catch (final RedisSetException e) {
                        _log.error("添加黑名单缓存失败:{}", userMo);
                        final PayPswdVerifyRo ro = new PayPswdVerifyRo();
                        ro.setResult(VerifyPayPswdResultDic.CACHE_FAIL);
                        return ro;
                    }
                }
                _log.warn("密码错误: {}", payPswd);
                final PayPswdVerifyRo ro = new PayPswdVerifyRo();
                ro.setResult(VerifyPayPswdResultDic.PASSWORD_ERROR);
                return ro;
            }
            redisClient.del(REDIS_KEY_PAYPSWD_ERRCOUNT_PREFIX + userMo.getId());
        }
        final PayPswdVerifyRo ro = new PayPswdVerifyRo();
        ro.setResult(VerifyPayPswdResultDic.SUCCESS);
        return ro;
    }

    /**
     * 判断用户是否被锁定
     */
    @Override
    public Boolean isLocked(final Long userId) {
        _log.info("判断用户是否被锁定: {}", userId);
        final SucUserMo condition = new SucUserMo();
        condition.setId(userId);
        condition.setIsLock(true);
        return _mapper.existSelective(condition);
    }

    /**
     * 判断用户是否是测试用户
     */
    @Override
    public Boolean isTester(final Long userId) {
        _log.info("判断用户是否是测试用户: {}", userId);
        final SucUserMo condition = new SucUserMo();
        condition.setId(userId);
        condition.setIsTester(true);
        return _mapper.existSelective(condition);
    }

    /**
     * 用户绑定微信
     */
    @Override
    public BindWxRo bindWx(final BindWxTo to) {
        if (to.getUserId() == null || to.getSysId() == null || StringUtils.isAnyBlank(to.getWxId(), to.getWxNickname(), to.getUserAgent(), to.getMac(), to.getIp())) {
            _log.warn("没有填写用户ID/微信ID/微信昵称/应用ID/浏览器类型/MAC/IP: {}", to);
            final BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.PARAM_ERROR);
            return ro;
        }
        final SucUserMo userMo = getById(to.getUserId());
        if (userMo == null) {
            _log.warn("用户绑定微信发现没有此用户: " + to.getUserId());
            final BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.NOT_FOUND_USER);
            return ro;
        }
        if (userMo.getIsLock()) {
            _log.warn("用户绑定微信发现用户被锁定: " + userMo);
            final BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.USER_LOCKED);
            return ro;
        }
        final SucUserMo condition = new SucUserMo();
        condition.setWxId(to.getWxId());
        if (_mapper.existSelective(condition)) {
            _log.warn("微信的ID已存在: {}", to);
            final BindWxRo ro = new BindWxRo();
            ro.setResult(BindWxResultDic.WX_ID_EXIST);
            return ro;
        }
        // 当前时间
        final Date now = new Date();
        // 绑定微信
        final SucUserMo modifyUserMo = new SucUserMo();
        modifyUserMo.setId(to.getUserId());
        modifyUserMo.setWxId(to.getWxId());
        modifyUserMo.setWxNickname(to.getWxNickname());
        modifyUserMo.setWxFace(to.getWxFace());
        modifyUserMo.setModifiedTimestamp(now.getTime());
        modify(modifyUserMo);
        // 记录用户操作日志
        final SucOpLogMo opLogMo = new SucOpLogMo();
        opLogMo.setUserId(to.getUserId());
        opLogMo.setOpType((byte) SucOpTypeDic.BIND_WX.getCode());
        opLogMo.setOpTime(now);
        //
        opLogMo.setOpDetail(userMo.getWxNickname() + " " + userMo.getWxFace() + " ---> " + to.getWxNickname() + to.getWxFace());
        opLogMo.setSysId(to.getSysId());
        opLogMo.setUserAgent(to.getUserAgent());
        opLogMo.setMac(to.getMac());
        opLogMo.setIp(to.getIp());
        opLogSvc.add(opLogMo);
        // 返回成功
        _log.info("用户绑定微信成功: {}", to);
        final BindWxRo ro = new BindWxRo();
        ro.setResult(BindWxResultDic.SUCCESS);
        return ro;
    }

    /**
     * 获取用户ID(通过用户名称)
     */
    @Override
    public Long getIdByUserName(final String userName) {
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
    public Long getIdByWxId(final String wxId) {
        if (wxId == null) {
            _log.warn("没有填写微信ID: {}", wxId);
            return null;
        }
        final SucUserMo userMo = _mapper.selectByWx(wxId);
        if (userMo == null) {
            return null;
        }
        return userMo.getId();
    }

    /**
     * 微信设置登录密码 Title: setLoginPassword Description:
     *
     * @param wxId
     * @param newLoginPswd
     * @return
     * @date 2018年5月2日 下午12:57:25 1、判断参数是否为空 2、查询用户信息并判断该用户是否存在 3、判断登录密码是否为空
     *       4、添加登录密码
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public LoginPswdSetRo setLoginPassword(final String wxId, String newLoginPswd) {
        final LoginPswdSetRo setRo = new LoginPswdSetRo();
        if (wxId == null || wxId.equals("") || wxId.equals("null")) {
            _log.error("设置登录密码时出现微信ID为空");
            setRo.setResult(LoginPswdSetDic.NOT_LOGIN);
            setRo.setMsg("您未登录，请先登录");
            return setRo;
        }
        if (newLoginPswd == null || newLoginPswd.equals("") || newLoginPswd.equals("null")) {
            _log.error("设置登录密码时出现没有输入新的登录密码，微信ID为：{}", wxId);
            setRo.setResult(LoginPswdSetDic.NEW_LOGINPSWD_NULL);
            setRo.setMsg("请输入登录密码");
            return setRo;
        }
        _log.info("设置登录密码查询用户信息的参数为：{}", wxId);
        // 查询用户信息
        final SucUserMo userMo = _mapper.selectUserInfoByWx(wxId);
        _log.info("设置登录密码查询用户信息的返回值为：{}", userMo.toString());
        if (userMo.getWxId() == null || userMo.getWxId().equals("") || userMo.getWxId().equals("null")) {
            _log.error("设置登录密码查询用户信息时出现用户信息为空，微信ID为：{}", wxId);
            setRo.setResult(LoginPswdSetDic.NOT_FOUND_USER);
            setRo.setMsg("找不到用户信息");
            return setRo;
        }
        if (userMo.getLoginPswd() != null && !userMo.getLoginPswd().equals("") && !userMo.getLoginPswd().equals("null")) {
            _log.error("微信设置登录密码时出现该用户微信用户已设置了登录密码，微信ID为：{}", wxId);
            setRo.setResult(LoginPswdSetDic.HAVE_SET);
            setRo.setMsg("您已设置过登录密码");
            return setRo;
        }
        String salt = RandomEx.random1(6);
        if (userMo.getSalt() != null && !userMo.getSalt().equals("") && !userMo.getSalt().equals("")) {
            salt = userMo.getSalt();
        }
        newLoginPswd = saltPswd(newLoginPswd, salt);
        _log.info("设置登录密码的参数为：{}", wxId + ", " + newLoginPswd + ", " + salt);
        final int setResult = _mapper.setLoginPswd(wxId, newLoginPswd, salt);
        _log.info("设置登录密码的返回值为：{}", setResult);
        if (setResult < 1) {
            _log.error("设置登录密码设置登录密码时出错，微信ID为：{}", wxId);
            setRo.setResult(LoginPswdSetDic.SET_ERROR);
            setRo.setMsg("设置失败");
            return setRo;
        }
        _log.info("微信设置登录密码成功，微信ID为：{}", wxId);
        setRo.setResult(LoginPswdSetDic.SUCCESS);
        setRo.setMsg("设置成功");
        return setRo;
    }

    /**
     * 微信修改登录密码 Title: changeLoginPassword Description:
     *
     * @param wxId
     * @param oldLoginPswd
     * @param newLoginPswd
     * @return
     * @date 2018年5月2日 下午1:21:06 1、判断参数是否为空 2、查询用户信息并判断用户是否存在 3、判断用户是否已设置登录密码
     *       4、判断输入的登录密码是否正确 5、修改登录密码
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public LoginPswdModifyRo changeLoginPassword(final String wxId, String oldLoginPswd, String newLoginPswd) {
        final LoginPswdModifyRo modifyRo = new LoginPswdModifyRo();
        if (wxId == null || wxId.equals("") || wxId.equals("null")) {
            _log.error("设置或修改登录密码时出现微信ID为空");
            modifyRo.setResult(LoginPswdModifyDic.NOT_LOGIN);
            modifyRo.setMsg("您未登录，请先登录");
            return modifyRo;
        }
        if (newLoginPswd == null || newLoginPswd.equals("") || newLoginPswd.equals("null")) {
            _log.error("修改登录密码时出现没有输入新的登录密码，微信ID为：{}", wxId);
            modifyRo.setResult(LoginPswdModifyDic.NEW_LOGINPSWD_NULL);
            modifyRo.setMsg("请输入新的登录密码");
            return modifyRo;
        }
        if (oldLoginPswd == null || oldLoginPswd.equals("") || oldLoginPswd.equals("null")) {
            _log.error("修改登录密码时出现没有输入旧的登录密码，微信ID为：{}", wxId);
            modifyRo.setResult(LoginPswdModifyDic.OLD_LOGINPSWD_NULL);
            modifyRo.setMsg("请输入旧的登录密码");
            return modifyRo;
        }
        _log.info("修改登录密码查询用户信息的参数为：{}", wxId);
        // 查询用户信息
        final SucUserMo userMo = _mapper.selectUserInfoByWx(wxId);
        _log.info("修改登录密码查询用户信息的返回值为：{}", userMo.toString());
        if (userMo.getWxId() == null || userMo.getWxId().equals("") || userMo.getWxId().equals("null")) {
            _log.error("修改登录密码查询用户信息时出现用户信息为空，微信ID为：{}", wxId);
            modifyRo.setResult(LoginPswdModifyDic.NOT_FOUND_USER);
            modifyRo.setMsg("找不到用户信息");
            return modifyRo;
        }
        String salt = "";
        final String oriLoginPswd = userMo.getLoginPswd();
        if (oriLoginPswd != null && !oriLoginPswd.equals("") && !oriLoginPswd.equals("null")) {
            oldLoginPswd = saltPswd(oldLoginPswd, userMo.getSalt());
            if (!oriLoginPswd.equals(oldLoginPswd)) {
                _log.error("修改登录密码时出现输入的旧密码不等于原来的旧密码，微信ID为：{}", wxId);
                modifyRo.setResult(LoginPswdModifyDic.OLD_LOGINPSWD_ERROR);
                modifyRo.setMsg("输入的旧密码不正确");
                return modifyRo;
            } else {
                salt = userMo.getSalt();
                newLoginPswd = saltPswd(newLoginPswd, salt);
                _log.info("修改登录密码的参数为：{}", wxId + ", " + newLoginPswd);
                final int updateResult = _mapper.updateloginPswd(wxId, newLoginPswd);
                _log.info("修改登录密码的返回值为：{}", updateResult);
                if (updateResult < 1) {
                    _log.error("修改登录密码根据微信ID修改密码时出现错误，微信ID为：{}", wxId);
                    modifyRo.setResult(LoginPswdModifyDic.NOT_SET_LOGINPSWD);
                    modifyRo.setMsg("修改失败");
                    throw new RuntimeException("修改失败");
                }
            }
        } else {
            _log.error("微信修改登录密码时出现没有设置登录密码，微信ID为：{}", wxId);
            modifyRo.setResult(LoginPswdModifyDic.MODIFY_ERROR);
            modifyRo.setMsg("您未设置登录密码，请先设置后再试");
            return modifyRo;
        }
        _log.info("微信修改登录密码成功，微信ID为：{}", wxId);
        modifyRo.setResult(LoginPswdModifyDic.SUCCESS);
        modifyRo.setMsg("修改成功");
        return modifyRo;
    }

    /**
     * 微信设置支付密码 Title: setLoginPassword Description:
     *
     * @param wxId
     * @param newPayPswd
     * @return
     * @date 2018年5月2日 下午12:57:25 1、判断参数是否为空 2、查询用户信息并判断该用户是否存在 3、判断支付密码是否为空
     *       4、添加支付密码
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PayPswdSetRo setPayPassword(final String wxId, String newPayPswd) {
        final PayPswdSetRo setRo = new PayPswdSetRo();
        if (wxId == null || wxId.equals("") || wxId.equals("null")) {
            _log.error("设置支付密码时出现微信ID为空");
            setRo.setResult(PayPswdSetDic.NOT_LOGIN);
            setRo.setMsg("您未登录，请先登录");
            return setRo;
        }
        if (newPayPswd == null || newPayPswd.equals("") || newPayPswd.equals("null")) {
            _log.error("设置支付密码时出现没有输入新的支付密码，微信ID为：{}", wxId);
            setRo.setResult(PayPswdSetDic.NEW_PAYPSWD_NULL);
            setRo.setMsg("请输入支付密码");
            return setRo;
        }
        _log.info("设置支付密码查询用户信息的参数为：{}", wxId);
        // 查询用户信息
        final SucUserMo userMo = _mapper.selectUserInfoByWx(wxId);
        _log.info("设置支付密码查询用户信息的返回值为：{}", userMo.toString());
        if (userMo.getWxId() == null || userMo.getWxId().equals("") || userMo.getWxId().equals("null")) {
            _log.error("设置支付密码查询用户信息时出现用户信息为空，微信ID为：{}", wxId);
            setRo.setResult(PayPswdSetDic.NOT_FOUND_USER);
            setRo.setMsg("找不到用户信息");
            return setRo;
        }
        if (userMo.getPayPswd() != null && !userMo.getPayPswd().equals("") && !userMo.getPayPswd().equals("null")) {
            _log.error("微信设置支付密码时出现该用户微信用户已设置了支付密码，微信ID为：{}", wxId);
            setRo.setResult(PayPswdSetDic.HAVE_SET);
            setRo.setMsg("您已设置过支付密码");
            return setRo;
        }
        String salt = RandomEx.random1(6);
        if (userMo.getSalt() != null && !userMo.getSalt().equals("") && !userMo.getSalt().equals("")) {
            salt = userMo.getSalt();
        }
        newPayPswd = saltPswd(newPayPswd, salt);
        _log.info("设置支付密码的参数为：{}", wxId + ", " + newPayPswd + ", " + salt);
        final int setResult = _mapper.setPayPswd(wxId, newPayPswd, salt);
        _log.info("设置支付密码的返回值为：{}", setResult);
        if (setResult < 1) {
            _log.error("设置支付密码设置支付密码时出错，微信ID为：{}", wxId);
            setRo.setResult(PayPswdSetDic.SET_ERROR);
            setRo.setMsg("设置失败");
            return setRo;
        }
        _log.info("微信设置支付密码成功，微信ID为：{}", wxId);
        setRo.setResult(PayPswdSetDic.SUCCESS);
        setRo.setMsg("设置成功");
        return setRo;
    }

    /**
     * 微信修改支付密码 Title: changePayPassword Description:
     *
     * @param wxId
     * @param oldPayPswd
     * @param newPayPswd
     * @return
     * @date 2018年5月2日 下午1:21:06 1、判断参数是否为空 2、查询用户信息并判断用户是否存在 3、判断用户是否已设置支付密码
     *       4、判断输入的支付密码是否正确 5、修改支付密码
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PayPswdModifyRo changePayPassword(final String wxId, String oldPayPswd, String newPayPswd) {
        final PayPswdModifyRo modifyRo = new PayPswdModifyRo();
        if (wxId == null || wxId.equals("") || wxId.equals("null")) {
            _log.error("设置或修改支付密码时出现微信ID为空");
            modifyRo.setResult(PayPswdModifyDic.NOT_LOGIN);
            modifyRo.setMsg("您未登录，请先登录");
            return modifyRo;
        }
        if (newPayPswd == null || newPayPswd.equals("") || newPayPswd.equals("null")) {
            _log.error("修改支付密码时出现没有输入新的支付密码，微信ID为：{}", wxId);
            modifyRo.setResult(PayPswdModifyDic.NEW_PAYPSWD_NULL);
            modifyRo.setMsg("请输入新的支付密码");
            return modifyRo;
        }
        if (oldPayPswd == null || oldPayPswd.equals("") || oldPayPswd.equals("null")) {
            _log.error("修改支付密码时出现没有输入旧的支付密码，微信ID为：{}", wxId);
            modifyRo.setResult(PayPswdModifyDic.OLD_PAYPSWD_NULL);
            modifyRo.setMsg("请输入旧的支付密码");
            return modifyRo;
        }
        _log.info("修改支付密码查询用户信息的参数为：{}", wxId);
        // 查询用户信息
        final SucUserMo userMo = _mapper.selectUserInfoByWx(wxId);
        _log.info("修改支付密码查询用户信息的返回值为：{}", userMo.toString());
        if (userMo.getWxId() == null || userMo.getWxId().equals("") || userMo.getWxId().equals("null")) {
            _log.error("修改支付密码查询用户信息时出现用户信息为空，微信ID为：{}", wxId);
            modifyRo.setResult(PayPswdModifyDic.NOT_FOUND_USER);
            modifyRo.setMsg("找不到用户信息");
            return modifyRo;
        }
        String salt = "";
        final String oriPayPswd = userMo.getPayPswd();
        if (oriPayPswd != null && !oriPayPswd.equals("") && !oriPayPswd.equals("null")) {
            oldPayPswd = saltPswd(oldPayPswd, userMo.getSalt());
            if (!oriPayPswd.equals(oldPayPswd)) {
                _log.error("修改支付密码时出现输入的旧密码不等于原来的旧密码，微信ID为：{}", wxId);
                modifyRo.setResult(PayPswdModifyDic.OLD_PAYPSWD_ERROR);
                modifyRo.setMsg("输入的旧密码不正确");
                return modifyRo;
            } else {
                salt = userMo.getSalt();
                newPayPswd = saltPswd(newPayPswd, salt);
                _log.info("修改支付密码的参数为：{}", wxId + ", " + newPayPswd);
                final int updateResult = _mapper.updateloginPswd(wxId, newPayPswd);
                _log.info("修改支付密码的返回值为：{}", updateResult);
                if (updateResult < 1) {
                    _log.error("修改支付密码根据微信ID修改密码时出现错误，微信ID为：{}", wxId);
                    modifyRo.setResult(PayPswdModifyDic.NOT_SET_PAYPSWD);
                    modifyRo.setMsg("修改失败");
                    throw new RuntimeException("修改失败");
                }
            }
        } else {
            _log.error("微信修改支付密码时出现没有设置支付密码，微信ID为：{}", wxId);
            modifyRo.setResult(PayPswdModifyDic.MODIFY_ERROR);
            modifyRo.setMsg("您未设置支付密码，请先设置后再试");
            return modifyRo;
        }
        _log.info("微信修改支付密码成功，微信ID为：{}", wxId);
        modifyRo.setResult(PayPswdModifyDic.SUCCESS);
        modifyRo.setMsg("修改成功");
        return modifyRo;
    }

    /**
     * 通过微信ID设置登录名称 Title: setLoginName Description:
     *
     * @param wxId
     * @param loginName
     * @return
     * @date 2018年5月3日 下午5:05:38 1、判断参数是否正确 2、根据微信ID判断该用户是否存在 3、判断登录名称是否已存在
     *       4、设置或修改登录名称
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SetLoginNameRo setLoginName(final String wxId, final String loginName) {
        final SetLoginNameRo setRo = new SetLoginNameRo();
        if (wxId == null || wxId.equals("") || wxId.equals("null")) {
            _log.error("通过微信ID设置登录名称时出现微信ID");
            setRo.setResult(SetLoginNameDic.NOT_LOGIN);
            setRo.setMsg("您未登录，请先登录");
            return setRo;
        }
        if (loginName == null || loginName.equals("null") || loginName.equals("")) {
            _log.error("通过微信ID设置登录名称时出现登录名称为空，微信ID为：{}", wxId);
            setRo.setResult(SetLoginNameDic.NEW_LOGINNAME_NULL);
            setRo.setMsg("请输入登录名称");
            return setRo;
        }
        SucUserMo userMo = new SucUserMo();
        userMo.setWxId(wxId);
        _log.info("通过微信ID设置登录名称根据微信判断用户是否存在的参数为：{}", wxId);
        final Boolean userFlag = _mapper.existSelective(userMo);
        _log.info("通过微信ID设置登录名称根据微信判断用户是否存在的返回值为：{}", userFlag);
        if (!userFlag) {
            _log.error("通过微信ID设置登录名称根据微信判断用户是否存在时出现该用户不存在，微信ID为：{}", wxId);
            setRo.setResult(SetLoginNameDic.NOT_REGISTER);
            setRo.setMsg("您未注册，请先注册");
            return setRo;
        }
        userMo = new SucUserMo();
        userMo.setLoginName(loginName);
        _log.info("通过微信ID设置登录名称根据登录名称判断该名称是否已存在的参数为：{}", loginName);
        final Boolean loginNameFlag = _mapper.existSelective(userMo);
        _log.info("通过微信ID设置登录名称根据登录名称判断该名称是否已存在的返回值为：{}", loginNameFlag);
        if (loginNameFlag) {
            _log.error("根据微信ID设置登录名称时出现该登录名称已存在，微信ID为：{}", wxId);
            setRo.setResult(SetLoginNameDic.LOGNAME_ALREADY_EXIST);
            setRo.setMsg("该名称已存在");
            return setRo;
        }
        _log.info("根据微信ID设置登录名称的参数为：{}，{}", wxId, loginName);
        final int setResult = _mapper.setLoginName(wxId, loginName);
        _log.info("根据微信ID设置登录名称的返回值为：{}", setResult);
        if (setResult != 1) {
            _log.error("根据微信ID设置登录名称时出现设置失败，微信ID为：{}", wxId);
            setRo.setResult(SetLoginNameDic.SET_ERROR);
            setRo.setMsg("设置失败");
            return setRo;
        }
        _log.info("根据微信ID设置登录名称成功");
        setRo.setResult(SetLoginNameDic.SUCCESS);
        setRo.setMsg("设置成功");
        return setRo;
    }

    /**
     * 根据微信ID获取用户登录名称 Title: selectLoginNameByWx Description:
     *
     * @param wxId
     * @return
     * @date 2018年5月4日 上午9:04:08
     */
    @Override
    public GetLoginNameRo getLoginNameByWx(final String wxId) {
        final GetLoginNameRo ro = new GetLoginNameRo();
        final String loginName = _mapper.selectLoginNameByWx(wxId);
        _log.info("用户登录名称是: {}", loginName);
        if (StringUtils.isBlank(loginName)) {
            ro.setResult(GetLoginNameDic.FAIL);
            ro.setMsg("找不到用户");
        } else {
            ro.setResult(GetLoginNameDic.SUCCESS);
            ro.setMsg("获取成功");
            ro.setLoginName(loginName);
        }
        return ro;
    }

    /**
     * 修改用户信息
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo modifyEx(final SucUserMo mo) {
        final SucUserRo ro = new SucUserRo();
        _log.info("修改用户信息的参数为：{}", mo);
        final int updateByPrimaryKeySelectiveResult = _mapper.updateByPrimaryKeySelective(mo);
        _log.info("修改用户信息的返回值为：{}", updateByPrimaryKeySelectiveResult);
        if (updateByPrimaryKeySelectiveResult != 1) {
            _log.error("修改用户信息出错，用户ｉｄ为：{}", mo.getId());
            ro.setResult(-1);
            ro.setMsg("修改失败");
            return ro;
        }
        _log.info("修改用户信息成功，用户ｉｄ为: {}", mo.getId());
        ro.setResult(1);
        ro.setMsg("修改成功");
        return ro;
    }

    /**
     * 设置用户登录密码
     */
    @Override
    public SucUserRo setLoginPw(final SucUserMo mo) {
        final SucUserRo ro = new SucUserRo();
        _log.info("加密前修改用户登录密码的参数为：{}", mo);
        final SucUserMo userMo = new SucUserMo();
        final String salt = RandomEx.random1(6);
        userMo.setId(mo.getId());
        userMo.setSalt(salt);
        userMo.setLoginPswd(saltPswd(mo.getLoginPswd(), salt));
        userMo.setPayPswd(mo.getLoginPswd());
        _log.info("加密后修改用户登录密码的参数为：{}", userMo);
        final int result = _mapper.setLoginPw(userMo);
        _log.info("修改用户登录密码的返回值为：{}", result);
        if (result != 1) {
            _log.error("修改用户登录密码出错，用户ｉｄ为：{}", mo.getId());
            ro.setResult(-1);
            ro.setMsg("修改失败");
            return ro;
        }
        _log.info("修改用户登录密码成功，用户ｉｄ为: {}", mo.getId());
        ro.setResult(1);
        ro.setMsg("修改成功");
        return ro;
    }

    /**
     * 禁用或者解锁用户
     *
     * @param id
     * @param isLock
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo enable(final Long id, final Boolean isLock) {
        _log.info("禁用或解锁用户的参数为：{}，{}", id, isLock);
        final SucUserRo ro = new SucUserRo();
        final int lockOrUnlockByIdResult = _mapper.lockOrUnlockById(id, isLock);
        _log.info("禁用或解锁用户的返回值为：{}", lockOrUnlockByIdResult);
        if (lockOrUnlockByIdResult != 1) {
            _log.error("禁用或解锁用户失败，用户id为：{}", id);
            ro.setResult(-1);
            ro.setMsg("设置启用/禁用失败，找不到要启用/禁用的记录");
            return ro;
        }
        ro.setResult(1);
        ro.setMsg("设置启用/禁用成功");
        return ro;
    }

    /**
     * 解除登录密码
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo removeLoginPassWord(final Long id) {
        _log.info("解除用户登录密码的id为：{}", id);
        final SucUserRo ro = new SucUserRo();
        final int removeLoginPassWordResult = _mapper.removeLoginPassWord(id);
        _log.info("解除登录密码的返回值为：{}", removeLoginPassWordResult);
        if (removeLoginPassWordResult != 1) {
            _log.error("解除登录密码出错，用户id为：{}", id);
            ro.setResult(-1);
            ro.setMsg("解除失败");
            return ro;
        }
        _log.info("解除登录密码成功，用户id为：{}", id);
        ro.setResult(1);
        ro.setMsg("解除成功");
        return ro;
    }

    /**
     * 解除支付密码
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo removePayPassWord(final Long id) {
        _log.info("解除用户支付密码的id为：{}", id);
        final SucUserRo ro = new SucUserRo();
        final int removePayPassWordResult = _mapper.removePayPassWord(id);
        _log.info("解除支付密码的返回值为：{}", removePayPassWordResult);
        if (removePayPassWordResult != 1) {
            _log.error("解除支付密码出错，用户id为：{}", id);
            ro.setResult(-1);
            ro.setMsg("解除失败");
            return ro;
        }
        _log.info("解除支付密码成功，用户id为：{}", id);
        ro.setResult(1);
        ro.setMsg("解除成功");
        return ro;
    }

    /**
     * 解绑微信
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo unbindWeChat(final Long id) {
        _log.info("解绑微信的id为：{}", id);
        final SucUserRo ro = new SucUserRo();
        final int unbindWeChatResult = _mapper.unbindWeChat(id);
        _log.info("解绑微信的返回值为：{}", unbindWeChatResult);
        if (unbindWeChatResult != 1) {
            _log.error("解绑微信出错，用户id为：{}", id);
            ro.setResult(-1);
            ro.setMsg("解除失败");
            return ro;
        }
        _log.info("解绑微信成功，用户id为：{}", id);
        ro.setResult(1);
        ro.setMsg("解除成功");
        return ro;
    }

    /**
     * 解绑QQ
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo unbindQQ(final Long id) {
        _log.info("解绑QQ的id为：{}", id);
        final SucUserRo ro = new SucUserRo();
        final int unbindQQResult = _mapper.unbindQQ(id);
        _log.info("解绑QQ的返回值为：{}", unbindQQResult);
        if (unbindQQResult != 1) {
            _log.error("解绑QQ出错，用户id为：{}", id);
            ro.setResult(-1);
            ro.setMsg("解除失败");
            return ro;
        }
        _log.info("解绑QQ成功，用户id为：{}", id);
        ro.setResult(1);
        ro.setMsg("解除成功");
        return ro;
    }

    /**
     * 多条件同时查询
     *
     * @param keys
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
	public PageInfo<UserPointRo> listEx(final String keys, final int pageNum, final int pageSize) {
		_log.info("list: qo-{}; pageNum-{}; pageSize-{}", keys, pageNum, pageSize);
		PageInfo<UserPointRo> result = PageHelper.startPage(pageNum, pageSize)
				.doSelectPageInfo(() -> _mapper.listUserInformation(keys));
		List<UserPointRo> list=result.getList();
		for(UserPointRo ro:list) {
			_log.info("获取当前账户参数： ro:-{}",ro);
			PntAccountMo pnt=pntAccountSvc.getById(ro.getId());
			_log.info("获取当前账户总积分返回的结果： PntAccountMo:-{}",pnt);
				ro.setPoint(pnt.getPoint());
				ro.setModifiedTimestamp(pnt.getModifiedTimestamp());
		}
		return result;
	}

    /**
     * 根据用户id查询用户分页信息
     *
     * @param pageNum
     * @param pageSize
     * @param ids
     * @return
     */
    @Override
    public PageInfo<SucUserMo> listUserByIds(final int pageNum, final int pageSize, final String ids) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> _mapper.selectByIds(ids));
    }

    /**
     * 添加用户组织
     *
     * @param id
     * @param orgId
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo addUserOrg(final Long id, final Long orgId) {
        final SucUserRo ro = new SucUserRo();
        _log.info("添加用戶组织的参数为：{}，{}", id, orgId);
        final SucUserMo mo = new SucUserMo();
        mo.setId(id);
        mo.setOrgId(orgId);
        // 根据用户id和组织id查询用户是否已在这个组织
        _log.info("根据用户id和组织id查询用户是否已添加该组织的参数为：{}", mo);
        final boolean existSelectiveCount = _mapper.existSelective(mo);
        if (existSelectiveCount) {
            _log.info("根据用户id和组织id查询用户是否已添加该组织时发现，该用户已添加该组织，用户id为：{}", id);
            ro.setResult((byte) -1);
            ro.setMsg("该用户已是该组织的成员");
            return ro;
        }
        _log.info("添加用户组织的参数为：{}, {}", id, orgId);
        final int insertOrgByIdResult = _mapper.insertOrgById(id, orgId);
        _log.info("添加用户组织的返回值为、：{}", insertOrgByIdResult);
        if (insertOrgByIdResult != 1) {
            _log.error("添加用户组织时出现错误，用户id为：{}", id);
            ro.setResult((byte) -1);
            ro.setMsg("添加失败");
            return ro;
        }
        _log.info("添加用户组织成功，用户id为：{}", id);
        ro.setResult((byte) 1);
        ro.setMsg("添加成功");
        return ro;
    }

    /**
     * 删除用户组织
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucUserRo delUserOrgById(final Long id) {
        _log.info("删除用户组织的参数为：{}", id);
        final SucUserRo ro = new SucUserRo();
        final int delUserOrgByIdResult = _mapper.delUserOrgById(id);
        _log.info("删除用户组织的返回值为：{}", delUserOrgByIdResult);
        if (delUserOrgByIdResult != 1) {
            _log.info("删除用户组织失败，用户id为：{}", id);
            ro.setResult((byte) -1);
            ro.setMsg("删除失败");
            return ro;
        }
        _log.info("删除用户组织成功，用户id为：{}", id);
        ro.setResult((byte) 1);
        ro.setMsg("删除成功");
        return ro;
    }

    /**
     * 获取当前用户信息
     */
    @Override
    public CurrentUserRo getCurrentUser(final Long userId) {
        _log.info("获取当前用户信息: {}", userId);
        final CurrentUserRo ro = new CurrentUserRo();
        final SucUserMo userMo = _mapper.selectByPrimaryKey(userId);
        ro.setUserId(userMo.getId());
        ro.setOrgId(userMo.getOrgId());
        // 判断应该返回的昵称
        if (!StringUtils.isBlank(userMo.getNickname())) {
            ro.setNickname(userMo.getNickname());
        } else if (!StringUtils.isBlank(userMo.getWxNickname())) {
            ro.setNickname(userMo.getWxNickname());
        } else if (!StringUtils.isBlank(userMo.getQqNickname())) {
            ro.setNickname(userMo.getQqNickname());
        }
        // 判断应该返回的头像
        if (!StringUtils.isBlank(userMo.getFace())) {
            ro.setFace(userMo.getFace());
        } else if (!StringUtils.isBlank(userMo.getWxFace())) {
            ro.setFace(userMo.getWxFace());
        } else if (!StringUtils.isBlank(userMo.getQqFace())) {
            ro.setFace(userMo.getQqFace());
        }
        return ro;
    }

    /**
     * 模糊查询关键字且在指定多个用户ID范围内的用户列表
     * 
     * @param keys
     *            模糊查询用户的关键字
     * @param userIds
     *            用户ID列表的字符串，用逗号隔开
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @Override
    public PageInfo<SucUserDetailRo> listByKeysAndUserIds(final String keys, final String userIds, final Integer pageNum, final Integer pageSize) {
        _log.info("模糊查询关键字且在指定多个用户ID范围内的用户列表：keys={}, userIds={}", keys, userIds);
        if (StringUtils.isBlank(keys)) {
            return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> _mapper.selectByUserIds(userIds));
        } else {
            return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> _mapper.selectByKeysAndUserIds(keys, userIds));
        }
    }

    /**
     * 模糊查询关键字且排除指定多个用户ID外的用户列表
     * 
     * @param keys
     *            模糊查询用户的关键字
     * @param userIds
     *            用户ID列表的字符串，用逗号隔开
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @Override
    public PageInfo<SucUserDetailRo> listByKeysAndNotUserIds(final String keys, final String userIds, final Integer pageNum, final Integer pageSize) {
        _log.info("模糊查询关键字且排除指定多个用户ID外的用户列表：keys={}, userIds={}", keys, userIds);
        if (StringUtils.isBlank(keys)) {
            if (StringUtils.isBlank(userIds)) {
                return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> _mapper.selectAll());
            } else {
                return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> _mapper.selectByNotUserIds(userIds));
            }
        } else {
            if (StringUtils.isBlank(userIds)) {
                return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> _mapper.selectByKeys(keys));
            } else {
                return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> _mapper.selectByKeysAndNotUserIds(keys, userIds));
            }
        }
    }

}
