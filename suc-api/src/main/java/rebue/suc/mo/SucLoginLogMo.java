package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户登录日志
 *
 * 数据库表: SUC_LOGIN_LOG
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucLoginLogMo implements Serializable {

    /**
     *    用户登录日志ID
     *
     *    数据库字段: SUC_LOGIN_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long userId;

    /**
     *    操作时间
     *
     *    数据库字段: SUC_LOGIN_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date opTime;

    /**
     *    登录类型(与注册类型一致)
     *                1: 用户登录名
     *                2: 电子邮箱
     *                3: 手机
     *                4: QQ
     *                5: 微信
     *
     *    数据库字段: SUC_LOGIN_LOG.LOGIN_TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte loginType;

    /**
     *    应用ID
     *                标记是通过哪个应用系统登录的编码，要与注册的应用ID意义一致
     *
     *    数据库字段: SUC_LOGIN_LOG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte appId;

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String userAgent;

    /**
     *    IP地址
     *
     *    数据库字段: SUC_LOGIN_LOG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String ip;

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_LOGIN_LOG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String mac;

    /**
     *    系统id
     *
     *    数据库字段: SUC_LOGIN_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String sysId;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户登录日志ID
     *
     *    数据库字段: SUC_LOGIN_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户登录日志ID
     *
     *    数据库字段: SUC_LOGIN_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_LOGIN_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_LOGIN_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     *    登录类型(与注册类型一致)
     *                1: 用户登录名
     *                2: 电子邮箱
     *                3: 手机
     *                4: QQ
     *                5: 微信
     *
     *    数据库字段: SUC_LOGIN_LOG.LOGIN_TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getLoginType() {
        return loginType;
    }

    /**
     *    登录类型(与注册类型一致)
     *                1: 用户登录名
     *                2: 电子邮箱
     *                3: 手机
     *                4: QQ
     *                5: 微信
     *
     *    数据库字段: SUC_LOGIN_LOG.LOGIN_TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginType(Byte loginType) {
        this.loginType = loginType;
    }

    /**
     *    应用ID
     *                标记是通过哪个应用系统登录的编码，要与注册的应用ID意义一致
     *
     *    数据库字段: SUC_LOGIN_LOG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getAppId() {
        return appId;
    }

    /**
     *    应用ID
     *                标记是通过哪个应用系统登录的编码，要与注册的应用ID意义一致
     *
     *    数据库字段: SUC_LOGIN_LOG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAppId(Byte appId) {
        this.appId = appId;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_LOGIN_LOG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIp() {
        return ip;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_LOGIN_LOG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_LOGIN_LOG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMac() {
        return mac;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_LOGIN_LOG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     *    系统id
     *
     *    数据库字段: SUC_LOGIN_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    系统id
     *
     *    数据库字段: SUC_LOGIN_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", opTime=").append(opTime);
        sb.append(", loginType=").append(loginType);
        sb.append(", appId=").append(appId);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", ip=").append(ip);
        sb.append(", mac=").append(mac);
        sb.append(", sysId=").append(sysId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SucLoginLogMo other = (SucLoginLogMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
