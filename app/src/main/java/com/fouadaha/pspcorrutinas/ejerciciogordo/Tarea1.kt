package com.fouadaha.pspcorrutinas.ejerciciogordo

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default + job)
    val jobs = mutableListOf<Deferred<String>>()

    repeat(3) { index ->
        val downland = scope.async {
            archivo(index + 1)
        }
        jobs.add(downland)
    }

    val progressJob = launch {
        jobs.forEachIndexed { index, download ->
            try {
                download.await() // Espera a que cada descarga termine
            } catch (e: CancellationException) {
                println("Descarga del Archivo${index + 1} cancelada.")
            }
        }
    }

    delay(4000) //Cambia este valor para cambiar el tiempo de espera de la cancelación
    scope.cancel()
    println("Cancelando todas las descargas...")
    progressJob.join()
}

suspend fun archivo(num: Int): String {
    val fileName = "Archivo$num" //Nombre del archivo que cambia en cada ejecución
    repeat(5) { index ->
        delay(1000)
        println("Descargando $fileName... ${index + 1}/5")
    }
    return "Descarga de $fileName completada." //Para cuando no se canele la descarga
}
