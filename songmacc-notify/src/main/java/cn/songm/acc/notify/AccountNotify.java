package cn.songm.acc.notify;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
            String ms = msg.getText();
            log.info("Receive message: {}", ms);
            // 保存用户报道日志
            JsonObject jObj = new JsonParser().parse(ms).getAsJsonObject();
            UserReport report = new UserReport();
            report.setSesId(jObj.get("sesId").getAsString());
            report.setRtime(new Date(jObj.get("created").getAsLong()));
            report.setUserId(jObj.get("userId") != null ? jObj.get("userId").getAsLong() : null);
            userService.recordReport(report);
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
    }

}
