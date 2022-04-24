package result

object Result {
  case class Matches(searchContext: String, indices: Seq[Int])
  case class FileSearchResult (filePath: String, matches: Seq[Matches])
  case class RequestResult(phrases: Seq[String], lang: Seq[String], status: String, results: Seq[FileSearchResult], errors: Seq[String])
}
