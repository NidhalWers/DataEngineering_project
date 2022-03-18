package com.project.spark.model

case class PeaceScore(
                     value : Int
                     ){
  def isBad() : Boolean = {
    value <= 10
  }
}

