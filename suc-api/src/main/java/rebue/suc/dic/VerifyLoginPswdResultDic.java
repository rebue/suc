package rebue.suc.dic;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import rebue.wheel.baseintf.EnumBase;

/**
 * 1 成功
 * 0 缓存失败
 * -1 参数不正确
 * -2 找不到用户信息
 * -3 用户被锁定
 * -4 密码错误
 * -5 用户用Email登录，但Email尚未通过验证
 * -6 用户用手机号登录，但手机号尚未通过验证
 */
@ApiModel(value = "校验支付密码的返回结果", description = "校验支付密码的返回结果的字典")
public enum VerifyLoginPswdResultDic implements EnumBase {
    /**
     * 1: 成功
     */
    @ApiModelProperty(value = "成功")
    SUCCESS(1),
    /**
     * 0: 缓存失败
     */
    @ApiModelProperty(value = "缓存失败")
    CACHE_FAIL(0),
    /**
     * -1: 参数不正确 没有填写用户ID/支付密码
     */
    @ApiModelProperty(value = "参数不正确")
    PARAM_ERROR(-1),
    /**
     * -2: 找不到用户
     */
    @ApiModelProperty(value = "找不到用户")
    NOT_FOUND_USER(-2),
    /**
     * -3: 用户被锁定
     */
    @ApiModelProperty(value = "用户被锁定")
    USER_LOCKED(-3),
    /**
     * -4: 密码错误
     */
    @ApiModelProperty(value = "密码错误")
    PASSWORD_ERROR(-4),
    /**
     * -5: 用户用邮箱登录，但邮箱尚未通过验证
     */
    @ApiModelProperty(value = "用户用邮箱登录，但邮箱尚未通过验证")
    NO_VERITY_EMAIL(-5),
    /**
     * -6: 用户用手机号登录，但手机号尚未通过验证
     */
    @ApiModelProperty(value = "用户用手机号登录，但手机号尚未通过验证")
    NO_VERITY_MOBILE(-6);

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
     * jackson反序列化时，通过code得到枚举的实例 注意：此方法必须是static的方法，且返回类型必须是本枚举类，而不能是接口EnumBase 否则jackson将调用默认的反序列化方法，而不会调用本方法
     */
    @JsonCreator
    public static VerifyLoginPswdResultDic getItem(int code) {
        EnumBase result = valueMap.get(code);
        if (result == null) {
            throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
        }
        return (VerifyLoginPswdResultDic) result;
    }

    private int code;

    /**
     * 构造器，传入code
     */
    VerifyLoginPswdResultDic(int code) {
        this.code = code;
    }

    /**
     * @return jackson序列化时，输出枚举实例的code
     */
    @Override
    public int getCode() {
        return code;
    }
}