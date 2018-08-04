package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.suc.mo.SucUserMo;

@JsonInclude(Include.NON_NULL)
public class SucUserRo {

	private SucUserMo record;

	private int result;

	private String msg;

	public SucUserMo getRecord() {
		return record;
	}

	public void setRecord(SucUserMo record) {
		this.record = record;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "SucUserRo [record=" + record + ", result=" + result + ", msg=" + msg + "]";
	}

}
