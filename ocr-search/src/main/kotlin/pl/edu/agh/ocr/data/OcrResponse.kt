package pl.edu.agh.ocr.data

data class OcrResponse(
    val phrases: List<String>,
    val lang: List<String>,
    val status: String,
    val results: List<OcrResult>,
    val errors: List<String>
)
