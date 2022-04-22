package com.example.common.di

import com.example.common.CounterViewModel
import com.example.common.InMemoryCounterDataSource
import com.example.common.data.CounterRepository
import com.example.common.data.DecrementCounter
import com.example.common.data.GetCounter
import com.example.common.data.IncrementCounter
import org.kodein.di.DI
import org.kodein.di.bindProvider

val injectedServices = DI {
    // data
    val counterDataSource = InMemoryCounterDataSource()
    val counterRepository = CounterRepository(counterDataSource)

    // Use cases
    val getCounter = GetCounter(counterRepository)
    val incrementCounter = IncrementCounter(counterRepository)
    val decrementCounter = DecrementCounter(counterRepository)

    // View model
    bindProvider { CounterViewModel(getCounter, incrementCounter, decrementCounter) }
}