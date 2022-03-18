package com.project.spark.service

import com.project.spark.model.{Citizen, Report, PeaceWatcher}
import org.apache.spark.rdd.RDD

class MessageService {

  private val wordsDataset = scala.io.Source.fromFile("resources/words.txt", "utf-8")
    .getLines()
    .flatMap( l => l.split(" ", -1))
    .toList

  def generateMessage(citizens : List[Citizen], peaceWatchers: PeaceWatcher) : Report = {
    val nCitizen = scala.util.Random.nextInt(4)
    val citizenForMessage = scala.util.Random.shuffle(citizens).take(nCitizen)
    val nWords = scala.util.Random.nextInt(10)
    val wordsForMessage = scala.util.Random.shuffle(wordsDataset).take(nWords)
    new Report(peaceWatchers, citizenForMessage, wordsForMessage)
  }

}
