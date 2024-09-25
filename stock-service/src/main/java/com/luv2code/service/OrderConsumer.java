package com.luv2code.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.luv2code.dto.OrderEvent;

@Service
public class OrderConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
	
	@KafkaListener(topics = "${spring.kafka.template.default-topic}" ,
			groupId = "${spring.kafka.consumer.group-id}")
	public void consume(OrderEvent event) {
		LOGGER.info("order event recieved in stock service =>%s",event.toString());
		System.out.println("order event recieved in stock service => "+event.toString());
		
		//save the order event into the database 
	}
}
