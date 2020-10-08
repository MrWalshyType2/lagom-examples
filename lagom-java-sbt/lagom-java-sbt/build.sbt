organization in ThisBuild := "org.example" // package
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.13.0"

// microservices - two parts, api & implementation
lazy val `inventory` = (project in file("."))
  .aggregate(`product-api`, `product-impl`)

// ...`api name` = (...(file name))
lazy val `product-api` = (project in file("product-api"))
  .settings(common)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `product-impl` = (project in file("product-impl"))
  .enablePlugins(LagomJava)
  .settings(common)
  .settings(
    libraryDependencies ++= Seq(
      //lagomJavadslPersistenceCassandra, // open-source nosql db
      //lagomJavadslKafkaBroker, // stream-processing implementation
      lagomLogback,
      lagomJavadslTestKit, // for testing
      lombok
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`product-api`)

val lombok = "org.projectlombok" % "lombok" % "1.18.8"

def common = Seq(
  javacOptions in Compile += "-parameters"
)

lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false
