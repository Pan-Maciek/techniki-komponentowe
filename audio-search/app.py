from flask import Flask, request
from src.logic import FileSearcher
from src.result import RequestResult
import json


app = Flask(__name__)


@app.route("/search", methods=["GET"])
def search():
    phrase: str = request.args.get("phrase")
    root_path: str = request.args.get("rootPath")

    try:
        file_searcher = FileSearcher(root_path)
        request_res = RequestResult(
            phrase=phrase,
            status="ok",
            results=file_searcher.find_phrase(phrase),
            errors=file_searcher.errors
        )
    except Exception as e:
        request_res = RequestResult(
            phrase=phrase,
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
    app.run(host="0.0.0.0", port=8184)
