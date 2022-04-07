package com.project.spark

import com.project.spark.service.{AnalyzeService}
import org.apache.spark.sql.SparkSession
import org.apache.spark.api.java.JavaSparkContext.fromSparkContext

object Main {

  //services
  val analyzeService = new AnalyzeService;


  def main(args: Array[String]): Unit = {
    println("Peaceland Project - Analyze")
    val spark = SparkSession.builder().appName("Peaceland Project - Analyzer")
      .master("local[*]")
      .getOrCreate()

    val sc = spark.sparkContext


    analyzeService.analyze(spark);

    println("End program")
    sc.close()
  }
}
