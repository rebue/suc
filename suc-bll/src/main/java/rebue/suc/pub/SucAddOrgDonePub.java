package rebue.suc.pub;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import rebue.sbs.rabbit.RabbitProducer;
import rebue.suc.co.SucExchangeCo;
import rebue.suc.msg.SucAddOrgDoneMsg;

/**
 * 添加组织完成的发布者
 */
@Service
public class SucAddOrgDonePub implements ApplicationListener<ApplicationStartedEvent> {
    private static final Logger _log         = LoggerFactory.getLogger(SucAddOrgDonePub.class);

    /**
     * 启动标志，防止多次启动
     */
    private boolean             bStartedFlag = false;

    @Resource
    private RabbitProducer      producer;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        // 防止多次启动
        if (bStartedFlag)
            return;
        bStartedFlag = true;        // 声明Exchange

        try {
            producer.declareExchange(SucExchangeCo.SUC_ADD_ORG_DONE_EXCHANGE_NAME);
        } catch (Exception e) {
            String msg = "声明添加组织完成消息的Exchange失败";
            _log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    /**
     * 发送消息
     */
    public void send(SucAddOrgDoneMsg msg) {
        producer.send(SucExchangeCo.SUC_ADD_ORG_DONE_EXCHANGE_NAME, msg);
    }

}
