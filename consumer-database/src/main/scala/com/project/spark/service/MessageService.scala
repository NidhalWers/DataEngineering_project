package com.project.spark.service

import com.project.spark.model.{Citizen, Message, PeaceWatcher, Report}

import java.time.LocalDateTime

import com.google.gson.Gson

class MessageService {


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
