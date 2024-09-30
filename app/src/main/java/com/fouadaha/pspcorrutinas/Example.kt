package com.fouadaha.pspcorrutinas

import kotlinx.coroutines.*
import kotlin.system.*

/**
 * Ejecución secuencial, sincrona
 */
fun main() {
    val time = measureTimeMillis() {
        runBlocking() {
            println("Pronóstico de tiempo")

            val pronostico: Deferred<String> = async {
                getPronostico()
            }


            val temperature: Deferred<String> = async {
                getTemperature()
            }
            println("${pronostico.await()} ${temperature.await()}")
            println("!Disfruta del día")
        }

        /**
         * Saber la diferencia entre launch y el async:
         * El launch Lo lanzas y te despreocupas.
         * El async lo lanzas y está pendiente de que llame.
         */
    }
    println("El tiempo de ejecución es de ${time / 1000.0} segundos")
}

suspend fun getPronostico(): String {
    delay(1000)
    return ("Soleado")
}

suspend fun getTemperature(): String {
    delay(1000)
    return ("30\u00b0 C")
}