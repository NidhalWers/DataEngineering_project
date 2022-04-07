package com.project.spark.service

import com.project.spark.infrastructure.MessageRepository
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, count, countDistinct, desc}

class AnalyzeService {

  val messageRepository = new MessageRepository;

  def analyze(sc : SparkSession): Unit = {

    val reportDF = messageRepository.getAllDate(sc);
    reportDF.show()

    val reportConatingCitizensDF = reportDF.withColumn("listCitizen",split(col("data"),","))
    val citizensDF = reportConatingCitizensDF.select(col("peaceWatcherId"),col("listCitizen"))
    val citizens = citizensDF.select(col("peaceWatcherId"),explode(col("listCitizen")))
    val citizensAndScores = citizens.withColumn("peaceScore",col("col").substr(-2,2)).withColumnRenamed("col","citizenData")
    val finalcitizendf = citizensAndScores.withColumn("citizenName",expr("substring(citizenData, 1, length(citizenData)-3)"))
    val finalcitizendfv2= finalcitizendf.withColumn("peaceScore", regexp_replace(col("peaceScore"), ":", "")).withColumn("peaceScore",col("peaceScore").cast("integer"))

    val days = reportDF.withColumn("day",col("time").substr(1,2))
    days.show()
    finalcitizendfv2.show()

    println("\n--------------------------STARTING ANALYSIS----------------------- \n")
    reportDF.select(countDistinct("peaceWatcherId").alias("How much distinct peaceWatchers are there in service?")).show()

    println("\n ---------- Which days has most reports ? ------------------ \n")
    days.groupBy(col("day")).agg(count("day").as("NbOfReports")).orderBy(desc("NbOfReports")).show(1)

    println("\n ---------- Worst citizens ? ------------------ \n")
    finalcitizendfv2.orderBy(col("peaceScore")).show(5)

    println("\n ---------- Best citizens ? ------------------ \n")
    finalcitizendfv2.orderBy(desc("peaceScore")).show(5)

  }

}
