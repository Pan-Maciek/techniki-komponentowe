package pl.edu.agh.backend

import org.springframework.web.util.UriUtils
import java.nio.charset.StandardCharsets

fun String.encode() = UriUtils.encode(this, StandardCharsets.UTF_8)
