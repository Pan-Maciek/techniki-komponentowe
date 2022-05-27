from typing import List


class RequestResult:
    phrases: List[str]
    status: str
    results: List[str]
    errors: List[str]

    def __init__(self, phrases: List[str], status: str, results: List[str], errors: List[str]):
        self.phrases = phrases
        self.status = status
        self.results = results
        self.errors = errors
