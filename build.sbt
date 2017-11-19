import Dependencies._

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    inThisBuild(List(
      organization := "com.ruchij",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "password-cracker-kata",
    libraryDependencies += scalaTest % "it, test",
    coverageEnabled := true
  )