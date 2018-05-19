package rebue.suc.msg;

public class SucAddUserDoneMsg {
    /**
     * 用户ID
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SucUserAddMsg [id=" + id + "]";
    }

}
