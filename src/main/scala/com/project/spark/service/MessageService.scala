package com.project.spark.service

import com.project.spark.model.{Citizen, Message, PeaceWatcher}

class MessageService {

  val wordsDataset = scala.io.Source.fromFile("", "utf-8")
    .getLines()
    .flatMap( l => l.split(" ", -1))
    .toList

  def generateXMessages(numberOfMessages : Int, citizens : List[Citizen], peaceWatchers: List[PeaceWatcher]) : List[Message] = {
    def aux(numberOfMessages : Int, citizens : List[Citizen], peaceWatchers: List[PeaceWatcher], acc : List[Message]) : List[Message] = numberOfMessages match {
      case 0 => acc
      case x => {
        val nCitizen = scala.util.Random.nextInt(4)
        val citizenForMessage = scala.util.Random.shuffle(citizens).take(nCitizen)
        val peaceWatcherForMessage = scala.util.Random.shuffle(peaceWatchers).head
        val nWords = scala.util.Random.nextInt(10)
        val wordsForMessage = scala.util.Random.shuffle(wordsDataset).take(nWords)
        aux(x-1, citizens, peaceWatchers, new Message(, peaceWatcherForMessage, citizenForMessage, wordsForMessage):: acc)
      }
    }
    aux(numberOfMessages, citizens, peaceWatchers, List[Message]())
  }

}
