package result

object Result {
  case class ParagraphSearchResult(searchContext: String, indices: Seq[Int])
  case class FileSearchResult (rootPath: String, paragraphSearchResults: Seq[ParagraphSearchResult])
  case class RequestResult(phrase: String, status: String, results: Seq[FileSearchResult], errors: Seq[String])
}
