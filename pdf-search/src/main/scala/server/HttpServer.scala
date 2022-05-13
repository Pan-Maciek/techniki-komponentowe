package server

import akka.actor.{ActorSystem, Terminated}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.DebuggingDirectives
import logic.FileSearcher
import result.Result.{FileSearchResult, Matches, RequestResult}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class HttpServer extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val queryFormat: RootJsonFormat[Query] = jsonFormat3(Query)
  implicit val paragraphResFormat: RootJsonFormat[Matches] = jsonFormat2(Matches)
  implicit val fileResFormat: RootJsonFormat[FileSearchResult] = jsonFormat2(FileSearchResult)
  implicit val resFormat: RootJsonFormat[RequestResult] = jsonFormat5(RequestResult)

  case class Query(phrases: Seq[String], lang: Seq[String], rootPath: String)

  implicit val system = ActorSystem()

  def routes: Route = {
    path("search") {
      get {
        parameters("phrases".as[Seq[String]], "lang".as[Seq[String]], "rootPath".as[String]) {
          (raw_phrases, lang, rootPath) =>
            val phrases = raw_phrases.map { URLDecoder.decode(_, "utf8") }
            try {
              val fileSearcher = new FileSearcher(rootPath)
              val res = fileSearcher.findInDir(phrases)
              complete(200, RequestResult(phrases, lang, "ok", res, fileSearcher.errors))
            } catch {
              case e: Throwable =>
                complete(200, RequestResult(phrases, lang, "error", Seq(), Seq(e.getMessage)))
            }

        }
      }
    }
  }

  def start(port: Int): Future[Terminated] = {
    Http().newServerAt("0.0.0.0", port).bind(routes)
    Await.ready(system.whenTerminated, Duration.Inf)
  }
}

object HttpServerApp extends App {
  new HttpServer().start(8183)
}
