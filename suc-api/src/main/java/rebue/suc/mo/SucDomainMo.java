package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 领域信息
 *
 * 数据库表: SUC_DOMAIN
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucDomainMo implements Serializable {

    /**
     *    领域ID
     *
     *    数据库字段: SUC_DOMAIN.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String id;

    /**
     *    领域名称
     *
     *    数据库字段: SUC_DOMAIN.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String name;

    /**
     *    领域备注
     *
     *    数据库字段: SUC_DOMAIN.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String remark;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    领域ID
     *
     *    数据库字段: SUC_DOMAIN.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getId() {
        return id;
    }

    /**
     *    领域ID
     *
     *    数据库字段: SUC_DOMAIN.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *    领域名称
     *
     *    数据库字段: SUC_DOMAIN.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     *    领域名称
     *
     *    数据库字段: SUC_DOMAIN.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    领域备注
     *
     *    数据库字段: SUC_DOMAIN.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRemark() {
        return remark;
    }

    /**
     *    领域备注
     *
     *    数据库字段: SUC_DOMAIN.REMARK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
        SucDomainMo other = (SucDomainMo) that;
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
