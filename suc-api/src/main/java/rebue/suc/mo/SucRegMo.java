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
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucRegMo implements Serializable {

    /**
     *    用户ID
     *
     *    数据库字段: SUC_REG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    注册时间
     *
     *    数据库字段: SUC_REG.REG_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date regTime;

    /**
     *    注册类型(与登录类型一致)
     *                        1: 用户登录名
     *                        2: 电子邮箱
     *                        3: 手机
     *                        4: QQ
     *                        5: 微信
     *
     *    数据库字段: SUC_REG.REG_TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte regType;

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_REG.PROMOTER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long promoterId;

    /**
     *    数据库字段: SUC_REG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte appId;

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_REG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String userAgent;

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_REG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String mac;

    /**
     *    IP地址
     *
     *    数据库字段: SUC_REG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String ip;

    /**
     *    数据库字段: SUC_REG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String sysId;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_REG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_REG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    注册时间
     *
     *    数据库字段: SUC_REG.REG_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     *    注册时间
     *
     *    数据库字段: SUC_REG.REG_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     *    注册类型(与登录类型一致)
     *                        1: 用户登录名
     *                        2: 电子邮箱
     *                        3: 手机
     *                        4: QQ
     *                        5: 微信
     *
     *    数据库字段: SUC_REG.REG_TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getRegType() {
        return regType;
    }

    /**
     *    注册类型(与登录类型一致)
     *                        1: 用户登录名
     *                        2: 电子邮箱
     *                        3: 手机
     *                        4: QQ
     *                        5: 微信
     *
     *    数据库字段: SUC_REG.REG_TYPE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRegType(Byte regType) {
        this.regType = regType;
    }

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_REG.PROMOTER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getPromoterId() {
        return promoterId;
    }

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_REG.PROMOTER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPromoterId(Long promoterId) {
        this.promoterId = promoterId;
    }

    /**
     *    数据库字段: SUC_REG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Byte getAppId() {
        return appId;
    }

    /**
     *    数据库字段: SUC_REG.APP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAppId(Byte appId) {
        this.appId = appId;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_REG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     *    浏览器类型
     *
     *    数据库字段: SUC_REG.USER_AGENT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_REG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMac() {
        return mac;
    }

    /**
     *    MAC地址
     *
     *    数据库字段: SUC_REG.MAC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_REG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIp() {
        return ip;
    }

    /**
     *    IP地址
     *
     *    数据库字段: SUC_REG.IP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *    数据库字段: SUC_REG.SYS_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSysId() {
        return sysId;
    }

    /**
     *    数据库字段: SUC_REG.SYS_ID
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
        sb.append(", regTime=").append(regTime);
        sb.append(", regType=").append(regType);
        sb.append(", promoterId=").append(promoterId);
        sb.append(", appId=").append(appId);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", mac=").append(mac);
        sb.append(", ip=").append(ip);
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
        SucRegMo other = (SucRegMo) that;
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
