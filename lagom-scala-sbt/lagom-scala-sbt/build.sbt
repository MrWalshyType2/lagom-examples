organization in ThisBuild := "org.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.13.0"

// https://github.com/ghik/silencer
val silencerVersion = "1.6.0"

libraryDependencies in Global ++= Seq(
  compilerPlugin("com.github.ghik" % "silencer-plugin" % silencerVersion cross CrossVersion.full),
  "com.github.ghik" % "silencer-lib" % silencerVersion % Provided cross CrossVersion.full
)

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lagomServiceGatewayImpl in ThisBuild := "akka-http"

lazy val `greetingapi` = (project in file("."))
  .aggregate(`weatherapi-api`, `weatherapi-impl`, `lagom-play-gateway`)

lazy val `greetingapi-api` = (project in file("greetingapi-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `greetingapi-impl` = (project in file("greetingapi-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`greetingapi-api`, `weatherapi-api`)

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

lazy val `lagom-play-gateway` = (project in file("lagom-play-gateway"))
  .enablePlugins(PlayScala, LagomPlay)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslServer,
      lagomScaladslAkkaDiscovery,
      lagomScaladslTestKit,
      macwire,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,

      // https://github.com/webjars/bootstrap
      // https://github.com/webjars/bootstrap/releases
      "org.webjars" % "bootstrap" % "3.3.6",

      // https://github.com/webjars/webjars-play
      // https://github.com/webjars/webjars-play/releases
      "org.webjars" %% "webjars-play" % "2.8.0",

      // https://github.com/webjars/foundation
      // https://github.com/webjars/foundation/releases
      "org.webjars" % "foundation" % "6.4.3-1",

      // https://github.com/webjars/foundation-icon-fonts
      // https://github.com/webjars/foundation-icon-fonts/releases
      "org.webjars" % "foundation-icon-fonts" % "d596a3cfb3",

      // https://github.com/webjars/swagger-ui/releases
      "org.webjars" % "swagger-ui" % "3.23.8"
    ),
    // https://www.playframework.com/documentation/2.8.x/AssetsOverview#Reverse-routing-and-fingerprinting-for-public-assets
//    pipelineStages := Seq(rjs, digest, gzip)
    )
  .dependsOn(`weatherapi-api`,`greetingapi-api`)

//def project(id: String) = Project(id, base = file(id))
