package com.project.spark.model
import java.time.LocalDateTime
import com.google.gson._

sealed class Message

class Report(
              peaceWatcher: PeaceWatcher,
              citizens : List[Citizen],
              wordsHeard : List[String],
              time : String
              ) extends Message {

  override def toString(): String = {
    "peaceWatcher : " + peaceWatcher.id.toString + "\n" +
    "location : " + peaceWatcher.location.toString + "\n"+
    "citizen : " + citizens.map(c => c.name + " : score =" + c.peaceScore.value) + "\n " +
    "words heard : " + wordsHeard + "\n " +
    "time : " + time
  }

}
class Alert (
            peaceWatcher: PeaceWatcher,
            citizen : Citizen,
            time : String,
            ) extends Message {
  override def toString: String = {
    "\t\t Alert ! Alert ! \n"+
    "peaceWatcher : " + peaceWatcher.id.toString + "\n" +
    "location : " + peaceWatcher.location.toString + "\n"+
    " citizen " + citizen.name + " : score = " + citizen.peaceScore.value + "\n " +
      "time : " + time
  }
}
