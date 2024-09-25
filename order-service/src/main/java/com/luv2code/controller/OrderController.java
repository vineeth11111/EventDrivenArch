package com.luv2code.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.dto.Order;
import com.luv2code.dto.OrderEvent;
import com.luv2code.service.KafkaProducerService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private KafkaProducerService kafkaProducerService;
	
	OrderController(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}
	
	@PostMapping("/orders")
	public ResponseEntity<String> placeOrder(@RequestBody Order order) {
		
		order.setOrderId(UUID.randomUUID().toString());
		
		OrderEvent event = new OrderEvent();
		event.setStatus("PENDING");
		event.setMessage("order status is in pending state");
		event.setOrder(order);
		
		kafkaProducerService.sendMessage(event);
		
		return ResponseEntity.ok("Order placed successfully");
	}
	
}
