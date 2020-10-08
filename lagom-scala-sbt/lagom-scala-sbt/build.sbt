organization in ThisBuild := "org.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.13.0"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lazy val `greetingapi` = (project in file("."))
  .aggregate(`weatherapi-api`, `weatherapi-impl`)

lazy val `weatherapi-api` = (project in file("weatherapi-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `weatherapi-impl` = (project in file("weatherapi-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`weatherapi-api`)

//def project(id: String) = Project(id, base = file(id))
