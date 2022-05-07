import os

from typing import List
from .audio_searcher import AudioSearcher
from src.result import FileSearchResult


class FileSearcher:
    path: str
    errors: List[str]
    audio_searcher: AudioSearcher = AudioSearcher()

    def __init__(self, path: str):
        self.path = path

    def find_phrases(self, phrases: List[str], languages: List[str]) -> List[FileSearchResult]:
        if len(phrases) != len(languages):
            raise Exception("Each phrase has to be associated with a language")
        self.errors = []
        results = []
        for root, _, files in os.walk(self.path):
            for filename in filter(lambda file: file.endswith(".wav"), files):
                abs_path = os.path.abspath(os.path.join(root, filename))
                try:
                    result = self.audio_searcher.phrase_occurrences(phrases, languages, abs_path)
                    if result is not None:
                        results.append(result)
                except Exception as e:
                    self.errors.append(str(e))

        return results
