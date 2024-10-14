package com.fouadaha.pspcorrutinas

import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

val formateador = DateTimeFormatter.ISO_LOCAL_TIME
fun log(mensaje: String) {
    println("${formateador.format(LocalTime.now())} - ${Thread.currentThread()} :: $mensaje")
}

fun main() {
    log("INICIO")
    runBlocking {
        val job1 = launch {
            val hijoJob1 = launch {
                repeat(5) {
                    log("hhh hhh hhh hijoJob1 -> iteración $it")
                    delay(1000)
                }
                log("hhh hhh hhh HIJO JOB1 Acaba")
            }
            log("111 111 JOB1 FIN bloque")
        }
        delay(100)
        val job2 = launch {
            repeat(3) {
                log("222 222 Job2 -> Iteración $it")
                delay(1000)
            }
            log("222 222 JOB2 FIN bloque")
        }
        delay(200)

        while (job1.isActive || job2.isActive) {
            log("JOB1 :: ${job1.isActive} XXX JOB2 :: ${job2.isActive}")
            delay(1000)
        }
    }
    log("FIN")
}