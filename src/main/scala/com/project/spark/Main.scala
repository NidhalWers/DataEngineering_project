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

    def action(moveIndex:Int)={
      peaceWatchers.map(pw => pw.move(moveIndex))
        .map(pw => (pw, messageService.generateMessage(citizenDataset.citizensList, pw)))
        .map((tuple) => (tuple._1, println(tuple._2.toString) ))
    }

    def makeAction(acc:Int):Unit = acc match{
      case 0 => "End actions"
      case _ => action(acc)
        makeAction(acc-1)
        Thread.sleep(30000)
    }
    makeAction(3)



  }
}
