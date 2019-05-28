package com.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.Employee;

@Service
public class ProducerComplex {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${amitstack.rabbitmq.exchange}")
	private String exchange;

	@Value("${amitstack.rabbitmq.routingkey}")
	private String routingKey;

	@Value("${amitstack.rabbitmq.queue}")
	private String queueName;

	public void sendEmployee(Employee employee) {
		rabbitTemplate.convertAndSend(exchange, routingKey, employee);
		System.out.println("Send msg = " + employee);
	}

}
