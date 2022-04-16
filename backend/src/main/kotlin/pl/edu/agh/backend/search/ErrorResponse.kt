package pl.edu.agh.backend.search

class ErrorResponse(val path : String, val status : String = "error", val results : List<Any> = emptyList(), val errors : List<String>)