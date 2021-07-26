package com.ciaransloan.nytkmm.domain

import com.ciaransloan.nytkmm.HttpLogger
import com.ciaransloan.nytkmm.domain.remote.PARAM_API_KEY
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

object ApiClient {
    fun build(): HttpClient = HttpClient {
        default()
    }

    private fun HttpClientConfig<*>.default() {
        install(Logging) {
            logger = HttpLogger()
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(DefaultRequest) {
            headers { append(HttpHeaders.ContentType, ContentType.Application.Json) }
        }
        defaultRequest {
            parameter(PARAM_API_KEY, API_KEY)
        }
    }
}

private const val API_KEY = "aGjKGaU1nO7QvHCq1fNmbl9sdmw6fkyU"