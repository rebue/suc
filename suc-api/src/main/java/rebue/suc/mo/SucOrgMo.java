package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 公司/组织信息
 *
 * 数据库表: SUC_ORG
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucOrgMo implements Serializable {

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_ORG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    公司/组织名称
     *
     *    数据库字段: SUC_ORG.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String name;

    /**
     *    公司/组织备注
     *
     *    数据库字段: SUC_ORG.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String remark;

    /**
     *    创建时间戳
     *
     *    数据库字段: SUC_ORG.CREATE_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long createTimestamp;

    /**
     *    是否启用
     *
     *    数据库字段: SUC_ORG.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     *    联系方式
     *
     *    数据库字段: SUC_ORG.CONTACT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String contact;

    /**
     *    公司/组织简称
     *
     *    数据库字段: SUC_ORG.SHORT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String shortName;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_ORG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_ORG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    公司/组织名称
     *
     *    数据库字段: SUC_ORG.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     *    公司/组织名称
     *
     *    数据库字段: SUC_ORG.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    公司/组织备注
     *
     *    数据库字段: SUC_ORG.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     *    公司/组织备注
     *
     *    数据库字段: SUC_ORG.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     *    创建时间戳
     *
     *    数据库字段: SUC_ORG.CREATE_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     *    创建时间戳
     *
     *    数据库字段: SUC_ORG.CREATE_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     *    是否启用
     *
     *    数据库字段: SUC_ORG.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    是否启用
     *
     *    数据库字段: SUC_ORG.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    联系方式
     *
     *    数据库字段: SUC_ORG.CONTACT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getContact() {
        return contact;
    }

    /**
     *    联系方式
     *
     *    数据库字段: SUC_ORG.CONTACT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *    公司/组织简称
     *
     *    数据库字段: SUC_ORG.SHORT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *    公司/组织简称
     *
     *    数据库字段: SUC_ORG.SHORT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
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
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", contact=").append(contact);
        sb.append(", shortName=").append(shortName);
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
        SucOrgMo other = (SucOrgMo) that;
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
