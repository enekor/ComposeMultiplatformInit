package com.example.common.data

/**
 * Use case for decrementing a counter
 */
class DecrementCounter(
    private val repository: CounterRepository
) {
    suspend operator fun invoke() {
        repository.decrement()
    }
}