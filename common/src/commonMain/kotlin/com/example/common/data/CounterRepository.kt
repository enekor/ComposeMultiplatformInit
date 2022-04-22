package com.example.common.data

import com.example.common.Counter
import kotlinx.coroutines.flow.Flow

/**
 * Repository for [Counter]
 */
class CounterRepository(
    private val counterDataSource: CounterDataSource
) {

    suspend fun increment() {
        counterDataSource.increment()
    }
    suspend fun decrement() {
        counterDataSource.decrement()
    }
    fun getCounterFlow(): Flow<Counter> {
        return counterDataSource.getCounterFlow()
    }
}