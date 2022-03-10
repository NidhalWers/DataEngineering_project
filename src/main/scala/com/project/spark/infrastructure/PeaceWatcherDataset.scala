package com.project.spark.infrastructure

import com.project.spark.model.{Coordinates, PeaceWatcher}

import scala.util.Random

class PeaceWatcherDataset {

  val random = new Random()

  val pw0 = new PeaceWatcher(0, new Coordinates(random.nextInt(20), random.nextInt(20)))

  val pw1 = new PeaceWatcher(1, new Coordinates(random.nextInt(20), random.nextInt(20)))

  val pw2 = new PeaceWatcher(2, new Coordinates(random.nextInt(20), random.nextInt(20)))

  val pw3 = new PeaceWatcher(3, new Coordinates(random.nextInt(20), random.nextInt(20)))

}