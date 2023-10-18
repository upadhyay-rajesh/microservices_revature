package com.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	//accessing eureka registry
	@Autowired
	private DiscoveryClient ds;
	//accessing ribbon
	//@Autowired
	//private LoadBalancerClient loadBalancer;
	
	@GetMapping("consumeproducerservice")
	public String consumeService() {
		
		//eureka registry instance access start
		 List<ServiceInstance> instances=  ds.getInstances("EMPLOYEE-PRODUCER");
		 System.out.println(instances.size());
		 ServiceInstance ss= instances.get(0);
		//eureka registry instance access end
		//ribbon instance access start
		// ServiceInstance ss1=loadBalancer.choose("EMPLOYEE-PRODUCER");
		//ribbon instance access end
		 String uri=ss.getUri().toString();
		 System.out.println("producer instance detail is "+uri);
		
		String url=uri+"/displayAll";
		
		
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> result=  restTemplate.exchange(url,HttpMethod.GET,getHeader(),String.class);
		
		return result.getBody();
	}
	
	private static HttpEntity<?> getHeader(){
		HttpHeaders header=new HttpHeaders();
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity(header);
	}
}
