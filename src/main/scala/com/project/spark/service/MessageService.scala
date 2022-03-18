package com.project.spark.service

import com.project.spark.model.{Alert, Citizen, Message, PeaceWatcher, Report}
import org.apache.spark.rdd.RDD

class MessageService {

  private val wordsDataset = scala.io.Source.fromFile("resources/words.txt", "utf-8")
    .getLines()
    .flatMap( l => l.split(" ", -1))
    .toList

  def generateMessage(citizens : List[Citizen], peaceWatchers: PeaceWatcher) : List[Message] = {
    val nCitizen = scala.util.Random.nextInt(4)
    val citizenForMessage = scala.util.Random.shuffle(citizens).take(nCitizen)
    if( citizenForMessage.filter(c => c.peaceScore.isBad()).size > 0){
      citizenForMessage.filter(c => c.peaceScore.isBad())
        .map( c => new Alert(peaceWatchers, c) )
    }else {
      val nWords = scala.util.Random.nextInt(10)
      val wordsForMessage = scala.util.Random.shuffle(wordsDataset).take(nWords)
      List(new Report(peaceWatchers, citizenForMessage, wordsForMessage))
    }
  }

}
