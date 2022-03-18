package com.project.spark.model

sealed class Message

class Report(
              peaceWatcher: PeaceWatcher,
              citizens : List[Citizen],
              wordsHeard : List[String]
              ) extends Message {

  override def toString(): String = {
    " peaceWatcher : " + peaceWatcher.id.toString + "\n" +
      "citizen : " + citizens.map(c => c.name + " : score =" + c.peaceScore.value) + "\n " +
      "words heard : " + wordsHeard

  }

}
class Alert (
            peaceWatcher: PeaceWatcher,
            citizen : Citizen
            ) extends Message {
  override def toString: String = {
    "\t\t Alert ! Alert ! \n"+
    " peaceWatcher : " + peaceWatcher.id.toString + "\n" +
    " citizen " + citizen.name + " : score = " + citizen.peaceScore.value
  }
}
