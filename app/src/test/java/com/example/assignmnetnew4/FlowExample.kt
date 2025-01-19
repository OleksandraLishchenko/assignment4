package com.example.assignmnetnew4

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.junit.Test

class FlowExample {

    @Test
    fun FlowTest2() = runBlocking {
        val flow = flow<Int> {
            emit(1)
            emit(2)
            emit(123)
        }
        flow.collect{
            println(it)
        }
    }

    @Test
    fun xyz(): Unit = runBlocking {
        (0..10)
            .asFlow()
            .map { it -> "Item $it" }
            .onEach { println(it) }
            .collect()
    }

    @Test
    fun x() = runBlocking {
        println()
        (0..10).asFlow()
            .onEach { it ->
                delay(500)
                println("OnEach intial - $it")
            }.filter {
                it.mod(2) == 0
            }.onEach {
                println("onEach after filtering: $it")
            }
            .map { "Item $it" }
            .onEach { it ->
            }
            .collect {
                println(it)
            }
    }

    @Test
    fun flatMapLatestTest(): Unit = runBlocking {
        val numbers = (0..5).toList()
        val fooBar = listOf("Foo", "Bar", "Baz", "Qux", "Quux")

        numbers
            .asFlow()
            .onEach {
                delay(3000)
                println(it)
            }
            .flatMapLatest { numbers ->
                fooBar.asFlow()
                    .onEach { delay(100) }
                    .map { "$numbers -> $it" }
            }
            .collect {
                println(it)
            }
    }

    @Test
    fun debounceTest() = runBlocking {
        (1..5).asFlow()
            .onEach { println(it) }
            .onEach { delay(1000) }
            .debounce(1100)
            .onEach { println("Past debounce: $it") }
            .collect()
    }

    @Test
    fun flowConstruction(): Unit = runBlocking {
        val testedFlow = flow {
            emit("1" to appendTime())
            delay(50)
        }
        testedFlow.collect { value ->
            println(value)
        }
    }
    private fun appendTime() = System.currentTimeMillis().toString()
}