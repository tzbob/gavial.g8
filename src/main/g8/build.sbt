resolvers in ThisBuild += "Sonatype OSS Snapshots" at
"https://oss.sonatype.org/content/repositories/snapshots"

ThisBuild / organization := "$organization$"
ThisBuild / scalaVersion := "2.12.4"
ThisBuild / version := "0.0.1-SNAPSHOT"

scalacOptions in ThisBuild ++= Seq(
  "-feature",
  "-Yno-adapted-args",
  "-Xfuture",
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:implicitConversions"
)

lazy val $name$ = crossProject
  .in(file("."))
  .settings(
    name := "$name$",
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    libraryDependencies ++= Seq("be.tzbob" %%% "gavial" % "0.4.0-SNAPSHOT"),
    scalaJSUseMainModuleInitializer := true
  )

lazy val $name$JS = $name$.js
  .settings(WebKeys.packagePrefix in Assets := "content/")
  .enablePlugins(ScalaJSBundlerPlugin, ScalaJSWeb)

lazy val $name$JVM = $name$.jvm
  .settings(
    scalaJSProjects := Seq($name$JS),
    // Automatically package JS when JVM is compiled (optional)
    pipelineStages in Assets := Seq(scalaJSPipeline),
    managedClasspath in Runtime += (packageBin in Assets).value
  )
  .enablePlugins(WebScalaJSBundlerPlugin)

// Automatically package JS when JVM is compiled (optional)
onLoad in Global ~= (_ andThen ("project $name$JVM" :: _))
