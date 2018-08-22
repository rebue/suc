package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户锁定日志
 *
 * 数据库表: SUC_LOCK_LOG
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucLockLogMo implements Serializable {

    /**
     *    用户锁定日志ID
     *
     *    数据库字段: SUC_LOCK_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOCK_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long userId;

    /**
     *    锁定原因
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_REASON
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String lockReason;

    /**
     *    锁定时间
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lockTime;

    /**
     *    锁定操作员ID，0为系统操作
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long lockOpId;

    /**
     *    解锁原因
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_REASON
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String unlockReason;

    /**
     *    解锁时间
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date unlockTime;

    /**
     *    解锁操作员ID，0为系统操作
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long unlockOpId;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户锁定日志ID
     *
     *    数据库字段: SUC_LOCK_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户锁定日志ID
     *
     *    数据库字段: SUC_LOCK_LOG.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOCK_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_LOCK_LOG.USER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *    锁定原因
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_REASON
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLockReason() {
        return lockReason;
    }

    /**
     *    锁定原因
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_REASON
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    /**
     *    锁定时间
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     *    锁定时间
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     *    锁定操作员ID，0为系统操作
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getLockOpId() {
        return lockOpId;
    }

    /**
     *    锁定操作员ID，0为系统操作
     *
     *    数据库字段: SUC_LOCK_LOG.LOCK_OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLockOpId(Long lockOpId) {
        this.lockOpId = lockOpId;
    }

    /**
     *    解锁原因
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_REASON
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUnlockReason() {
        return unlockReason;
    }

    /**
     *    解锁原因
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_REASON
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnlockReason(String unlockReason) {
        this.unlockReason = unlockReason;
    }

    /**
     *    解锁时间
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getUnlockTime() {
        return unlockTime;
    }

    /**
     *    解锁时间
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    /**
     *    解锁操作员ID，0为系统操作
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getUnlockOpId() {
        return unlockOpId;
    }

    /**
     *    解锁操作员ID，0为系统操作
     *
     *    数据库字段: SUC_LOCK_LOG.UNLOCK_OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnlockOpId(Long unlockOpId) {
        this.unlockOpId = unlockOpId;
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
        sb.append(", lockReason=").append(lockReason);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", lockOpId=").append(lockOpId);
        sb.append(", unlockReason=").append(unlockReason);
        sb.append(", unlockTime=").append(unlockTime);
        sb.append(", unlockOpId=").append(unlockOpId);
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
        SucLockLogMo other = (SucLockLogMo) that;
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
