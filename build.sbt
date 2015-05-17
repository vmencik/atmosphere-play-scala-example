name := "chat"

version := "1.0"

scalaVersion := "2.11.6"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += "org.atmosphere" % "atmosphere-play" % "2.1.2"
