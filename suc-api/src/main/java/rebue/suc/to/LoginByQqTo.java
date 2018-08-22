package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "登录(QQ)", description = "用户通过QQ登录的参数")
@JsonInclude(Include.NON_NULL)
public class LoginByQqTo extends RegAndLoginBaseTo {
	@ApiModelProperty(value = "QQ的ID")
	private String qqId;
	/**
	 * QQopenid
	 */
	private String qqOpenid;
	@ApiModelProperty(value = "QQ昵称")
	private String qqNickname;
	@ApiModelProperty(value = "QQ头像")
	private String qqFace;

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getQqOpenid() {
		return qqOpenid;
	}

	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid = qqOpenid;
	}

	public String getQqNickname() {
		return qqNickname;
	}

	public void setQqNickname(String qqNickname) {
		this.qqNickname = qqNickname;
	}

	public String getQqFace() {
		return qqFace;
	}

	public void setQqFace(String qqFace) {
		this.qqFace = qqFace;
	}

	@Override
	public String toString() {
		return "LoginByQqTo [qqId=" + qqId + ", qqOpenid=" + qqOpenid + ", qqNickname=" + qqNickname + ", qqFace="
				+ qqFace + ", getSysId()=" + getSysId() + ", getUserAgent()=" + getUserAgent() + ", getMac()="
				+ getMac() + ", getIp()=" + getIp() + "]";
	}

}
