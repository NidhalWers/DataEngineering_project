package com.project.spark.infrastructure

import org.apache.spark.sql
import org.apache.spark.sql.{SparkSession}


class MessageRepository {

  def getAllDate(sc : SparkSession) : sql.DataFrame = {
    sc.read.option("inferSchema", "true").option("header","true").csv("../consumer-database/Storage/Messages.csv")
  }

}
