package com.revature.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Sender {
	
	private static final Logger LOG = LoggerFactory.getLogger(Sender.class);
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String payload) {
		
        LOG.info("sending message='{}' to topic='{}'", payload, topic);
		try {
			kafkaTemplate.send(topic, mapper.writeValueAsString(payload));
		} catch (Exception e) {
			LOG.error("Couldn't stringify POJO in sender.", e);
		}
	}
}
