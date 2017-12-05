package com.ericliu.dubbo.provider.user.amqp;

import com.ericliu.dubbo.provider.user.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/5
 */
@Component
public class UserAmqpListener implements ChannelAwareMessageListener {

    @Resource(name = "rabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "user.event.queue")
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        try {
            User userDomain = (User) rabbitTemplate.getMessageConverter().fromMessage(message);
            System.out.println(userDomain);
            //do somthing
        } catch (Exception e) {
            //requeue
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        } finally {
            //no requeue
            if (!true) {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        }
    }
}
