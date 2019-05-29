package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerPremitiveConfig {

	@Autowired
	private RabbitTemplate premitiveRabbitTemplate;

	@Value("${amitstack.pre.rabbitmq.exchange}")
	private String exchange;

	@Value("${amitstack.pre.rabbitmq.routingkey}")
	private String routingKey;

	@Value("${amitstack.pre.rabbitmq.queue}")
	private String queueName;

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	public void produceMsg(String msg) {
		premitiveRabbitTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("Message :: " + msg);
	}

}
