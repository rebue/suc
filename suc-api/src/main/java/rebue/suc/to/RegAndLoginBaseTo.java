package rebue.suc.to;

import io.swagger.annotations.ApiModelProperty;

/**
 * 注册与登录的基础传输对象
 */
public abstract class RegAndLoginBaseTo {
    @ApiModelProperty(value = "系统ID", required = true)
    private String   sysId;
    @ApiModelProperty(value = "用户的浏览器类型", required = true)
    private String userAgent;
    @ApiModelProperty(value = "用户的网卡的MAC地址", required = true)
    private String mac;
    @ApiModelProperty(value = "用户的网卡的IP地址", required = true)
    private String ip;

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String appId) {
        this.sysId = appId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
