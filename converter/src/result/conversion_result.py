from typing import List, Dict


class ConversionResult:
    pathMap: Dict[str, str]
    status: str
    errors: List[str]

    def __init__(self, path_map: Dict[str, str], status: str, errors: List[str]):
        self.pathMap = path_map
        self.status = status
        self.errors = errors
