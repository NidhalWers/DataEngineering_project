package com.project.spark.model

case class Coordinates(
                      x : Int,
                      y : Int
                      ){
  override def toString: String = {
    "("+x+","+y+")"
  }
}