package pl.edu.agh.backend.search

class ServiceMap {

        private val servicesNames : Map<String, String> = mapOf(
                "text-search" to "80",
                "odt-search" to "8182",
                "pdf-search" to "8183",
                "audio-search" to "8184",
                "video-search" to "8186",
                "ocr-search" to "8187"
        )

        fun filterServices(enabledFormats: List<String>) : Map<String, String> = servicesNames.filter { it.key in enabledFormats }

}