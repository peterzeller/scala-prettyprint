import Dependencies._

ThisBuild / scalaVersion     := "3.0.0"
ThisBuild / version          := "0.2.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "scala-prettyprint",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.26" % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
