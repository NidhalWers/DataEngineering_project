package com.project.spark.service

import com.project.spark.infrastructure.MessageRepository
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{col, count, countDistinct, desc}

class AnalyzeService {

  val messageRepository = new MessageRepository;

  def analyze(sc : SparkSession): Unit = {

    val reportDF = messageRepository.getAllDate(sc);
    reportDF.show()

    val hours = reportDF.withColumn("hours",col("time").substr(10,2))
    val numberOfWords = reportDF.withColumn("nb_words", functions.size(functions.split(col("wordsHeard"),"|")))
    hours.show()
    numberOfWords.show()

    println("\n--------------------------STARTING ANALYSIS----------------------- \n")
    reportDF.select(countDistinct("peaceWatcher").alias("How much distinct peaceWatchers are there in service?")).show()

    println("\n ---------- Which hours has most reports ? ------------------ \n")
    hours.groupBy(col("hours")).agg(count("hours").as("NbOfReports")).orderBy(desc("NbOfReports")).show(1)

    println("\n ---------- Message with less words ? ------------------ \n")
    numberOfWords.orderBy(col("nb_words")).show(5)

    println("\n ---------- Message with most words ? ------------------ \n")
    numberOfWords.orderBy(desc("nb_words")).show(5)



  }

}
