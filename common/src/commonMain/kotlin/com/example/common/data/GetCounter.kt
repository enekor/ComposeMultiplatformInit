package com.example.common.data

import com.example.common.Counter
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting a counter flow
 */
class GetCounter(
    private val repository: CounterRepository
) {
    operator fun invoke(): Flow<Counter> {
        return repository.getCounterFlow()
    }
}