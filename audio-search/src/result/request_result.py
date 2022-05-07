from typing import List
from . import FileSearchResult


class RequestResult:
    phrases: List[str]
    lang: List[str]
    status: str
    results: List[FileSearchResult]
    errors: List[str]

    def __init__(self, phrases: List[str], lang: List[str], status: str, results: List[FileSearchResult], errors: List[str]):
        self.phrases = phrases
        self.lang = lang
        self.status = status
        self.results = results
        self.errors = errors
