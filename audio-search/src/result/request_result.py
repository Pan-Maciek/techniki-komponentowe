from typing import List
from . import FileSearchResult


class RequestResult:
    phrase: str
    status: str
    results: List[FileSearchResult]
    errors: List[str]

    def __init__(self, phrase: str, status: str, results: List[FileSearchResult], errors: List[str]):
        self.phrase = phrase
        self.status = status
        self.results = results
        self.errors = errors
