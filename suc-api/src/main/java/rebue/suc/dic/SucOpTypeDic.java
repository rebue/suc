package rebue.suc.dic;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

import rebue.wheel.baseintf.EnumBase;

/**
 * 用户操作类型
 * 1: 修改登录密码
 * 2: 修改支付密码
 * 3: 绑定QQ
 * 4: 绑定微信
 * 5: 更新QQ昵称和头像
 * 6: 更新微信昵称和头像
 */
public enum SucOpTypeDic implements EnumBase {
    /**
     * 1: 修改登录密码
     */
    MODIFY_LOGIN_PSWD(1),
    /**
     * 2: 修改支付密码
     */
    MODIFY_PAY_PSWD(2),
    /**
     * 3: 绑定QQ
     */
    BIND_QQ(3),
    /**
     * 4: 绑定微信
     */
    BIND_WX(4),
    /**
     * 5: 更新QQ昵称和头像
     */
    MODIFY_QQ_INFO(5),
    /**
     * 6: 更新微信昵称和头像
     */
    MODIFY_WX_INFO(6);

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
    public static SucOpTypeDic getItem(int code) {
        EnumBase result = valueMap.get(code);
        if (result == null) {
            throw new IllegalArgumentException("输入的code" + code + "不在枚举的取值范围内");
        }
        return (SucOpTypeDic) result;
    }

    private int code;

    /**
     * 构造器，传入code
     */
    SucOpTypeDic(int code) {
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
