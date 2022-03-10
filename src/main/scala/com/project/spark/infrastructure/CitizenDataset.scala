package com.project.spark.infrastructure

import com.project.spark.model.Citizen

import scala.util.Random

class CitizenDataset {
  val random = new Random()

  val c0 = Citizen("citizen_0", random.nextInt(10) )

  val c1 = Citizen("citizen_1", random.nextInt(10) )

  val c2 = Citizen("citizen_2", random.nextInt(10) )

  val c3 = Citizen("citizen_3", random.nextInt(10) )

  val c4 = Citizen("citizen_4", random.nextInt(10) )

  val c5 = Citizen("citizen_5", random.nextInt(10) )

  val c6 = Citizen("citizen_6", random.nextInt(10) )

  val c7 = Citizen("citizen_7", random.nextInt(10) )

  val c8 = Citizen("citizen_8", random.nextInt(10) )

  val c9 = Citizen("citizen_9", random.nextInt(10) )

}
