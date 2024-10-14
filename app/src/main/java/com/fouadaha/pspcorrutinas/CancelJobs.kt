package com.fouadaha.pspcorrutinas

import kotlinx.coroutines.*

fun main() {
    println("INICIO")
    runBlocking {
        val job = launch {
            val jobHijo = launch {
                repeat(10) {
                    println("Hijo - repetición #$it : ¡Estoy activo!")
                    delay(1000)
                }
                println("Hijo : Estoy acabando.")
            }
            delay(2500)
            while (jobHijo.isActive) {
                println("Hijo : ¡Estoy activo!")
                delay(1000)
                println("Launch : Cancelando Job Hijo")
                jobHijo.cancel() //Las canelaciones si que afectan a sus padres.
            } //Las excepciones no se propagan hacia arriba, solo hacia abajo.
            if (jobHijo.isCancelled) {
                println("Launch : Job está cancelado")
            }
            if (jobHijo.isCompleted) {
                println("Launch : Job está completado")
            }
            println("Launch :: Estoy acabando.")
        }
        delay(200)
        while (job.isActive) {
            println("RunBlocking : Job está activo")
            delay(1000)
        }
        if (job.isCancelled) {
            println("RunBlocking : Job está cancelado")
        }
        if (job.isCompleted) {
            println("RunBlocking : Job está completado")
        }
    }
    println("FIN")
}