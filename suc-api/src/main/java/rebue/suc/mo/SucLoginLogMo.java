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
 * 自动生成，如需修改，请删除本行 @mbg.generated
 */
@JsonInclude(Include.NON_NULL)
public class SucLoginLogMo implements Serializable {

    /**
     *    用户登录日志ID
     *
     *    数据库字段: SUC_LOGIN_LOG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long id;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long userId;

    /**
     *    操作时间
     *
     *    数据库字段: SUC_LOGIN_LOG.OP_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
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
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Byte loginType;

    /**
     *    应用ID
     *                标记是通过哪个应用系统登录的编码，要与注册的应用ID意义一致
     *
     *    数据库字段: SUC_LOGIN_LOG.APP_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Byte appId;

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String userAgent;

    /**
     *    IP地址
     *
     *    数据库字段: SUC_LOGIN_LOG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String ip;

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_LOGIN_LOG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String mac;

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户登录日志ID
     *
     *    数据库字段: SUC_LOGIN_LOG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户登录日志ID
     *
     *    数据库字段: SUC_LOGIN_LOG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_LOGIN_LOG.OP_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_LOGIN_LOG.OP_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
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
     *    自动生成，如需修改，请删除本行 @mbg.generated
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
     *    自动生成，如需修改，请删除本行 @mbg.generated
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
     *    自动生成，如需修改，请删除本行 @mbg.generated
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
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setAppId(Byte appId) {
        this.appId = appId;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_LOGIN_LOG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_LOGIN_LOG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_LOGIN_LOG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_LOGIN_LOG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getMac() {
        return mac;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_LOGIN_LOG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
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
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
