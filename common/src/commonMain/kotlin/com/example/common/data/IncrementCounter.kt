package com.example.common.data

import kotlinx.coroutines.flow.Flow

/**
 * Use case for incrementing a counter
 */
class IncrementCounter(
    private val repository: CounterRepository
) {
    suspend operator fun invoke() {
        repository.increment()
    }
}