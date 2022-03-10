package com.project.spark.model

case class Message(
                  peaceWatcher: PeaceWatcher,
                  citizens : List[Citizen],
                  wordsHeard : List[String]
                  )

object Message{
  def apply(body : String): Message = {
    new Message(
      null,
      null,
      null
    )
  }
}