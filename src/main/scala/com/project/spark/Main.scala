package com.project.spark

import com.project.spark.infrastructure.{CitizenDataset, PeaceWatcherDataset}
import com.project.spark.service.MessageService
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  //services
  val messageService = new MessageService
  val peaceWatcherDataset = new PeaceWatcherDataset
  val citizenDataset = new CitizenDataset

  def main(args: Array[String]): Unit = {
    println("Peaceland Project")
    val spark = SparkSession.builder().appName("Peaceland Project")
      .master("local[*]")
      .getOrCreate()
    val sc = spark.sparkContext

    // val sc = SparkContext.getOrCreate(conf)

    val peaceWatchers = sc.parallelize(peaceWatcherDataset.peaceWatchersList)

    def action(moveIndex : Int)={
      peaceWatchers.map(pw => pw.move(moveIndex))
        .map(pw => (pw, messageService.generateMessage(citizenDataset.citizensList, pw)))
        .map((tuple) => (tuple._1, println(tuple._2.toString) ))
    }

    def makeAction(acc:Int):Unit = acc match{
      case 0 => print("End actions")
      case _ => action(acc)
        makeAction(acc-1)
        Thread.sleep(30000)
    }
    makeAction(3)



  }
}
