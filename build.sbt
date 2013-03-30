name := "machines"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.0-M9"

libraryDependencies += "org.scalaz" %% "scalaz-effect" % "7.0.0-M9"

libraryDependencies += "org.scalaz" %% "scalaz-scalacheck-binding" % "7.0.0-M9" % "test"

libraryDependencies += "org.typelevel" %% "scalaz-specs2" % "0.1.2" % "test"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.2", "2.10.1")

scalacOptions ++= Seq("-deprecation", "-unchecked")

scalacOptions <++= scalaVersion map {
  case sv if sv.contains("2.10") =>
    Seq("-feature", "-language:implicitConversions", "-language:higherKinds", "-language:existentials", "-language:postfixOps")
  case _ =>
    Seq("-Ydependent-method-types")
}
