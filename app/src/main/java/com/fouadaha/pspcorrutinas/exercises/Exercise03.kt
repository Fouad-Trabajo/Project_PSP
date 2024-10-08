package com.fouadaha.pspcorrutinas.exercises


import kotlinx.coroutines.*
import java.time.*
import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ISO_LOCAL_TIME
val time =  LocalDateTime.now().format(formatter)


fun log(message: String) {
    println("${time} [${Thread.currentThread()}] $message")
}

fun main() {
    log("INICIO")
    runBlocking {
        val job1 = launch {
            repeat(5) {
                log("hhh hhh hhh job1Hijo -> iteraicón $it")
                delay(1000)
            }
            println("hhh hhh job1Hijo :: Estoy acabando.")
            println("111 111 job1 -> estoy activo")
        }


        delay(200)
        val job2 = launch {
            repeat(3){
                log("222 222 job2 -> iteración $it")
                delay(1000)
            }
            println("222 222 job2 :: Fin bloque.")
        }
        delay(200)
        while(job1.isActive || job2.isActive){
            log("JOB1 :: ${job1.isActive} XXX JOB2 :: $job2.isActive")
            delay(1000)
        }
    }
    log("FIN")
}
