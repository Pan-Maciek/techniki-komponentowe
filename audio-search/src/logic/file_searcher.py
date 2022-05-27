import os

from typing import List, Dict
from .audio_searcher import AudioSearcher
from ..result import FileSearchResult
import requests


class FileSearcher:
    path: str
    errors: List[str]
    audio_searcher: AudioSearcher = AudioSearcher()

    converter_path: str = "http://converter:8185"

    def __init__(self, path: str):
        self.path = path

    def find_phrases(self, phrases: List[str], languages: List[str], with_conversion: bool) -> List[FileSearchResult]:
        new_list = ["pl"] * (len(phrases) - len(languages))
        for new in new_list:
            languages.append(new)
        if len(phrases) != len(languages):
            raise Exception("Each phrase has to be associated with a language")
        self.errors = []
        results = []

        path_map: Dict[str, str] = {}
        if with_conversion:
            conversion_result = requests.get(
                f"{self.converter_path}/audio_to_wav",
                {"rootPath": self.path}
            ).json()

            status = conversion_result["status"]
            if status == "ok":
                path_map = conversion_result['pathMap']
            else:
                self.errors = conversion_result["errors"]

        for root, _, files in os.walk(self.path):
            for filename in filter(lambda file: file.endswith(".wav"), files):
                abs_path = os.path.abspath(os.path.join(root, filename))
                try:
                    result = self.audio_searcher.phrase_occurrences(phrases, languages, abs_path)
                    if result is not None:
                        # remap path if it is a tmp file creating by the converter
                        if with_conversion and result.filePath in path_map:
                            result.change_path(path_map[result.filePath])
                        results.append(result)
                except Exception as e:
                    self.errors.append(str(e))

        if with_conversion:
            requests.get(
                f"{self.converter_path}/cleanup_audio",
                {"rootPath": self.path}
            )

        return results
