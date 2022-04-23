from . import Matches
from typing import List


class FileSearchResult:
    file_path: str
    matches: List[Matches]

    def __init__(self, file_path: str, matches: List[Matches]):
        self.file_path = file_path
        self.matches = matches
