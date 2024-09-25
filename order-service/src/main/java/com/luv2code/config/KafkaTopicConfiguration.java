package com.luv2code.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

	
	@Value("${spring.kafka.template.default-topic}")
	public String topicName;
	
	@Bean
	NewTopic createNewTopic() {
		
		return TopicBuilder.name(topicName).build();
	}
}
