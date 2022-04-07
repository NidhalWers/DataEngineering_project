package com.project.spark.infrastructure

import com.project.spark.model.{Message, Report}
import org.apache.spark.sql
import org.apache.spark.sql.{SaveMode, SparkSession}


class MessageRepository {


  def select(query : String) : List[Message] = {
    Nil
  }

  def getAllDate(sc : SparkSession) : sql.DataFrame = {
    sc.read.option("inferSchema", "true").option("header","true").csv("../consumer-database/Storage/Messages.csv")
  }

}
