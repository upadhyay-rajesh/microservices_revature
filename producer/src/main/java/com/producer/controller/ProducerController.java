package com.producer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.producer.entity.Employee;

@RestController
public class ProducerController {
	@GetMapping("displayAll")
	@HystrixCommand(fallbackMethod = "getcircuitfail")
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
		
		if(ll.size()==2) {
			throw new RuntimeException();
		}
		return ll;
	}
	
	public List<Employee> getcircuitfail() {
		Employee e2=new Employee();
		e2.setName("fail");
		e2.setPassword("fail");
		e2.setEmail("fail");
		e2.setAddress("fail");
		List<Employee> ll=new ArrayList<Employee>();
		ll.add(e2);
		return ll;
	}
}





