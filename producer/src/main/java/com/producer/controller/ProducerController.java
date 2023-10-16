package com.producer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.entity.Employee;

@RestController
public class ProducerController {
	@GetMapping("displayAll")
	public List<Employee> getList(){
		Employee e1=new Employee();
		e1.setName("Patric");
		e1.setPassword("pp123");
		e1.setEmail("patric@gmail.com");
		e1.setAddress("USA");
		
		Employee e2=new Employee();
		e2.setName("Alex");
		e2.setPassword("aaa123");
		e2.setEmail("alex@gmail.com");
		e2.setAddress("Los Angeles");
		
		List<Employee> ll=new ArrayList<Employee>();
		ll.add(e1);
		ll.add(e2);
		return ll;
	}
}
