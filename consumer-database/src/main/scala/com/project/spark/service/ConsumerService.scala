package com.project.spark.service
import java.time.Duration
import java.util.Properties

import com.project.spark.infrastructure.MessageRepository

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer



class ConsumerService {


  val props : Properties = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "database")

  val consumer : KafkaConsumer[String, String] = new KafkaConsumer[String,String](props)
  consumer.subscribe(List("peace-project").asJava)

  val messageService = new MessageService
  val messageRepository = new MessageRepository

  def readMessage() : Unit /* List[String]*/ = {
    val records : ConsumerRecords[String,String] = consumer.poll(Duration.ofMillis(100))
    records.asScala
      .map( record => messageService.parseFromJson(record.value()) )
      .foreach( message => messageRepository.insert(message) )
  }


  def closeConsumer():Unit = {
    consumer.close()
  }
}