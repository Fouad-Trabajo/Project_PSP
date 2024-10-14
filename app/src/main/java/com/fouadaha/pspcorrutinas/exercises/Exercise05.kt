package com.fouadaha.pspcorrutinas.exercises

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

fun main() {
    log("INICIO")
    runBlocking {
        // El async devuelve un valor y debemos gestionarlo
        // Devuelve el resultado del deffered que está terminado
        val deffered1 = async {
            log("Inicio deffered1")
            delay(3000)
            log("Fin deffered1")
            "result1"
        }
        val deffered2 = async {
            log("Inicio deffered1")
            delay(4000)
            log("Fin deffered1")
            "result2"
        }
        val deffered3 = async {
            log("Inicio deffered1")
            delay(5000)
            log("Fin deffered1")
            "result3"
        }

        delay(3900)

        if (deffered1.isActive) {
            deffered1.cancel()
            log("deffered1 cancelado")
        }
        if (deffered2.isActive) {
            deffered2.cancel()
            log("deffered2 cancelado")

        }
        if (deffered3.isActive) {
            deffered3.cancel()
            log("deffered3 cancelado")
        }

        // Recuperar los valores del async
        try {
            val resultado1 = deffered1.await()
            log("Resultado de deferred1: $resultado1")
        } catch (e: CancellationException) {
            log("deffered1 fue cancelado")
        }

        try {
            val resultado2 = deffered2.await()
            log("Resultado de deferred2: $resultado2")
        } catch (e: CancellationException) {
            log("deffered2 fue cancelado")
        }

        try {
            val resultado3 = deffered3.await()
            log("Resultado de deferred3: $resultado3")
        } catch (e: CancellationException) {
            log("deffered3 fue cancelado")
        }

    }
    log("FIN")
}