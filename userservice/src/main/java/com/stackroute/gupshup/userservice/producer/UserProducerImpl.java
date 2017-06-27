package com.stackroute.gupshup.userservice.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserProducerImpl implements UserProducer {
	
	@Autowired
	private Environment environment;
	
	
	/* publishing an activity to a topic */
	public void publishUserActivity(String topicName, String message)
	{
		Properties configProperties = new Properties();
		/* setting all the configurations for a producer */
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("userproducer.bootstrap-servers"));
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		
		Producer userProducer = new KafkaProducer<>(configProperties);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, message);
		/* publishing an activity */
		userProducer.send(record);
		userProducer.close();	
	}

}
