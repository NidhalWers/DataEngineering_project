package com.project.spark.infrastructure

import com.project.spark.model.{Message, Report}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

class MessageRepository {

  def insert(message: Report) : Unit = {
    val sparkSession = SparkSession.builder().appName("insert4-db").getOrCreate()


    val data=Seq((message.peaceWatcher.id.toString(),message.citizens.toString(),message.wordsHeard.toString(),message.time)
    )
    val columns = Seq("peaceWatcher","citizens","wordsHeard","time")
    import sparkSession.implicits._
    val df = data.toDF(columns:_*)

    df.write.option("header",true).mode(SaveMode.Overwrite).csv("Storage/Messages.csv")

  }

  def select(query : String) : List[Message] = {
    Nil
  }

  def update(query : String) : Unit = {

  }

  def delete(query : String) : Unit = {

  }

}
