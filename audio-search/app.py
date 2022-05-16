from flask import Flask, request
from src.logic import FileSearcher
from src.result import RequestResult
import json
from typing import List

DEFAULT_ARR_SEPARATOR = ','

app = Flask(__name__)


def search_and_prepare_response(phrases: List[str], lang: List[str], root_path: str, with_conversion: bool = True):
    try:
        file_searcher = FileSearcher(root_path)
        request_res = RequestResult(
            phrases=phrases,
            lang=lang,
            status="ok",
            results=file_searcher.find_phrases(phrases, lang, with_conversion),
            errors=file_searcher.errors
        )
    except Exception as e:
        request_res = RequestResult(
            phrases=phrases,
            lang=lang,
            status="error",
            results=[],
            errors=[str(e)]
        )

    return app.response_class(
        response=json.dumps(request_res, default=lambda o: o.__dict__),
        status=200,
        mimetype='application/json'
    )


@app.route("/search", methods=["GET"])
def search():
    phrases: List[str] = request.args.get("phrases").split(DEFAULT_ARR_SEPARATOR)
    lang: List[str] = request.args.get("lang").split(DEFAULT_ARR_SEPARATOR)
    root_path: str = request.args.get("rootPath")

    return search_and_prepare_response(phrases, lang, root_path, with_conversion=True)


@app.route("/no_conversion_search", methods=["GET"])
def no_conversion_search():
    phrases: List[str] = request.args.get("phrases").split(DEFAULT_ARR_SEPARATOR)
    lang: List[str] = request.args.get("lang").split(DEFAULT_ARR_SEPARATOR)
    root_path: str = request.args.get("rootPath")

    return search_and_prepare_response(phrases, lang, root_path, with_conversion=False)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8184)
