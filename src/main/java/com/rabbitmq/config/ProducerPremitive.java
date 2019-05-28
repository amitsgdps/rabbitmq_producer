package com.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerPremitive {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${amitstack.rabbitmq.exchange}")
	private String exchange;

	@Value("${amitstack.rabbitmq.routingkey}")
	private String routingKey;

	@Value("${amitstack.rabbitmq.queue}")
	private String queueName;

	public void produceMsg(String msg) {
		rabbitTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("Message :: " + msg);
	}

}
