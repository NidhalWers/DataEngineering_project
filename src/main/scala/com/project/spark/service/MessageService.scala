package com.project.spark.service

import com.project.spark.model.{Citizen, Message, PeaceWatcher}
import org.apache.spark.rdd.RDD

class MessageService {

  private val wordsDataset = scala.io.Source.fromFile("resources/words.txt", "utf-8")
    .getLines()
    .flatMap( l => l.split(" ", -1))
    .toList

  def generateMessage(citizens : RDD[Citizen], peaceWatchers: PeaceWatcher) : Message = {
    val nCitizen = scala.util.Random.nextInt(4)
    val citizenForMessage = scala.util.Random.shuffle(citizens.collect.toList).take(nCitizen)
    val nWords = scala.util.Random.nextInt(10)
    val wordsForMessage = scala.util.Random.shuffle(wordsDataset).take(nWords)
    Message(peaceWatchers, citizenForMessage, wordsForMessage)
  }

}
