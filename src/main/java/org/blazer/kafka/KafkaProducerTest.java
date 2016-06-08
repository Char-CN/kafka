package org.blazer.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerTest {
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
		ProducerRecord re = new ProducerRecord<String, String>("my-topic", "key000000000000000000000000000000", "value111111111111111111111111111");
		producer.send(re);
		for (int i = 0; i < 5; i++) {
			ProducerRecord record = new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i));
			System.out.println(record.topic() + "	" + i);
			producer.send(record);
		}
		producer.close();
	}
}