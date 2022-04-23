from typing import List


class Matches:
    search_context: str
    indices: List[int]

    def __init__(self, search_context: str, indices: List[int]):
        self.search_context = search_context
        self.indices = indices
