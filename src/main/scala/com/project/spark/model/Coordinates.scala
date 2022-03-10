package com.project.spark.model

case class Coordinates(
                      x : Double,
                      y : Double
                      )

object Coordinates{
  def apply(x: Double, y: Double): Coordinates = new Coordinates(x, y)
}