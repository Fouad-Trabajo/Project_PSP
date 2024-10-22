package com.fouadaha.pspcorrutinas.ejerciciogordo

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = async {
        repeat(3) { index ->
            task(index + 1)
        }

    }

}


suspend fun task(num: Int) {
    val taskName = "Tarea $num" //Nombre del archivo que cambia en cada ejecución
    repeat(5) { index ->
        delay(1000)
        println("$taskName en progreso... ${index + 1}/5")
    }
    println("Descarga de $taskName completada.") //Para cuando no se canele la descarga
}
