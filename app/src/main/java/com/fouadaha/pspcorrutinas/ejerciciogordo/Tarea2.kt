    package com.fouadaha.pspcorrutinas.ejerciciogordo

    import kotlinx.coroutines.async
    import kotlinx.coroutines.*
    import kotlinx.coroutines.runBlocking


    fun main() = runBlocking {
        println("Inicio del programa: Factorial de número de forma asíncrona")

        val listNumbers = listOf(50, 12, 20, 3, 6, 25)

        for (number in listNumbers) {
            val deferred  = async {
                factorial(number) //Llamada a la función de manera asíncrona
            }
            println("El factorial de $number es ${deferred.await()}")
        }

    }

    suspend fun factorial(n: Int): Long {
        var result = 1L // L (long), números muy muy grandes
        for (i in 1..n) {
            delay(200)
            result *= i
        }
        return result
    }


