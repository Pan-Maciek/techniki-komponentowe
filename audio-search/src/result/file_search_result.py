from . import Matches
from typing import List


class FileSearchResult:
    path: str
    matches: List[Matches]

    def __init__(self, path: str, matches: List[Matches]):
        self.path = path
        self.matches = matches
