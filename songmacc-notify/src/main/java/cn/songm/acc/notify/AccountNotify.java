package cn.songm.acc.notify;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.songm.acc.entity.UserReport;
import cn.songm.acc.service.UserService;

public class AccountNotify implements MessageListener {

    private static final Logger log = LoggerFactory
            .getLogger(AccountNotify.class);

    @Autowired
    private UserService userService;

    @Override
    public void onMessage(Message message) {
        ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
        try {
            UserReport report = new UserReport();
            String ms = msg.getText();
            log.info("Receive message: {}", ms);
            JSONObject jo = JSONObject.parseObject(ms);
            report.setSesId(jo.getString("sesId"));
            report.setRtime(jo.getDate("created"));
            report.setUserId(jo.getLong("userId"));
            userService.recordReport(report);
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
    }

}
