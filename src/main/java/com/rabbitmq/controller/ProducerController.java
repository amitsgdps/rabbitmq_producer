package com.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.Employee;
import com.rabbitmq.config.ProducerComplex;
import com.rabbitmq.config.ProducerPremitive;

@RestController
public class ProducerController {

	@Autowired
	ProducerPremitive producerPremitive;

	@Autowired
	ProducerComplex producerComplex;

	@GetMapping(value = "/sendMsg")
	public String pushNormalMessage(@RequestParam("msg") String msg) {
		System.out.println("This is pushNormalMessage");
		producerPremitive.produceMsg(msg);
		return "Message pushed through rabbitmq";
	}

	@GetMapping(value = "/sendEmp")
	public String producerEmp(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {
		System.out.println("This is producerEmp");
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		producerComplex.sendEmployee(emp);

		return "Employee Message sent to the RabbitMQ Successfully";
	}
}
