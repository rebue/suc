package rebue.suc.dic;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

import rebue.wheel.baseintf.EnumBase;

/**
 * 注册与登录的类型
 * 1: 用户登录名
 * 2: 电子邮箱
 * 3: 手机
 * 4: QQ
 * 5: 微信
 */
public enum RegAndLoginTypeDic implements EnumBase {
    /**
     * 1: 用户登录名
     */
    LOGIN_NAME(1),
    /**
     * 2: 电子邮箱
     */
    EMAIL(2),
    /**
     * 3: 手机
     */
    MOBILE(3),
    /**
     * 4: QQ
     */
    QQ(4),
    /**
     * 5: 微信
     */
    WX(5);

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
    public static RegAndLoginTypeDic getItem(int code) {
        EnumBase result = valueMap.get(code);
        if (result == null) {
            throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
        }
        return (RegAndLoginTypeDic) result;
    }

    private int code;

    /**
     * 构造器，传入code
     */
    RegAndLoginTypeDic(int code) {
        this.code = code;
    }

    /**
     * @return jackson序列化时，输出枚举实例的code
     */
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name();
    }

}
