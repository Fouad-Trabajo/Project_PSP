package com.fouadaha.pspcorrutinas.ejerciciogordo

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Inicio del programa: Gestión de tareas concurrentes")

    val jobs = mutableListOf<Job>() // lista para almacenar los jobs

    for (index in 1..3) { // ejecutamos las 3 tareas concurrentemente
        val job = launch {
            task(index)
        }
        jobs.add(job) // guardamos el job actual
    }

    delay(2000) // esperamos antes de cancelar la tarea 2
    println("Cancelando tarea 2...") // Mensaje de cancelación
    jobs[1].cancel() // cancelamos la tarea 2

    // mostrar el estado de las tareas
    jobs.forEachIndexed { index, job ->
        println("Estado de la tarea ${index + 1}: ${job.isActive}")
    }

    jobs.forEach { job ->
        job.join() // ejecutar las demás tareas
    }
}

suspend fun task(num: Int) {
    val taskName = "Tarea $num" // Nombre del archivo que cambia en cada ejecución
    repeat(5) { index ->
        delay(1000)
        println("$taskName en progreso... ${index + 1}/5") // Mensaje de progreso
    }
    println("Tarea $taskName completada.") // Para cuando no se cancele la descarga
}
