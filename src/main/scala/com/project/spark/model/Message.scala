package com.project.spark.model

case class Message(
                  peaceWatcher: PeaceWatcher,
                  citizens : List[Citizen],
                  wordsHeard : List[String]
                  ){

  override def toString():String = {
    " peaceWatcher : "+peaceWatcher.id.toString+"\n" +
      "citizen : "+citizens.map(c => c.name)+"\n " +
      "words heard : "+wordsHeard
  }
}
