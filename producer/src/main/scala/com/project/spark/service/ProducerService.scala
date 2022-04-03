package com.project.spark.service
import java.util.Properties
import java.util.concurrent.Future

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer



class ProducerService {


  val props : Properties = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])

  val producer : KafkaProducer[String, String] = new KafkaProducer[String,String](props)


  def sendMessage(id : String, body : String) : Unit = {
    val record = new ProducerRecord[String, String]("peace-project",id, body)

    producer.send(record)
    Thread.sleep(10000)

    //producer.close()
  }

  def closeProducer():Unit = {
    producer.close()
  }
}