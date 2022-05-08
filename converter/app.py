from flask import Flask, request
import json
from typing import List
from src.logic import ConverterFileManager
from src.result import ConversionResult

app = Flask(__name__)


@app.route("/audio_to_wav", methods=["GET"])
def audio_to_wav():
    root_path: str = request.args.get("rootPath")

    try:
        converter_file_manager = ConverterFileManager(root_path)
        tmp_paths_to_original_paths = converter_file_manager.convert_audio()
        request_res = ConversionResult(
            path_map=tmp_paths_to_original_paths,
            status="ok",
            errors=converter_file_manager.errors
        )
    except Exception as e:
        request_res = ConversionResult(
            path_map={},
            status="error",
            errors=[str(e)]
        )

    return app.response_class(
        response=json.dumps(request_res, default=lambda o: o.__dict__),
        status=200,
        mimetype='application/json'
    )


@app.route("/cleanup_audio", methods=["GET"])
def cleanup_audio():
    root_path: str = request.args.get("rootPath")

    converter_file_manager = ConverterFileManager(root_path)
    converter_file_manager.cleanup_audio()

    return app.response_class(status=200)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8185)
