name := "mameclub-gatling"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.3"

libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.1.3"

val test = project.in(file("."))
  .enablePlugins(GatlingPlugin)
//  .settings(libraryDependencies ++= /* Gatling dependencies */)
