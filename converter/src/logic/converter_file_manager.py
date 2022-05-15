import os
import shutil

from typing import List, Dict
from src.logic.converters import Converter, Mp3ToWavConverter


class ConverterFileManager:
    path: str
    errors: List[str]
    audio_output_dir: str
    audio_converters: Dict[str, Converter] = {
        ".mp3": Mp3ToWavConverter()
    }

    def __init__(self, path: str):
        self.path = path
        # output dir for audio conversion results. Odd stuff at the end to avoid user creating having created identical directory
        self.audio_output_dir = os.path.abspath(os.path.join(path, "audio_conversion_results_6dsa8ba9v84a"))

    def convert_audio(self) -> Dict[str, str]:
        if os.path.isdir(self.audio_output_dir):
            # If user for some reason shut the app down before it managed to delete the tmp dir, let's just remove it now
            self.cleanup_audio()
        os.mkdir(self.audio_output_dir)

        self.errors = []
        tmp_paths_to_original_paths: Dict[str, str] = {}

        for root, _, files in os.walk(self.path):
            for filename in files:
                name, ext = os.path.splitext(filename)
                if ext in self.audio_converters:
                    abs_path = os.path.abspath(os.path.join(root, filename))
                    try:
                        dst_path = os.path.abspath(os.path.join(self.audio_output_dir, f"{name}.wav"))
                        self.audio_converters[ext].convert(abs_path, dst_path)
                        tmp_paths_to_original_paths[dst_path] = abs_path
                    except Exception as e:
                        self.errors.append(str(e))

        return tmp_paths_to_original_paths

    def cleanup_audio(self):
        shutil.rmtree(self.audio_output_dir)
