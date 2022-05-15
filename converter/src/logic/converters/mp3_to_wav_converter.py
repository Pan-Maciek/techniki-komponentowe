from src.logic.converters.converter import Converter
from pydub import AudioSegment


class Mp3ToWavConverter(Converter):
    def convert(self, src: str, dst: str):
        sound = AudioSegment.from_mp3(src)
        sound.export(dst, format="wav")
