package com.project.spark.service

import java.io.StringWriter

import com.project.spark.model.{Alert, Citizen, Message, PeaceWatcher, Report}
import java.time.LocalDateTime

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import com.google.gson.Gson
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

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

  def parseFromJson(line:String):Report = {
    val mapper = new ObjectMapper with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    mapper.readValue(line, classOf[Report])
  }

  def parseToJson(line:Message):String = {
    val gson = new Gson

    val mapper = new ObjectMapper with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val out = new StringWriter
    mapper.writeValue(out, line)
    val json = out.toString
    json
  }


  def consumeMessage(message : Report): List[Alert] = {
    if( message.citizens.filter(c => c.peaceScore.isBad()).size > 0){
      message.citizens.filter(c => c.peaceScore.isBad())
        .map( c => Alert(message.peaceWatcher, c,dateTimeNow()) )
    }else{
      List()
    }

  }


}
