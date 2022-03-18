package com.project.spark.infrastructure

import com.project.spark.model.{Coordinates, PeaceWatcher}
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

class PeaceWatcherDataset {



  private val movementFilePath = "resources/DroneMovement"
  private val movement = scala.io.Source.fromFile(movementFilePath, "utf-8").getLines().map( l => l.split(",")).toList

  private val random = new Random()

  private val pw0 = PeaceWatcher(0, Coordinates(random.nextInt(20), random.nextInt(20)), movement(0).toList)

  private val pw1 = PeaceWatcher(1, Coordinates(random.nextInt(20), random.nextInt(20)), movement(1).toList)

  private val pw2 = PeaceWatcher(2, Coordinates(random.nextInt(20), random.nextInt(20)), movement(2).toList)

  private val pw3 = PeaceWatcher(3, Coordinates(random.nextInt(20), random.nextInt(20)), movement(3).toList)

  val peaceWatchersList = List[PeaceWatcher](pw0, pw1, pw2, pw3)
}