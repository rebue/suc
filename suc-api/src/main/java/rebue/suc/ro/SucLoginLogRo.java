package rebue.suc.ro;
import rebue.suc.mo.SucLoginLogMo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(Include.NON_NULL)
public class SucLoginLogRo {
    /**
     *	This field was generated by MyBatis Generator.
     * 
     * 	返回值	1:成功  -1:失败 
     *	
     *	@mbg.generated
     *	
     */
	private Byte result;
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	返回的结果	
     *	
     * @mbg.generated
     */
	private String msg ;
	
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	返回的结果值
     *	
     * 	@mbg.generated
     */
	private SucLoginLogMo record;
	
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	@mbg.generated
     *
     * 
     */
	public Byte getResult() {
		return result;
	}
  	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	@mbg.generated
     *
     * 
     */
	public void setResult(Byte result) {
		this.result = result;
	}
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	@mbg.generated
     *
     * 
     */
	public String getMsg() {
		return msg;
	}
	
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	@mbg.generated
     *
     * 
     */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	@mbg.generated
     *
     * 
     */
	public SucLoginLogMo getRecord() {
		return record;
	}
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	@mbg.generated
     *
     * 
     */
	public void setRecord( SucLoginLogMo record) {
		this.record = record;
	}
	 /**
     *	This field was generated by MyBatis Generator.
     * 
     *	@mbg.generated
     *
     * 
     */
	@Override
	public String toString() {
		return "PfmSysTo [result=" + result + ", msg=" + msg + ", record=" + record + "]";
	}
	

}