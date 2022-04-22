package com.example.common
import com.example.common.data.DecrementCounter
import com.example.common.data.GetCounter
import com.example.common.data.IncrementCounter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class CounterViewModel(
    private val getCounter: GetCounter,
    private val incrementCounter: IncrementCounter,
    private val decrementCounter: DecrementCounter
) {

    /**
     * Increments the counter
     */
    fun incrementCounterLaunch() {
        CoroutineScope(Dispatchers.Default).launch {
            incrementCounter()
        }
    }

    /**
     * Decrements the counter
     */
    fun decrementCounterLaunch() {
        CoroutineScope(Dispatchers.Default).launch {
            decrementCounter()
        }
    }

    /**
     * Retrieves the counter flow
     */
    fun getCounterFlow(): Flow<Counter> {
        return getCounter()
    }
}