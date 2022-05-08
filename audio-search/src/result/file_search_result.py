from . import Matches
from typing import List


class FileSearchResult:
    filePath: str
    matches: List[Matches]

    def __init__(self, filePath: str, matches: List[Matches]):
        self.filePath = filePath
        self.matches = matches
