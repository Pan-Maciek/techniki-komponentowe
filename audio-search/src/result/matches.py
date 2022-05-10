from typing import List


class Matches:
    searchContext: str
    indices: List[int]

    def __init__(self, searchContext: str, indices: List[int]):
        self.searchContext = searchContext
        self.indices = indices
