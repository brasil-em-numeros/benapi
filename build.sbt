import Dependencies.Libraries._

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    organization := "brasil-em-numeros",
    name := "benapi",
    version := "0.0.1",
    scalaVersion := "2.13.1",
    maxErrors := 3,
    libraryDependencies ++= Seq(
        zio,
        zioStreams,
        zioInteropCats,
        zioLogging,
        http4sServer,
        http4sDsl,
        http4sClient,
        http4sCirce,
        circeCore,
        circeGeneric,
        circeParser,
        quillJdbc,
        doobieCore,
        doobieQuill,
        doobieH2,
        pureConfig,
        h2,
        logback,

        zioTestSbt
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

scalacOptions --= Seq(
    "-Xfatal-warnings"
)

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("chk", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
