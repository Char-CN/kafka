package org.blazer.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerTest {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "ms:9092,sl1:9092,sl2:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		ProducerRecord re = new ProducerRecord<String, String>("hyy-test", "k2", "hyy good");
		System.out.println(re);
		producer.send(re);
		for (int i = 0; i < 5; i++) {
			int k = 0;
			if (i % 2 == 0) {
				k = 1;
			}
			ProducerRecord record = new ProducerRecord<String, String>("hyy-test", "k" + k, "hello word");
			System.out.println(record);
			producer.send(record);
		}
		producer.close();
	}
}
