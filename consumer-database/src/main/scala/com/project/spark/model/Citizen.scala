package com.project.spark.model

case class Citizen(
                  name : String,
                  peaceScore : PeaceScore,
                  ){

  def updatePeaceScore(): Citizen = {
    val sign = scala.util.Random.nextInt(2)
    val change = scala.util.Random.nextInt(10)
    sign match {
      case 0 => Citizen(this.name, PeaceScore(this.peaceScore.value - change))
      case 1 => Citizen(this.name, PeaceScore(this.peaceScore.value + change))
    }
  }

  override def toString: String = {
    name+","+peaceScore.value
  }
}

