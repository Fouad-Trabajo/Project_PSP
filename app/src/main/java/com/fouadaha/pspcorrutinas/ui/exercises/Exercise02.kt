package com.fouadaha.pspcorrutinas.ui.exercises

import kotlinx.coroutines.*

/**
 * Ejercicio 2: Consultas a una API
 * Descripción: Realiza varias consultas a una API en paralelo y procesa los resultados.
 * Instrucciones:
 * 1. Crea una función fetchData que simule una consulta a una API.
 * 2. Usa async para realizar varias consultas en paralelo.
 * 3. Procesa y muestra los resultados en la consola.
 */

fun main() {
    runBlocking() {
        val apis = listOf("API 1", "API 2", "API 3", "API 4", "API 5")
        val requests = apis.map { api ->
            async {
                fetchData(api)
            }
        }
        requests.forEach { request ->
            println(request.await())
        }
    }
}

suspend fun fetchData(url: String): String {
    delay(1000) //Simulaicón del tiempo que tarda en responder la llamada a la Api
    val request: String
    if (url.equals("API 3")) {
        request = "cancelado"
    } else {
        return "Datos de $url"
    }
    return request
}

