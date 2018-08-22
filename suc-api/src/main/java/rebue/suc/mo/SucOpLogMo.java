package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户操作日志
 *
 * 数据库表: SUC_OP_LOG
 *
 * 自动生成，如需修改，请删除本行 @mbg.generated
 */
@JsonInclude(Include.NON_NULL)
public class SucOpLogMo implements Serializable {

    /**
     *    数据库字段: SUC_OP_LOG.SYS_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String sysId;

    /**
     *    数据库字段: SUC_OP_LOG.SYS_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    数据库字段: SUC_OP_LOG.SYS_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    用户操作日志ID
     *
     *    数据库字段: SUC_OP_LOG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long id;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_OP_LOG.USER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long userId;

    /**
     *    操作类型
     *                1:修改登录密码;
     *                2:修改支付密码;
     *                3:绑定QQ登录;
     *                4:绑定微信登录;
     *
     *    数据库字段: SUC_OP_LOG.OP_TYPE
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Byte opType;

    /**
     *    操作时间
     *
     *    数据库字段: SUC_OP_LOG.OP_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date opTime;

    /**
     *    操作详情
     *
     *    数据库字段: SUC_OP_LOG.OP_DETAIL
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String opDetail;

    /**
     *    应用ID
     *                标记是哪个应用系统来注册的编码，要与登录应用ID意义一致
     *
     *    数据库字段: SUC_OP_LOG.APP_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Byte appId;

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_OP_LOG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String userAgent;

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_OP_LOG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String mac;

    /**
     *    IP地址
     *
     *    数据库字段: SUC_OP_LOG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String ip;

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户操作日志ID
     *
     *    数据库字段: SUC_OP_LOG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户操作日志ID
     *
     *    数据库字段: SUC_OP_LOG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_OP_LOG.USER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_OP_LOG.USER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *    操作类型
     *                1:修改登录密码;
     *                2:修改支付密码;
     *                3:绑定QQ登录;
     *                4:绑定微信登录;
     *
     *    数据库字段: SUC_OP_LOG.OP_TYPE
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Byte getOpType() {
        return opType;
    }

    /**
     *    操作类型
     *                1:修改登录密码;
     *                2:修改支付密码;
     *                3:绑定QQ登录;
     *                4:绑定微信登录;
     *
     *    数据库字段: SUC_OP_LOG.OP_TYPE
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setOpType(Byte opType) {
        this.opType = opType;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_OP_LOG.OP_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_OP_LOG.OP_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     *    操作详情
     *
     *    数据库字段: SUC_OP_LOG.OP_DETAIL
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getOpDetail() {
        return opDetail;
    }

    /**
     *    操作详情
     *
     *    数据库字段: SUC_OP_LOG.OP_DETAIL
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setOpDetail(String opDetail) {
        this.opDetail = opDetail;
    }

    /**
     *    应用ID
     *                标记是哪个应用系统来注册的编码，要与登录应用ID意义一致
     *
     *    数据库字段: SUC_OP_LOG.APP_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Byte getAppId() {
        return appId;
    }

    /**
     *    应用ID
     *                标记是哪个应用系统来注册的编码，要与登录应用ID意义一致
     *
     *    数据库字段: SUC_OP_LOG.APP_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setAppId(Byte appId) {
        this.appId = appId;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_OP_LOG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_OP_LOG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_OP_LOG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getMac() {
        return mac;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_OP_LOG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_OP_LOG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_OP_LOG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip;
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
        sb.append(", opType=").append(opType);
        sb.append(", opTime=").append(opTime);
        sb.append(", opDetail=").append(opDetail);
        sb.append(", appId=").append(appId);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", mac=").append(mac);
        sb.append(", ip=").append(ip);
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
        SucOpLogMo other = (SucOpLogMo) that;
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
