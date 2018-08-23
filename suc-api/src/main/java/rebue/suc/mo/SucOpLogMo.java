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
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucOpLogMo implements Serializable {

    /**
     *    用户操作日志ID
     *
     *    数据库字段: SUC_OP_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_OP_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
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
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte opType;

    /**
     *    操作详情
     *
     *    数据库字段: SUC_OP_LOG.OP_DETAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String opDetail;

    /**
     *    操作时间
     *
     *    数据库字段: SUC_OP_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date opTime;

    /**
     *    应用ID
     *                标记是哪个应用系统来注册的编码，要与登录应用ID意义一致
     *
     *    数据库字段: SUC_OP_LOG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte appId;

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_OP_LOG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String userAgent;

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_OP_LOG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String mac;

    /**
     *    系统id
     *
     *    数据库字段: SUC_OP_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String sysId;

    /**
     *    IP地址
     *
     *    数据库字段: SUC_OP_LOG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String ip;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户操作日志ID
     *
     *    数据库字段: SUC_OP_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户操作日志ID
     *
     *    数据库字段: SUC_OP_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_OP_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_OP_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
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
     *    @mbg.generated 自动生成，如需修改，请删除本行
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
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpType(Byte opType) {
        this.opType = opType;
    }

    /**
     *    操作详情
     *
     *    数据库字段: SUC_OP_LOG.OP_DETAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getOpDetail() {
        return opDetail;
    }

    /**
     *    操作详情
     *
     *    数据库字段: SUC_OP_LOG.OP_DETAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpDetail(String opDetail) {
        this.opDetail = opDetail;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_OP_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     *    操作时间
     *
     *    数据库字段: SUC_OP_LOG.OP_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     *    应用ID
     *                标记是哪个应用系统来注册的编码，要与登录应用ID意义一致
     *
     *    数据库字段: SUC_OP_LOG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
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
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAppId(Byte appId) {
        this.appId = appId;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_OP_LOG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_OP_LOG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_OP_LOG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMac() {
        return mac;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_OP_LOG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     *    系统id
     *
     *    数据库字段: SUC_OP_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    系统id
     *
     *    数据库字段: SUC_OP_LOG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_OP_LOG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIp() {
        return ip;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_OP_LOG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIp(String ip) {
        this.ip = ip;
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
        sb.append(", opType=").append(opType);
        sb.append(", opDetail=").append(opDetail);
        sb.append(", opTime=").append(opTime);
        sb.append(", appId=").append(appId);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", mac=").append(mac);
        sb.append(", sysId=").append(sysId);
        sb.append(", ip=").append(ip);
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
        SucOpLogMo other = (SucOpLogMo) that;
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
