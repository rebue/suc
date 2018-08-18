package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 注册用户(通过微信)的传输对象
 */
@ApiModel(value = "注册(微信)", description = "用户通过微信注册的参数")
@JsonInclude(Include.NON_NULL)
public class RegByWxTo extends RegBaseTo {
	@ApiModelProperty(value = "微信的ID", required = true)
	private String wxId;
	@ApiModelProperty(value = "微信昵称", required = true)
	private String wxNickname;
	@ApiModelProperty(value = "微信头像", required = true)
	/**
	 * 微信openid
	 */
	private String wxOpenid;
	/**
	 * 微信头像
	 */
	private String wxFace;

	/**
	 * 推广人id
	 */
	private Long promoterId;

	/**
	 * 上线id
	 */
	private Long onlineId;

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getWxFace() {
		return wxFace;
	}

	public void setWxFace(String wxFace) {
		this.wxFace = wxFace;
	}

	public Long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}

	public Long getOnlineId() {
		return onlineId;
	}

	public void setOnlineId(Long onlineId) {
		this.onlineId = onlineId;
	}

	@Override
	public String toString() {
		return "RegByWxTo [wxId=" + wxId + ", wxNickname=" + wxNickname + ", wxOpenid=" + wxOpenid + ", wxFace="
				+ wxFace + ", promoterId=" + promoterId + ", onlineId=" + onlineId + "]";
	}

}
