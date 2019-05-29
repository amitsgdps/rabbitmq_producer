package com.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.Employee;

@Configuration
public class ProducerComplexConfig {

	@Autowired
	AmqpTemplate rabbitTemplate;

	@Value("${amitstack.comp.rabbitmq.exchange}")
	private String exchange1;

	@Value("${amitstack.comp.rabbitmq.routingkey}")
	private String routingKey1;

	@Value("${amitstack.comp.rabbitmq.queue}")
	private String queueName1;

	@Bean
	Queue queue1() {
		return new Queue(queueName1, false);
	}

	@Bean
	DirectExchange exchange1() {
		return new DirectExchange(exchange1);
	}

	@Bean
	Binding binding1(Queue queue1, DirectExchange exchange1) {
		return BindingBuilder.bind(queue1).to(exchange1).with(routingKey1);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	public void sendEmployee(Employee employee) {
		rabbitTemplate.convertAndSend(exchange1, routingKey1, employee);
		System.out.println("Send msg = " + employee);
	}

}
