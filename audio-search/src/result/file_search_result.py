from . import Matches
from typing import List


class FileSearchResult:
    path: str
    matches: List[Matches]

    def __init__(self, path: str, matches: List[Matches]):
        self.path = path
        self.matches = matches

    def change_path(self, new_path: str):
        self.path = new_path
