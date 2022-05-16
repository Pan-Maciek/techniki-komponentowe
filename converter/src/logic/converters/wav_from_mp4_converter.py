from src.logic.converters.converter import Converter
import subprocess


class WavFromMp4Converter(Converter):
    def convert(self, src: str, dst: str):
        subprocess.call(f"ffmpeg -i {src} -ab 160k -ac 2 -ar 44100 -vn {dst}", shell=True)
