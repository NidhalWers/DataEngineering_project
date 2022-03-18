package com.project.spark.model

case class PeaceWatcher(
                       id : Int,
                       location : Coordinates,
                       movements : List[String]
                       ){

  def move(move: Int): PeaceWatcher = movements(move).toLowerCase match {
    case "up" => {
      val x = location.x
      val y = location.y + 1
      PeaceWatcher(this.id, Coordinates(x, y), this.movements)
    }
    case "down" => {
      val x = location.x
      val y = location.y -1
      PeaceWatcher(this.id, Coordinates(x, y), this.movements)
    }
    case "left" => {
      val x = location.x - 1
      val y = location.y
      PeaceWatcher(this.id, Coordinates(x, y), this.movements)
    }
    case "right" => {
      val x = location.x + 1
      val y = location.y
      PeaceWatcher(this.id, Coordinates(x, y), this.movements)
    }
  }
}

