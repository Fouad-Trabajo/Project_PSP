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
                try {
                    getTemperature()
                } catch (e: AssertionError) {
                    //Log.error("Exception capturada en temperatura: $e")
                    "-\u00b0C"
                }
            }

            delay(200)
            //temperature.cancel() //Si cancelamos una llamada, luego no podemos utilizarlo abajo

            // Es útil cancelar un proceso cuando este tarde demasiado, por ejemplo

            println("${pronostico.await()} ${temperature.await()} ")
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


suspend fun getWeatherReport() = coroutineScope {
    val pronostico = async { getPronostico() }
    val temperature = async { getTemperature() }
    delay(200)
}

suspend fun getPronostico(): String {
    delay(1000)
    return ("Soleado")
}

suspend fun getTemperature(): String {
    delay(1000)
    //throw AssertionError("Temperatura no encontrada")
    return ("30\u00b0 C")
}