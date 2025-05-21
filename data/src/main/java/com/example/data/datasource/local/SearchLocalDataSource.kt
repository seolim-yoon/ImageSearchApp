package com.example.data.datasource.local

import javax.inject.Inject

class SearchLocalDataSource @Inject constructor() {

    private val cacheMap = mutableMapOf<String, CachedResult<*>>()
    private val cacheDuration = 5 * 60 * 1000L

    fun <T> saveCacheData(cacheKey: String, documents: List<T>) {
        cacheMap[cacheKey] = CachedResult(data = documents, timestamp = System.currentTimeMillis())
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> loadCacheData(cacheKey: String): CachedResult<T>? {
        val cachedData = cacheMap[cacheKey] as? CachedResult<T>
        val currentTime = System.currentTimeMillis()

        return if (cachedData != null && currentTime - cachedData.timestamp <= cacheDuration) {
            cachedData
        } else {
            cacheMap.remove(cacheKey)
            null
        }
    }
}

data class CachedResult<T>(
    val data: List<T>,
    val timestamp: Long
)