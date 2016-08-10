import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  val projectName = "some-project-name"
  val projectVersion = "0.1"
  val projectOrg = "some-org"

  override def settings = super.settings ++ Seq(
    name := projectName,
    organization := projectOrg,
    version := projectVersion,
    scalaVersion in ThisBuild := "2.11.7",
    scalacOptions ++= Seq(
      "target:jvm-1.8",
      "-encoding", "utf8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xfatal-warnings",
      "-Xlint",
      "-Xfuture",
      "-Ywarn-adapted-args",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-dead-code"
    )
  )

  import MainDependencies._
  import TestDependencies._
  import ScalariformSettings._

  val validChars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') :+ '-' :+ '_'
  val rootId = (projectName + "-v" + projectVersion).map { c =>
    if (validChars.contains(c)) c
    else '_'
  }
  lazy val root = Project(id = rootId, base = file("."))
    .settings(
      customScalariformSettings,
      libraryDependencies ++= Seq(
        scalaz,
        scalatest
      )
    )
}

