package com.example.common.data

import com.example.common.Counter
import kotlinx.coroutines.flow.Flow

/**
 * Data source for [Counter]
 */
interface CounterDataSource {
    /**
     * Increment the counter by 1
     */
    suspend fun increment()

    /**
     * Decrement the counter by 1
     */
    suspend fun decrement()

    /**
     * Get the counter value Flow
     */
    fun getCounterFlow(): Flow<Counter>
}