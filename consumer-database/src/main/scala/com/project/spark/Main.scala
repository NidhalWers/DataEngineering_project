package com.project.spark

import com.project.spark.infrastructure.{CitizenDataset, PeaceWatcherDataset}
import com.project.spark.service.{MessageService, ConsumerService}
import org.apache.spark.sql.SparkSession
import com.project.spark.model.{Message, PeaceWatcher, Report}
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  //services
  val messageService = new MessageService
  val peaceWatcherDataset = new PeaceWatcherDataset
  val citizenDataset = new CitizenDataset
  val consumerService =  new ConsumerService


  def main(args: Array[String]): Unit = {
    println("Peaceland Project - Consumer-Database")


  }
}
