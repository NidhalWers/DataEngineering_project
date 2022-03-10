package com.project.spark.infrastructure

import com.project.spark.model.{Citizen, PeaceScore}
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

class CitizenDataset {
  private val random = new Random()

  private val c0 = Citizen("citizen_0", PeaceScore(random.nextInt(10) ))

  private val c1 = Citizen("citizen_1", PeaceScore(random.nextInt(10) ))

  private val c2 = Citizen("citizen_2", PeaceScore(random.nextInt(10) ))

  private val c3 = Citizen("citizen_3", PeaceScore(random.nextInt(10) ))

  private val c4 = Citizen("citizen_4", PeaceScore(random.nextInt(10) ))

  private val c5 = Citizen("citizen_5", PeaceScore(random.nextInt(10) ))

  private val c6 = Citizen("citizen_6", PeaceScore(random.nextInt(10) ))

  private val c7 = Citizen("citizen_7", PeaceScore(random.nextInt(10) ))

  private val c8 = Citizen("citizen_8", PeaceScore(random.nextInt(10) ))

  private val c9 = Citizen("citizen_9", PeaceScore(random.nextInt(10) ))

  private val conf = new SparkConf ()
    .setAppName ("Inverted index")
    .setMaster ("local[*]")

  private val sc = SparkContext.getOrCreate(conf)

  val citizensList = sc.parallelize(List[Citizen](c0, c1, c2, c3, c4, c5, c6, c7, c8, c9))

}
