ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / organization     := "com.example"

lazy val root = (project in file("."))
  .settings(
    name := "Ostsee",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.19",
      "dev.zio" %% "zio-test" % "2.0.19" % Test,
      "dev.zio" %% "zio-http" % "3.0.0-RC3"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
