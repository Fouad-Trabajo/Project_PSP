package com.fouadaha.pspcorrutinas

import kotlinx.coroutines.*

fun main() {
    log("INICIO")
    runBlocking {
        val result = async {
            log("Async : Estoy empezando.")
            val a = (1..50).random()
            log("Async : A = $a")
            delay(1000)
            val b =
                (1..50).random()
            log("Async : B = $b")
            delay(1000)
            log("Async : Estoy acabando.")
            a + b
        }
        delay(100)
        while (result.isActive) {
            log("RunBlocking : Async está activo")
            delay(1000)
        }
        log("RunBlocking : El result de la suma es: ${result.await()}")
    }
    log("FIN")
}