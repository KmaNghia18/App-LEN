package com.nghia.applen.data.api

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ApiClient {
    
    fun createHttpClient(): HttpClient {
        return HttpClient {
            // Content negotiation for JSON
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            
            // Logging
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
            
            // Timeout
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
            
            // Default request configuration
            defaultRequest {
                // Add auth headers here if needed
                // header("Authorization", "Bearer $token")
            }
        }
    }
}
