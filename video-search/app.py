from flask import Flask, request
from src.result import RequestResult
import json
from src.logic.util import search_in_video
app = Flask(__name__)

DEFAULT_ARR_SEPARATOR = ","


@app.route("/search", methods=["GET"])
def search():
    phrases: str = request.args.get("phrases")
    lang: str = request.args.get("lang")
    root_path: str = request.args.get("rootPath")

    try:
        request_res = search_in_video(phrases, lang, root_path)
    except Exception as e:
        request_res = RequestResult(
            phrases=phrases.split(DEFAULT_ARR_SEPARATOR),
            lang=lang.split(DEFAULT_ARR_SEPARATOR),
            status="error",
            results=[],
            errors=[str(e)]
        )

    return app.response_class(
        response=json.dumps(request_res, default=lambda o: o.__dict__),
        status=200,
        mimetype='application/json'
    )


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8186)
