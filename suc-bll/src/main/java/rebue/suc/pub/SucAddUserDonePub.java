package rebue.suc.pub;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import rebue.sbs.rabbit.RabbitProducer;
import rebue.suc.co.SucExchangeCo;
import rebue.suc.msg.SucAddUserDoneMsg;

/**
 * 添加用户的发布者
 */
@Component
public class SucAddUserDonePub {
    @Resource
    private RabbitProducer producer;

    @PostConstruct
    void init() throws Exception {
        // 声明Exchange
        producer.declareExchange(SucExchangeCo.SUC_ADD_USER_DONE_EXCHANGE_NAME);
    }

    /**
     * 发送消息
     */
    public void send(SucAddUserDoneMsg msg) {
        producer.send(SucExchangeCo.SUC_ADD_USER_DONE_EXCHANGE_NAME, msg);
    }

}
