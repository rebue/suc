package rebue.suc.pub;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import rebue.sbs.rabbit.RabbitProducer;
import rebue.suc.co.SucExchangeCo;
import rebue.suc.msg.SucUserAddMsg;

/**
 * 添加用户的发布者
 */
@Component
public class SucUserAddPub {
    @Resource
    private RabbitProducer producer;

    @PostConstruct
    void init() throws Exception {
        // 声明Exchange
        producer.declareExchange(SucExchangeCo.SUC_USER_ADD_EXCHANGE_NAME);
    }

    /**
     * 发送消息
     */
    public void send(SucUserAddMsg msg) {
        producer.send(SucExchangeCo.SUC_USER_ADD_EXCHANGE_NAME, msg);
    }

}
