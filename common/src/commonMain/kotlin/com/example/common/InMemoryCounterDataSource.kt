package com.example.common

import com.example.common.data.CounterDataSource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * In-memory basic implementation of [CounterDataSource]
 */
class InMemoryCounterDataSource(
    private var counter: Counter = Counter(),
    private var counterFlow: MutableSharedFlow<Counter> = MutableSharedFlow(
        extraBufferCapacity=2,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
): CounterDataSource {

    override suspend fun increment() {
        counter = counter.copy(
            valor = counter.valor+1,
            mensaje = "increment"
        )
        counterFlow.tryEmit(counter)
    }

    override suspend fun decrement() {
        counter = counter.copy(
            valor = counter.valor-1,
            mensaje = "decrement"
        )
        counterFlow.tryEmit(counter)
    }

    override fun getCounterFlow(): Flow<Counter> {
        return counterFlow.asSharedFlow()
    }
}