package com.project.spark.service

import com.project.spark.model.{Alert, Citizen, Message, PeaceWatcher, Report}
import java.time.LocalDateTime

import com.google.gson.Gson

class MessageService {

  private val wordsDataset = scala.io.Source.fromFile("resources/words.txt", "utf-8")
    .getLines()
    .flatMap( l => l.split(" ", -1))
    .toList

  private def dateTimeNow(): String ={
    LocalDateTime.now().toString.replace("T","_").replace("-","").replace(":","")
  }

  def generateMessage(citizens : List[Citizen], peaceWatchers: PeaceWatcher) : Message = {
    val nCitizen = scala.util.Random.nextInt(4)
    val citizenForMessage = scala.util.Random.shuffle(citizens).take(nCitizen)

    val nWords = scala.util.Random.nextInt(10)
    val wordsForMessage = scala.util.Random.shuffle(wordsDataset).take(nWords)

    new Report(peaceWatchers, citizenForMessage, wordsForMessage, dateTimeNow())
  }

  def parseFromJson(line:String):Message = {
    val gson = new Gson
    gson.fromJson(line, classOf[Message])
  }

  def parseToJson(line:Message):String = {
    val gson = new Gson
    gson.toJson(line)
  }

  /*
  def consumeMessage(): Unit ={

    if( citizenForMessage.filter(c => c.peaceScore.isBad()).size > 0){
      citizenForMessage.filter(c => c.peaceScore.isBad())
        .map( c => new Alert(peaceWatchers, c,dateTimeNow()) )
    }else {
  }
  */

}
