package com.fouadaha.pspcorrutinas.exercises

import kotlinx.coroutines.*

/** Escribir una llamda a 3 apis que tardan 3, 4, y 5 segundos.
 * Al final, poner un control que toda API(job) que lleve más de 4 segundos se cancele.
 */

fun main() {
    log("INICIO")
    runBlocking {
        val job1 = launch {
            log("Inicio job1")
            delay(3000)
            log("Fin job1")
        }
        val job2 = launch {
            log("Inicio job2")
            delay(4000)
        }
        val job3 = launch {
            log("Inicio job3")
            delay(5000)
        }
        delay(3700)
        if (job1.isActive) {
            job1.cancel()
            log("Job1 cancelado")
        }
        if (job2.isActive) {
            job2.cancel()
            log("Job2 cancelado")

        }
        if (job3.isActive) {
            job3.cancel()
            log("Job3 cancelado")
        }
    }
    log("FIN")
}

