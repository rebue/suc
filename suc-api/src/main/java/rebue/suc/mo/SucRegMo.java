package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户注册
 *
 * 数据库表: SUC_REG
 *
 * 自动生成，如需修改，请删除本行 @mbg.generated
 */
@JsonInclude(Include.NON_NULL)
public class SucRegMo implements Serializable {

    /**
     *    数据库字段: SUC_REG.SYS_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String sysId;

    /**
     *    数据库字段: SUC_REG.SYS_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    数据库字段: SUC_REG.SYS_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_REG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long id;

    /**
     *    注册时间
     *
     *    数据库字段: SUC_REG.REG_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date regTime;

    /**
     *    注册类型(与登录类型一致)
     *                            1: 用户登录名
     *                            2: 电子邮箱
     *                            3: 手机
     *                            4: QQ
     *                            5: 微信
     *
     *    数据库字段: SUC_REG.REG_TYPE
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Byte regType;

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_REG.PROMOTER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long promoterId;

    /**
     *    应用ID
     *                标记是哪个应用系统来注册的编码，要与登录应用ID意义一致
     *
     *    数据库字段: SUC_REG.APP_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Byte appId;

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_REG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String userAgent;

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_REG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String mac;

    /**
     *    IP地址
     *
     *    数据库字段: SUC_REG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String ip;

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_REG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_REG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    注册时间
     *
     *    数据库字段: SUC_REG.REG_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     *    注册时间
     *
     *    数据库字段: SUC_REG.REG_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     *    注册类型(与登录类型一致)
     *                            1: 用户登录名
     *                            2: 电子邮箱
     *                            3: 手机
     *                            4: QQ
     *                            5: 微信
     *
     *    数据库字段: SUC_REG.REG_TYPE
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Byte getRegType() {
        return regType;
    }

    /**
     *    注册类型(与登录类型一致)
     *                            1: 用户登录名
     *                            2: 电子邮箱
     *                            3: 手机
     *                            4: QQ
     *                            5: 微信
     *
     *    数据库字段: SUC_REG.REG_TYPE
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setRegType(Byte regType) {
        this.regType = regType;
    }

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_REG.PROMOTER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getPromoterId() {
        return promoterId;
    }

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_REG.PROMOTER_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setPromoterId(Long promoterId) {
        this.promoterId = promoterId;
    }

    /**
     *    应用ID
     *                标记是哪个应用系统来注册的编码，要与登录应用ID意义一致
     *
     *    数据库字段: SUC_REG.APP_ID
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
     *    数据库字段: SUC_REG.APP_ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setAppId(Byte appId) {
        this.appId = appId;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_REG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_REG.USER_AGENT
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_REG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getMac() {
        return mac;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_REG.MAC
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_REG.IP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_REG.IP
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
        sb.append(", regTime=").append(regTime);
        sb.append(", regType=").append(regType);
        sb.append(", promoterId=").append(promoterId);
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
        SucRegMo other = (SucRegMo) that;
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
