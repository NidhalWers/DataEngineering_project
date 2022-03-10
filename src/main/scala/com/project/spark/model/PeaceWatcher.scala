package com.project.spark.model

case class PeaceWatcher(
                       id : Int,
                       location : Coordinates
                       )

object PeaceWatcher{
  def apply(body : String) : PeaceWatcher = {
    new PeaceWatcher(0, null)
  }
}