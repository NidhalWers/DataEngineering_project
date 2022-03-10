package com.project.spark.model

case class Message(
                  peaceWatcher: PeaceWatcher,
                  citizens : List[Citizen],
                  wordsHeard : List[String]
                  )
