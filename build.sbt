name := "gscr"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.9.8"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.10.1"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.1"
libraryDependencies += "org.projectlombok" % "lombok" % "1.18.8" % "provided"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.7"
libraryDependencies += "com.softwaremill.sttp.client" %% "json4s" % "2.0.1"
libraryDependencies += "com.softwaremill.sttp.model" %% "core" % "1.0.0"
libraryDependencies += "com.softwaremill.sttp.client" %% "core" % "2.0.1"