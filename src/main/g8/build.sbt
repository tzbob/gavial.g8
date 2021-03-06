resolvers in ThisBuild += "Sonatype OSS Snapshots" at
"https://oss.sonatype.org/content/repositories/snapshots"

organization in ThisBuild := "$organization$"
scalaVersion in ThisBuild := "2.12.4"
version in ThisBuild := "0.0.2-SNAPSHOT"

scalacOptions in ThisBuild ++= Seq(
  "-feature",
  "-Yno-adapted-args",
  "-Xfuture",
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:implicitConversions"
)

lazy val $name;format="word"$ = crossProject
  .in(file("."))
  .settings(
    name := "$name;format="norm"$",
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    libraryDependencies ++= Seq("be.tzbob" %%% "gavial" % "0.4.2-SNAPSHOT"),
    scalaJSUseMainModuleInitializer := true
  )

lazy val $name;format="word"$JS = $name;format="word"$.js
  .settings(WebKeys.packagePrefix in Assets := "content/")
  .enablePlugins(ScalaJSBundlerPlugin)

lazy val $name;format="word"$JVM = $name;format="word"$.jvm
  .settings(
    scalaJSProjects := Seq($name;format="word"$JS),
    // Automatically package JS when JVM is compiled (optional)
    pipelineStages in Assets := Seq(scalaJSPipeline),
    managedClasspath in Runtime += (packageBin in Assets).value
  )
  .enablePlugins(WebScalaJSBundlerPlugin)

// Automatically package JS when JVM is compiled (optional)
onLoad in Global ~= (_ andThen ("project $name;format="word"$JVM" :: _))
