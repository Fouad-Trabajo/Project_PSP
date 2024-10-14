package com.fouadaha.pspcorrutinas.exercises

import kotlinx.coroutines.*


/**
 * Ejercicio 1: Simulación de Descarga de Archivos Descripción:
 * Simula la descarga de varios archivos en paralelo y muestra el progreso de cada descarga. Instrucciones:
 * <ul>
 *     <li> 1. Crea una función downloadFile que simule la descarga de un archivo utilizando delay.</li>
 *     <li> 2. Usa async para iniciar varias descargas en paralelo. 3. Muestra el progreso de cada descarga en la consola. </il>
 * </ul>
 */


fun main() {
    runBlocking() {
        var ficheros = listOf("Archivo 1", "Archivo 2", "Archivo 3", "Archivo 4", "Archivo 5")
        val downloads = ficheros.map { file ->
            async {
                downloadFile(file)
            }
        }
        downloads.forEach { download ->
            println(download.await()) //El await se encarga de esperar y obtener el resultado de una operación asincrónica.
        } //defered:
    }
}


suspend fun downloadFile(file: String): String {
    delay(1000) // Simulamos el tiempo de descarga
    return "$file descargado"
}