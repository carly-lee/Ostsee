ThisBuild / scalaVersion     := "3.3.1"
ThisBuild / organization     := "com.example"

val circeVersion = "0.14.1"

lazy val root = (project in file("."))
  .settings(
    name := "Ostsee",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.19",
      "dev.zio" %% "zio-test" % "2.0.19" % Test,
      "dev.zio" %% "zio-http" % "3.0.0-RC3",
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
