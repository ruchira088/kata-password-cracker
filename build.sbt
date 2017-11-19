import Dependencies._

lazy val MyIntegrationTests = config("it") extend Test

lazy val root = (project in file("."))
  .configs(MyIntegrationTests)
  .settings(
    inThisBuild(List(
      organization := "com.ruchij",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    inConfig(MyIntegrationTests)(Defaults.testSettings),
    name := "password-cracker-kata",
    libraryDependencies += scalaTest % "it, test",
    coverageEnabled := true
  )

addCommandAlias("fullTestSuite", "; clean; compile; test; it:test; coverageReport")