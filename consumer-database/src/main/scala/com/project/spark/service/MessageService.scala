package com.project.spark.service

import java.io.StringWriter

import com.project.spark.model.{Citizen, Message, PeaceWatcher, Report}
import java.time.LocalDateTime

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import com.google.gson.Gson

class MessageService {


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

  /*
  def consumeMessage(): Unit ={

    if( citizenForMessage.filter(c => c.peaceScore.isBad()).size > 0){
      citizenForMessage.filter(c => c.peaceScore.isBad())
        .map( c => new Alert(peaceWatchers, c,dateTimeNow()) )
    }else {
  }
  */

}
