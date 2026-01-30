package com.nghia.applen.data.api

import com.nghia.applen.model.Vocabulary
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class VocabularyApiService(private val client: HttpClient) {
    
    companion object {
        private const val BASE_URL = "https://api.applen.com/v1" // TODO: Replace with actual API
    }
    
    /**
     * Get vocabulary list with pagination
     */
    suspend fun getVocabularyList(
        topic: String? = null,
        level: String? = null,
        page: Int = 1,
        pageSize: Int = 20
    ): Result<List<Vocabulary>> {
        return try {
            val response: HttpResponse = client.get("$BASE_URL/vocabulary") {
                parameter("page", page)
                parameter("pageSize", pageSize)
                topic?.let { parameter("topic", it) }
                level?.let { parameter("level", it) }
            }
            
            if (response.status == HttpStatusCode.OK) {
                val words: List<Vocabulary> = response.body()
                Result.success(words)
            } else {
                Result.failure(Exception("Failed to fetch vocabulary: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Get word of the day
     */
    suspend fun getWordOfTheDay(): Result<Vocabulary> {
        return try {
            val response: HttpResponse = client.get("$BASE_URL/vocabulary/word-of-day")
            
            if (response.status == HttpStatusCode.OK) {
                val word: Vocabulary = response.body()
                Result.success(word)
            } else {
                Result.failure(Exception("Failed to fetch word of the day: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Search vocabulary
     */
    suspend fun searchVocabulary(query: String): Result<List<Vocabulary>> {
        return try {
            val response: HttpResponse = client.get("$BASE_URL/vocabulary/search") {
                parameter("q", query)
            }
            
            if (response.status == HttpStatusCode.OK) {
                val words: List<Vocabulary> = response.body()
                Result.success(words)
            } else {
                Result.failure(Exception("Search failed: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Sync user progress to server
     */
    suspend fun syncProgress(
        vocabularyId: String,
        masteryLevel: Int,
        reviewCount: Int
    ): Result<Unit> {
        return try {
            val response: HttpResponse = client.post("$BASE_URL/progress/sync") {
                contentType(ContentType.Application.Json)
                setBody(mapOf(
                    "vocabularyId" to vocabularyId,
                    "masteryLevel" to masteryLevel,
                    "reviewCount" to reviewCount
                ))
            }
            
            if (response.status == HttpStatusCode.OK) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Sync failed: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
