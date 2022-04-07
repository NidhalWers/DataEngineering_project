package com.project.spark.service

import java.time.Duration
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkContext

import scala.collection.JavaConverters._


class ConsumerService {


  val props : Properties = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "alert")

  val consumer : KafkaConsumer[String, String] = new KafkaConsumer[String,String](props)
  consumer.subscribe(List("peace-project").asJava)

  val messageService = new MessageService

  def readMessage() : Unit = {
    val records : ConsumerRecords[String,String] = consumer.poll(Duration.ofMillis(100))
    records.asScala
      .map( record => messageService.parseFromJson(record.value()) )
      .map(message => messageService.consumeMessage(message))
      .map( alerts => alerts.foreach( a => println(a.toString)))
  }


  def closeConsumer():Unit = {
    consumer.close()
  }
}