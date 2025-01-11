package com.example.assignmnetnew4

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.junit.Test

class FlowExample {

    @Test
    fun xyz(): Unit = runBlocking{
        (0..10)
            .asFlow()
            .map { it -> "Item $it" }
            .onEach { println(it) }
            .collect()
    }
}