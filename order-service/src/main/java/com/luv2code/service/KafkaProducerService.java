package com.luv2code.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.luv2code.dto.OrderEvent;

@Service
public class KafkaProducerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

	private NewTopic orderTopic;
	
	private KafkaTemplate<String, OrderEvent > kafkaTemplate;
	
	KafkaProducerService(NewTopic orderTopic , KafkaTemplate<String, OrderEvent > kafkaTemplate) {
		this.orderTopic = orderTopic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(OrderEvent orderEvent) {
		LOGGER.info(String.format("Order event => %s", orderEvent.toString()));
		System.out.println("Order event => "+orderEvent.toString());
		Message<OrderEvent> message = MessageBuilder.
				withPayload(orderEvent).
				setHeader(KafkaHeaders.TOPIC, orderTopic.name())
				.build();
		kafkaTemplate.send(message);
	}
}
