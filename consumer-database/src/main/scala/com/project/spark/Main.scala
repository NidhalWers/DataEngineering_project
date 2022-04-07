package com.project.spark

import com.project.spark.infrastructure.MessageRepository
import com.project.spark.service.{ConsumerService, MessageService}
import org.apache.spark.sql.SparkSession
import org.apache.spark.api.java.JavaSparkContext.fromSparkContext

object Main {

  //services
  val messageService = new MessageService
  val consumerService =  new ConsumerService
  val Repo = new MessageRepository

  def main(args: Array[String]): Unit = {
    println("Peaceland Project - Consumer-Database")

    def makeAction(acc: Int): Unit = /*acc match*/ {
       consumerService.readMessage()
      makeAction(1)
    }

    makeAction(1)
    consumerService.closeConsumer()
    println("End program")
  }
}
