package rebue.suc.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 注册与登录的基础传输对象
 */
@Getter
@Setter
@ToString
public abstract class RegAndLoginBaseTo {
    /**
     * 系统ID
     */
    private String sysId;
    /**
     * 领域id
     */
    private String domainId;
    /**
     * 用户的浏览器类型
     */
    private String userAgent;
    /**
     * 用户的网卡的MAC地址
     */
    private String mac;
    /**
     * 用户的网卡的IP地址
     */
    private String ip;
}
