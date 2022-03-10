package com.project.spark.model

case class PeaceWatcher(
                       id : Int,
                       location : Coordinates
                       ){

  def move(move: String): PeaceWatcher = move.toLowerCase match {
    case "up" => {
      val x = location.x
      val y = location.y + 1
      PeaceWatcher(this.id, Coordinates(x, y))
    }
    case "down" => {
      val x = location.x
      val y = location.y -1
      PeaceWatcher(this.id, Coordinates(x, y))
    }
    case "left" => {
      val x = location.x - 1
      val y = location.y
      PeaceWatcher(this.id, Coordinates(x, y))
    }
    case "right" => {
      val x = location.x + 1
      val y = location.y
      PeaceWatcher(this.id, Coordinates(x, y))
    }
  }
}

