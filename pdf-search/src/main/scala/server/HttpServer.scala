package server

import akka.actor.{ActorSystem, Terminated}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import logic.FileSearcher
import result.Result.{FileSearchResult, ParagraphSearchResult, RequestResult}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class HttpServer extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val queryFormat: RootJsonFormat[Query] = jsonFormat2(Query)
  implicit val paragraphResFormat: RootJsonFormat[ParagraphSearchResult] = jsonFormat2(ParagraphSearchResult)
  implicit val fileResFormat: RootJsonFormat[FileSearchResult] = jsonFormat2(FileSearchResult)
  implicit val resFormat: RootJsonFormat[RequestResult] = jsonFormat4(RequestResult)

  case class Query(phrase: String, rootPath: String)

  implicit val system = ActorSystem()

  def routes: Route = {
    path("search") {
      get {
        parameters("phrase".as[String], "path".as[String]) {
          (phrase, path) =>
            try {
              val fileSearcher = new FileSearcher(path)
              val res = fileSearcher.findInDir(phrase)
              complete(200, RequestResult(phrase, "ok", res, fileSearcher.errors))
            } catch {
              case e: Throwable =>
                complete(200, RequestResult(phrase, "error", Seq(), Seq(e.getMessage)))
            }

        }
      }
    }
  }

  def start(port: Int): Future[Terminated] = {
    Http().newServerAt("localhost", port).bind(routes)
    Await.ready(system.whenTerminated, Duration.Inf)
  }
}

object HttpServerApp extends App {
  new HttpServer().start(8183)
}
