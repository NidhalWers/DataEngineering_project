package com.project.spark.infrastructure

import com.project.spark.model.{Message, Report}
import org.apache.spark.sql.{SaveMode, SparkSession}


class MessageRepository {

  def insert(message: Report) : Unit = {
    val sparkSession = SparkSession.builder().appName("insert4-db").getOrCreate()


    val data=Seq((message.peaceWatcher.id.toString(),message.citizens.foldLeft(""){(acc, c) => acc+"|"+c.toString},message.wordsHeard.foldLeft(""){(acc, w) => acc+"|"+w.toString},message.time)
    )
    val columns = Seq("peaceWatcher","citizens","wordsHeard","time")
    import sparkSession.implicits._
    val df = data.toDF(columns:_*)

    //val oldData =  getAllDate()
    //val fin = df.union(oldData)


    //df.write.option("header",true).mode(SaveMode.Overwrite).csv("Storage/Messages.csv")
    df.write.option("header",true).mode(SaveMode.Append).csv("Storage/Messages.csv")
  }

  def select(query : String) : List[Message] = {
    Nil
  }

  def update(query : String) : Unit = {

  }

  def delete(query : String) : Unit = {

  }

  def getAllDate(){
    val sparkSession = SparkSession.builder().appName("getAllData-db").getOrCreate()
    sparkSession.read.option("inferSchema", "true").option("header","true").csv("Storage/Messages.csv").show()
  }

}
