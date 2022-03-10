package com.project.spark

import com.project.spark.service.MessageService

object Main {

  //services
  val messageService = new MessageService

  def main(args: Array[String]): Unit = {

    val messages = messageService.convertJsonFileToMessages("").toList
    //todo utiliser un RDD

    println("Peaceland Project")
  }
}
