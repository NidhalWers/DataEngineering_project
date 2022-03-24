package com.project.spark.infrastructure

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import com.project.spark.model.Message

class JsonHandler {

  //create object mapper
  private val mapper = new ObjectMapper with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)


  def messageTojson(message : Message): String = {
    mapper.writeValueAsString(message)
  }

}
