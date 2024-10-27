package com.fouadaha.pspcorrutinas.exercises

import kotlinx.coroutines.*

fun main() = runBlocking {

    //usando launch y job
    val job = launch {
        delay(100)
        println("Tarea que no retorna nada: Completada")
    }

    // usando async y deferred
    val deferred = async {
        delay(1000)
        "Tarea que retorna algo: Completada"
    }

    job.join() // ejecutamos el job (esperamos a que no retorne nada)
    println("Resultado del deferrd: ${deferred.await()}") // el await es para esperar a que retorne el valor
    // ejecutamos el deferred y esperamos a que retorne el valor
}