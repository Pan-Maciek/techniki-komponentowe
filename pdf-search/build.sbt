name := "pdf-search"

version := "0.1"

Compile/mainClass := Some("server.HttpServerApp")

scalaVersion := "2.13.8"
val akkaVersion     = "2.6.16"
val akkaHttpVersion = "10.2.6"

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-lang3" % "3.12.0",
  "org.apache.pdfbox" % "pdfbox" % "2.0.25",
  "com.typesafe.akka"        %% "akka-actor"                % akkaVersion,
  "com.typesafe.akka"        %% "akka-persistence"          % akkaVersion,
  "com.typesafe.akka"        %% "akka-persistence-query"    % akkaVersion,
  "com.typesafe.akka"        %% "akka-persistence-typed"    % akkaVersion,
  "com.typesafe.akka"        %% "akka-cluster-typed"        % akkaVersion,
  "com.typesafe.akka"        %% "akka-http"                 % akkaHttpVersion,
  "com.typesafe.akka"        %% "akka-http-spray-json"      % akkaHttpVersion,
)
