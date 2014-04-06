import sbt._
import Keys._

object BuildSettings {
  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "",
    version := "0.1.0",
    scalaVersion := "2.11.0-RC3",
    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    scalacOptions ++= Seq()
  )
}

object MyBuild extends Build {
  import BuildSettings._

  lazy val macros: Project = Project(
    "root",
    file("."),
    settings = buildSettings ++ Seq(
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _),
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-compiler" % _),
      compile in Compile := {
        sys.props("sbt.class.directory") = (classDirectory in Compile).value.getAbsolutePath
        (compile in Compile).value
      }
    )
  )
}