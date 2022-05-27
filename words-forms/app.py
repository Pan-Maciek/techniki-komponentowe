from flask import Flask, request
import json
from typing import List
from src.logic.synonyms import Synonyms
from src.logic.typos import Typos
from src.logic.forms import Forms
from src.result import RequestResult

DEFAULT_ARR_SEPARATOR = ','

app = Flask(__name__)


def search_and_prepare_response(phrases: List[str], search_mode):
    try:
        phr = [phrase for phrase in phrases]
        word_mode = search_mode(phr)
        request_res = RequestResult(
            phrases=phrases,
            status="ok",
            results=word_mode.find_form(),
            errors=word_mode.errors
        )
    except Exception as e:
        request_res = RequestResult(
            phrases=phrases,
            status="error",
            results=[],
            errors=[str(e)]
        )

    return app.response_class(
        response=json.dumps(request_res.results, default=lambda o: o.__dict__),
        status=200,
        mimetype='application/json'
    )


@app.route("/typos/search", methods=["GET"])
def typos():
    phrases: List[str] = request.args.get("phrases").split(DEFAULT_ARR_SEPARATOR)
    search_mode = Typos

    return search_and_prepare_response(phrases, search_mode)


@app.route("/synonyms/search", methods=["GET"])
def synonyms():
    phrases: List[str] = request.args.get("phrases").split(DEFAULT_ARR_SEPARATOR)
    search_mode = Synonyms

    return search_and_prepare_response(phrases, search_mode)


@app.route("/forms/search", methods=["GET"])
def forms():
    phrases: List[str] = request.args.get("phrases").split(DEFAULT_ARR_SEPARATOR)
    search_mode = Forms

    return search_and_prepare_response(phrases, search_mode)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8187)
    # http://localhost:8187/synonyms/search?phrases=król,nosi,koronę
    # http://localhost:8080/search?rootPath=/app/files&phrase=wysoki%2520sprzedawca&additionalInfo.enabledFormats=text-search,odt-search,pdf-search&additionalInfo.lang=en,de&additionalInfo.forms=typos,synonyms,forms