package com.project.spark.service
import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.codehaus.jackson.map.ser.std.StringSerializer


class ProducerService {


  private val props : Properties = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])

  private val producer : KafkaProducer[String, String] = new KafkaProducer[String,String](props)


  def sendMessage(id : String, body : String) : Unit = {
    val record = new ProducerRecord[String, String](id, body)
    producer.send(record)
  }
}