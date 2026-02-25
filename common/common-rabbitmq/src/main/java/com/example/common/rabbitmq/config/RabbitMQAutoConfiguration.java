package com.example.common.rabbitmq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * RabbitMQ自动配置类
 */
@Slf4j
@AutoConfiguration
public class RabbitMQAutoConfiguration {

    /**
     * 消息转换器
     * 使用Jackson序列化/反序列化消息
     */
    @Bean
    @ConditionalOnMissingBean(MessageConverter.class)
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
        // 允许所有类型反序列化
        objectMapper.activateDefaultTyping(
            LaissezFaireSubTypeValidator.instance,
            ObjectMapper.DefaultTyping.NON_FINAL
        );
        log.info("RabbitMQ消息转换器初始化完成");
        return converter;
    }

    /**
     * RabbitTemplate配置
     */
    @Bean
    @ConditionalOnMissingBean(RabbitTemplate.class)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, 
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        
        // 开启发送确认
        rabbitTemplate.setMandatory(true);
        
        // 设置发送确认回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送成功，消息ID：{}", correlationData != null ? correlationData.getId() : "null");
            } else {
                log.error("消息发送失败，消息ID：{}，原因：{}", 
                         correlationData != null ? correlationData.getId() : "null", cause);
            }
        });
        
        // 设置返回回调
        rabbitTemplate.setReturnsCallback(returned -> {
            log.error("消息无法路由，消息ID：{}，交换机：{}，路由键：{}，回复码：{}，回复文本：{}",
                     returned.getMessage().getMessageProperties().getMessageId(),
                     returned.getExchange(),
                     returned.getRoutingKey(),
                     returned.getReplyCode(),
                     returned.getReplyText());
        });
        
        log.info("RabbitTemplate初始化完成");
        return rabbitTemplate;
    }
}