package com.project.spark

import com.project.spark.infrastructure.{CitizenDataset, PeaceWatcherDataset}
import com.project.spark.service.MessageService
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  //services
  val messageService = new MessageService
  val peaceWatcherDataset = new PeaceWatcherDataset
  val citizenDataset = new CitizenDataset

  def main(args: Array[String]): Unit = {
    println("Peaceland Project")

    val conf = new SparkConf ()
      .setAppName ("Peaceland Project")
      .setMaster ("local[*]")

    val sc = SparkContext.getOrCreate(conf)

    val peaceWatchers = sc.parallelize(peaceWatcherDataset.peaceWatchersList)
    peaceWatchers.map(pw => pw.move("up"))
      .map(pw => (pw, messageService.generateMessage(citizenDataset.citizensList, pw)))
      .map((tuple) => (tuple._1, println(tuple._2.toString) ))
    //val movementFilePath = ""

    //val movement = sc.textFile(movementFilePath).map( l => l.split(","))
  }
}
