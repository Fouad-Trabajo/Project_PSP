package com.fouadaha.pspcorrutinas.ejerciciogordo

import kotlinx.coroutines.*

fun main() = runBlocking {
    /**
     * Es como el ejercicio anterior,solo que mostrando los estados una vez termianos los
     * deferred, además en el anterior utilizabamos jobs y en este una función deferred que retorna
     */

    val deffered = mutableListOf<Deferred<String>>() // lista para almacenar los deferred

    for (index in 101..103) {
        val job = async {
            reservar(index)
        }
        deffered.add(job)
    }

    delay(3000)
    println("Cancelando la reserva de la habitación 102...")
    deffered[1].cancel() //cancelamos el job 2


    val resultados = deffered.map { deferred ->
        try {
            deferred.await() // Intenta obtener el resultado de la reserva
        } catch (e: CancellationException) {
            "Reserva cancelada" // Captura la excepción y devuelve un mensaje de cancelación
        }
    }
    resultados.forEach { resultado ->
        println("Resultado de la reserva: $resultado")
    }
}


suspend fun reservar(num: Int): String {
    val habitacion = "Habitación $num"
    val reserva = repeat(5) { index ->
        delay(1000)
        println("Reservando $habitacion... ${index + 1}/5")
    }
    return "$habitacion reservada"
}