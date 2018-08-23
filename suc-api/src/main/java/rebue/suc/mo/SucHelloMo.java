package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 数据库表: SUC_HELLO
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucHelloMo implements Serializable {

    /**
     *    测试ID
     *
     *    数据库字段: SUC_HELLO.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    HELLO
     *
     *    数据库字段: SUC_HELLO.HELLO
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String hello;

    /**
     *    WORLD
     *
     *    数据库字段: SUC_HELLO.WORLD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String world;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    测试ID
     *
     *    数据库字段: SUC_HELLO.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    测试ID
     *
     *    数据库字段: SUC_HELLO.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    HELLO
     *
     *    数据库字段: SUC_HELLO.HELLO
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getHello() {
        return hello;
    }

    /**
     *    HELLO
     *
     *    数据库字段: SUC_HELLO.HELLO
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setHello(String hello) {
        this.hello = hello;
    }

    /**
     *    WORLD
     *
     *    数据库字段: SUC_HELLO.WORLD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWorld() {
        return world;
    }

    /**
     *    WORLD
     *
     *    数据库字段: SUC_HELLO.WORLD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWorld(String world) {
        this.world = world;
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
        sb.append(", hello=").append(hello);
        sb.append(", world=").append(world);
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
        SucHelloMo other = (SucHelloMo) that;
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
