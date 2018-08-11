package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 用户名敏感词
 * 用户名敏感词汇信息表，就是注册登录账号不能用的词汇
 *
 * 数据库表: SUC_USER_FORBIDDEN_WORD
 *
 * 自动生成，如需修改，请删除本行 @mbg.generated
 */
@JsonInclude(Include.NON_NULL)
public class SucUserForbiddenWordMo implements Serializable {

    /**
     *    用户名敏感词ID
     *
     *    数据库字段: SUC_USER_FORBIDDEN_WORD.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long id;

    /**
     *    关键字
     *
     *    数据库字段: SUC_USER_FORBIDDEN_WORD.KEYWORD
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String keyword;

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户名敏感词ID
     *
     *    数据库字段: SUC_USER_FORBIDDEN_WORD.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户名敏感词ID
     *
     *    数据库字段: SUC_USER_FORBIDDEN_WORD.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    关键字
     *
     *    数据库字段: SUC_USER_FORBIDDEN_WORD.KEYWORD
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     *    关键字
     *
     *    数据库字段: SUC_USER_FORBIDDEN_WORD.KEYWORD
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
        sb.append(", keyword=").append(keyword);
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
        SucUserForbiddenWordMo other = (SucUserForbiddenWordMo) that;
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
