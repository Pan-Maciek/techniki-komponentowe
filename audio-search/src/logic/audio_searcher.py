import speech_recognition as sr
from ..result import FileSearchResult, Matches
import re
from typing import Optional, List


class AudioSearcher:
    r = sr.Recognizer()

    def phrase_occurrences(self, phrases: List[str], languages: List[str], path: str) -> Optional[FileSearchResult]:

        try:
            with sr.AudioFile(path) as source:
                self.r.adjust_for_ambient_noise(source, duration=0.5)
                audio = self.r.record(source)
            try:
                matches = []
                for i, phrase in enumerate(phrases):
                    lang = languages[i]
                    text = self.r.recognize_google(audio, key=None, language=lang)
                    indices = [occurrence.start() for occurrence in re.finditer(phrase, text)]
                    if len(indices) > 0:
                        matches.append(Matches(text, indices))
                if len(matches) > 0:
                    return FileSearchResult(path, matches)
                else:
                    return None
            except sr.UnknownValueError:
                raise Exception(f"{path}: Google Speech Recognition could not understand audio")
            except sr.RequestError:
                raise Exception(f"{path}: Could not request results from Google Speech Recognition service")
        except Exception:
            raise Exception(f"{path}: Error while converting file to audio")
