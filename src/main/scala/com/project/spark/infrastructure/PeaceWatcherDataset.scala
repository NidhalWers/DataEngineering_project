package com.project.spark.infrastructure

import com.project.spark.model.{Coordinates, PeaceWatcher}
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

class PeaceWatcherDataset {

  private val random = new Random()

  private val pw0 = PeaceWatcher(0, Coordinates(random.nextInt(20), random.nextInt(20)))

  private val pw1 = PeaceWatcher(1, Coordinates(random.nextInt(20), random.nextInt(20)))

  private val pw2 = PeaceWatcher(2, Coordinates(random.nextInt(20), random.nextInt(20)))

  private val pw3 = PeaceWatcher(3, Coordinates(random.nextInt(20), random.nextInt(20)))


  private val conf = new SparkConf ()
    .setAppName ("Inverted index")
    .setMaster ("local[*]")

  private val sc = SparkContext.getOrCreate(conf)

  val peaceWatchersList = List[PeaceWatcher](pw0, pw1, pw2, pw3)
}