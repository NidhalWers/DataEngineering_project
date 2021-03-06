package com.project.spark

import com.project.spark.service.{ConsumerService, MessageService}
import org.apache.spark.sql.SparkSession
import org.apache.spark.api.java.JavaSparkContext.fromSparkContext

object Main {

  //services
  val messageService = new MessageService
  val consumerService =  new ConsumerService


  def main(args: Array[String]): Unit = {
    println("Peaceland Project - Consumer-Alert")
    val spark = SparkSession.builder().appName("Peaceland Project - Consumer-Alert")
      .master("local[*]")
      .getOrCreate()

    val sc = spark.sparkContext


    def action() : Unit /*List[Message]*/ = {
      /*val jsons = consumerService.readMessage()
      jsons.map(json => messageService.parseFromJson(json))
      */

    }

    def makeAction(acc: Int): Unit = /*acc match*/ {
      /*case 0 => println("End actions")
      case _ => action()
        .foreach(x => x.toString)
        makeAction(1)
        */
      consumerService.readMessage()

      makeAction(1)
    }

    makeAction(1)
    consumerService.closeConsumer()
    println("End program")
    sc.close()
  }
}
