import os
import shutil

from typing import List, Dict
from src.logic.converters import Converter, Mp3ToWavConverter, WavFromMp4Converter


class ConverterFileManager:
    path: str
    errors: List[str]
    audio_output_dir: str
    audio_converters: Dict[str, Converter] = {
        ".mp3": Mp3ToWavConverter(),
    }
    video_output_dir: str
    video_converters: Dict[str, Converter] = {
        ".mp4": WavFromMp4Converter(),
    }

    def __init__(self, path: str):
        self.path = path
        # output dir for audio conversion results. Odd stuff at the end to avoid user creating having created identical directory
        self.audio_output_dir = os.path.abspath(os.path.join(path, "audio_conversion_results_6dsa8ba9v84a"))
        self.video_output_dir = os.path.abspath(os.path.join(path, "video_conversion_results_6dsa8ba9v84a"))

    def convert(self, output_dir: str, converters: Dict[str, Converter]) -> Dict[str, str]:
        if os.path.isdir(output_dir):
            # If user for some reason shut the app down before it managed to delete the tmp dir, let's just remove it now
            self.cleanup_conversion_dir(output_dir)
        os.mkdir(output_dir)

        tmp_paths_to_original_paths: Dict[str, str] = {}

        for root, _, files in os.walk(self.path):
            for filename in files:
                name, ext = os.path.splitext(filename)
                if ext in converters:
                    abs_path = os.path.abspath(os.path.join(root, filename))
                    try:
                        dst_path = os.path.abspath(os.path.join(output_dir, f"{name}.wav"))
                        converters[ext].convert(abs_path, dst_path)
                        tmp_paths_to_original_paths[dst_path] = abs_path
                    except Exception as e:
                        self.errors.append(str(e))

        return tmp_paths_to_original_paths

    def convert_audio(self) -> Dict[str, str]:
        self.errors = []
        return self.convert(self.audio_output_dir, self.audio_converters)

    def convert_video(self):
        self.errors = []
        return self.convert(self.video_output_dir, self.video_converters)

    def cleanup_conversion_dir(self, output_dir: str):
        shutil.rmtree(output_dir)

    def cleanup_audio(self):
        self.cleanup_conversion_dir(self.audio_output_dir)

    def cleanup_video(self):
        self.cleanup_conversion_dir(self.video_output_dir)
