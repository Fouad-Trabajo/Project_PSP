package com.fouadaha.pspcorrutinas

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Función runBlocking:: ${Thread.currentThread().name}")
        launch {
            println("Dentro del launch::${Thread.currentThread().name}")
            withContext(Dispatchers.Default) { //Estoy forzando que se ejecute en el hilo de Default
                println("Dentro del withcContext::${Thread.currentThread().name}")
                delay(1000)
                println("Encontrados 10 resultados")
            } //Cuando se acabe el launch, volver al hilo Main
            println("Fin del launch::${Thread.currentThread().name}")
        }
    }
    println("Fuera del runBlocking::${Thread.currentThread().name}")
    println("Loading...")
}