package rebue.suc.dic;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

import rebue.wheel.baseintf.EnumBase;

/**
 * 创建时间：2018年5月14日 下午2:22:34 项目名称：suc-api
 * 
 * @author daniel
 * @version 1.0
 * @since JDK 1.8 文件名称：LoginPswdSetDic.java 类说明： 设置登录名称返回结果字典
 */
public enum LoginNameSetDic implements EnumBase {
	/** 设置成功 **/
	SUCCESS(1),

	/** 用户未登录 **/
	NOT_LOGIN(-1),

	/** 输入的新登录名称为空 **/
	NEW_LOGINNAME_NULL(-2),

	/** 未注册 **/
	NOT_REGISTER(-3),

	/** 登录名称已存在 **/
	LOGNAME_ALREADY_EXIST(-4),

	/** 设置失败 **/
	SET_ERROR(-5);

	/**
	 * 枚举的所有项，注意这个变量是静态单例的
	 */
	private static Map<Integer, EnumBase> valueMap;
	// 初始化map，保存枚举的所有项到map中以方便通过code查找
	static {
		valueMap = new HashMap<>();
		for (EnumBase item : values()) {
			valueMap.put(item.getCode(), item);
		}
	}

	/**
	 * jackson反序列化时，通过code得到枚举的实例 注意：此方法必须是static的方法，且返回类型必须是本枚举类，而不能是接口EnumBase
	 * 否则jackson将调用默认的反序列化方法，而不会调用本方法
	 */
	@JsonCreator
	public static LoginNameSetDic getItem(int code) {
		EnumBase result = valueMap.get(code);
		if (result == null) {
			throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
		}
		return (LoginNameSetDic) result;
	}

	private int code;

	/**
	 * 构造器，传入code
	 */
	LoginNameSetDic(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

}
