package rebue.suc.dic;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import rebue.wheel.baseintf.EnumBase;

/**
 * <pre>
 *  1 成功
 *  0 缓存失败
 * -1 参数不正确
 *    没有填写用户登录名称/EMAIL/MOBILE/密码/注册来源/MAC/IP
 *    登录名称格式不正确
 *       长度不能小于3，且不能大于20
 *       不能为Email或手机号码的格式
 *    Email格式不正确
 *    手机号码格式不正确
 * -2 用户登录名已存在
 * -3 Email已存在
 * -4 手机号码已存在
 * -5 身份证号码已存在
 * -6 QQ的ID已存在
 * -7 微信的ID已存在
 * </pre>
 */
@ApiModel(value = "注册返回结果", description = "注册返回结果的字典")
public enum RegResultDic implements EnumBase {
    /**
     * 1 成功
     */
    @ApiModelProperty(value = "成功")
    SUCCESS(1),
    /**
     * 0 缓存失败
     */
    @ApiModelProperty(value = "缓存失败")
    CACHE_FAIL(0),
    /**
     * <pre>
     * -1 参数不正确
     *     没有填写用户登录名称/EMAIL/MOBILE/登录密码/注册来源/MAC/IP
     *     登录名称格式不正确
     *        长度不能小于3，且不能大于20
     *        不能为Email或手机号码的格式
     *     Email格式不正确
     *     手机号码格式不正确
     * </pre>
     */
    @ApiModelProperty(value = "参数不正确\n没有填写登录名称/EMAIL/MOBILE/登录密码/来源/MAC/IP\n登录名称格式不正确\nEmail格式不正确\n手机号码格式不正确")
    PARAM_ERROR(-1),
    /**
     * -2 用户登录名已存在
     */
    @ApiModelProperty(value = "用户登录名已存在")
    LOGIN_NAME_EXIST(-2),
    /**
     * -3 Email已存在
     */
    @ApiModelProperty(value = "Email已存在")
    EMAIL_EXIST(-3),
    /**
     * -4 手机号码已存在
     */
    @ApiModelProperty(value = "手机号码已存在")
    MOBILE_EXIST(-4),
    /**
     * -5 身份证号码已存在
     */
    @ApiModelProperty(value = "身份证号码已存在")
    IDCARD_EXIST(-5),
    /**
     * -6 QQ的ID已存在
     */
    @ApiModelProperty(value = "QQ的ID已存在")
    QQ_ID_EXIST(-6),
    /**
     * -7 微信的ID已存在
     */
    @ApiModelProperty(value = "微信的ID已存在")
    WX_ID_EXIST(-7);

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
     * jackson反序列化时，通过code得到枚举的实例
     * 注意：此方法必须是static的方法，且返回类型必须是本枚举类，而不能是接口EnumBase
     * 否则jackson将调用默认的反序列化方法，而不会调用本方法
     */
    @JsonCreator
    public static RegResultDic getItem(int code) {
        EnumBase result = valueMap.get(code);
        if (result == null) {
            throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
        }
        return (RegResultDic) result;
    }

    private int code;

    /**
     * 构造器，传入code
     */
    RegResultDic(int code) {
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