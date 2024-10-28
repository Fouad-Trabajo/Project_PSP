package com.fouadaha.pspcorrutinas.ejerciciogordo

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    println("Inicio del programa: Factorial de números de forma asíncrona")

    val listNumbers = listOf(16, 3, 20, 12, 4, 25)

    val deferredResults = listNumbers.map { number ->
        async {
            factorial(number) //Llamada a la función de manera asíncrona
        }
    }

    // Espera a que todos los deferred(factoriales) terminen para mostrarlos a la vez
    deferredResults.forEachIndexed { index, deferred ->
        println("El factorial de ${listNumbers[index]} es ${deferred.await()}")
    }

}

suspend fun factorial(n: Int): Long {
    var result = 1L // L (long), números muy muy grandes
    for (i in 1..n) {
        result *= i
    }
    return result
}


