package com.project.spark

import com.project.spark.infrastructure.{CitizenDataset, PeaceWatcherDataset}
import com.project.spark.service.{MessageService, ProducerService}
import com.project.spark.model.{Message, PeaceWatcher, Report}

object Main {

  //services
  val messageService = new MessageService
  val peaceWatcherDataset = new PeaceWatcherDataset
  val citizenDataset = new CitizenDataset
  val producerService =  new ProducerService


  def main(args: Array[String]): Unit = {
    println("Peaceland Project - Producer")

    def action(moveIndex : Int):List [(PeaceWatcher,Message)]={
      peaceWatcherDataset.peaceWatchersList.map(pw => pw.move(moveIndex))
        .map(pw => (pw, messageService.generateMessage(citizenDataset.getCitizenList(), pw)))
        //.map((tuple) => (tuple._1, println(tuple._2.toString) ))
    }

    def makeAction(acc:Int):Unit = acc match{
      case 0 => println("End actions")
      case _ => action(acc)
        //.foreach(x => x._2.toString()+ "\n")
        .map(x => (x._1.id, messageService.parseToJson(x._2)) )
        .foreach( x => producerService.sendMessage(x._1.toString, x._2) )
        Thread.sleep(3000)
        makeAction(acc-1)
    }

    makeAction(3)

    producerService.closeProducer()
    println("End program")
  }
}
