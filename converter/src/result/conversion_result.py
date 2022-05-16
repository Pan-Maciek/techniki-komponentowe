from typing import List, Dict


class ConversionResult:
    pathMap: Dict[str, str]
    status: str
    errors: List[str]
    outputDir: str

    def __init__(self, path_map: Dict[str, str], status: str, errors: List[str], output_dir: str):
        self.pathMap = path_map
        self.status = status
        self.errors = errors
        self.outputDir = output_dir
