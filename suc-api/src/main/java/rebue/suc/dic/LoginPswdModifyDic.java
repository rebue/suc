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
 * @since JDK 1.8 文件名称：LoginPswdSetDic.java 类说明： 设置登录密码返回结果字典
 */
public enum LoginPswdModifyDic implements EnumBase {
	/** 修改成功 **/
	SUCCESS(1),

	/** 用户未登录 **/
	NOT_LOGIN(-1),

	/** 输入的新登录密码为空 **/
	NEW_LOGINPSWD_NULL(-2),

	/** 输入的旧登录密码为空 **/
	OLD_LOGINPSWD_NULL(-3),

	/** 找不到用户信息 **/
	NOT_FOUND_USER(-4),

	/** 输入的旧密码不正确 **/
	OLD_LOGINPSWD_ERROR(-5),

	/** 修改失败 **/
	MODIFY_ERROR(-6),

	/** 未设置登录密码 **/
	NOT_SET_LOGINPSWD(-7);

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
	public static LoginPswdModifyDic getItem(int code) {
		EnumBase result = valueMap.get(code);
		if (result == null) {
			throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
		}
		return (LoginPswdModifyDic) result;
	}

	private int code;

	/**
	 * 构造器，传入code
	 */
	LoginPswdModifyDic(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

}
