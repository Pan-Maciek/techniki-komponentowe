import requests

audio_search_path: str = "http://audio-search:8184"
converter_path: str = "http://converter:8185"


def search_in_video(phrases: str, lang: str, root_path: str):
    # convert video files
    conversion_result = requests.get(
        f"{converter_path}/convert_video",
        {"rootPath": root_path}
    ).json()

    # search in audio files without conversion (all audio files created by the converter should already be in .wav format)
    search_result = requests.get(
        f"{audio_search_path}/no_conversion_search",
        {
            "rootPath": conversion_result["outputDir"],
            "phrases": phrases,
            "lang": lang
        }
    ).json()

    # remap audio_search results
    for result in search_result["results"]:
        result["filePath"] = conversion_result["pathMap"][result["filePath"]]

    requests.get(
        f"{converter_path}/cleanup_video",
        {"rootPath": root_path}
    )

    return search_result
