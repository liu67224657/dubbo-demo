package com.ericliu.dubbo.provider.user.amqp;

import com.ericliu.dubbo.provider.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/5
 */
@Component
public class UserAmqpSender {

    private static Logger log = LoggerFactory.getLogger(UserAmqpSender.class);

    @Resource(name = "amqpAdmin")
    private AmqpAdmin amqpAdmin;
    @Resource(name = "rabbitTemplate")
    private RabbitTemplate rabbitTemplate;



    public void sendMessage(User user) {
//        String sendMsg = "this is direct message";
//        Message message = MessageBuilder.withBody(user)
//                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
//                .build();
//        String routingKey = "user.event.save";
//        rabbitTemplate.setExchange();
//        rabbitTemplate.setRoutingKey("user.event.save");
        rabbitTemplate.convertAndSend("user.event.exchange","user.event.save",user);
//        rabbitTemplate.send(routingKey, message);
    }

    @PostConstruct
    private void declareContent() {
        Exchange e = ExchangeBuilder.topicExchange("user.event.exchange").build();
        Queue q = new Queue("user.event.queue");
        Binding bind = BindingBuilder.bind(q).to(e).with("user.event.#").noargs();

        amqpAdmin.declareQueue(q);
        amqpAdmin.declareExchange(e);
        amqpAdmin.declareBinding(bind);
    }


//    @RabbitListener(queues = "user.event.queue")
//    public void receiveContentMessage(Message message) {
//        Object event =  rabbitTemplate.getMessageConverter().fromMessage(message);
//        log.info("========receiveEvent: {} , message routing keys: {}", message, message.getMessageProperties().getReceivedRoutingKey());
////        receiveEvent(event);
//    }

}
