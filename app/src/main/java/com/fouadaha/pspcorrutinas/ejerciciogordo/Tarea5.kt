package com.fouadaha.pspcorrutinas.ejerciciogordo

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import kotlinx.coroutines.*
import java.io.IOException

/**
 * Podemos hacer los métodos de consultas a la api
 * con un try-catch para que cuando alguna api
 * no funcione o no se encuentra disponible,
 * no detenga la ejecución del programa
 */

// CONSULTAS A LAS APIS
suspend fun getCatFact(): String {
    return try {
        Fuel.get("https://catfact.ninja/fact").awaitString()
    } catch (e: IOException) {
        "Error al obtener la respuesta de la API de gatos"
    }
}


suspend fun getDogImage(): String {
    return try {
        Fuel.get("https://dog.ceo/api/breeds/image/random").awaitString()
    } catch (e: IOException) {
        "Error al obtener la respuesta de la API de perros"
    }
}

//NO FUNIONAN LAS API
suspend fun getActivity(): String {
    return try {
        Fuel.get("https://www.boredapi.com/api/activity").awaitString()
    } catch (e: Exception) {
        "Error al obtener la respuesta de la API de actividades"
    }

}

suspend fun getBitcoinPrice(): String {
    val response = Fuel.get("https://api.coindesk.com/v1/bpi/currentprice/BTC.json").awaitString()
    return response
}

suspend fun getRandomUser(): String {
    val response = Fuel.get("https://randomuser.me/api/").awaitString()
    return response
}

suspend fun getRandomJoke(): String {
    val response = Fuel.get(" https://official-joke-api.appspot.com/random_joke").awaitString()
    return response
}


fun main() = runBlocking {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.IO + job)

    try {
        //deferreds que nos permiten acceder a las funciones para hacer llamadas paralelas
        val catDeferred = scope.async { getCatFact() }
        val dogImageDeferred = scope.async { getDogImage() }
        val activityDeferred = scope.async { getActivity() }
        val bitcoinPriceDeferred = scope.async { getBitcoinPrice() }
        val randomUserDeferred = scope.async { getRandomUser() }
        val randomJokeDeferred = scope.async { getRandomJoke() }


        /**
         * En este caso, si las llamadas a las api, tardan demasiado(más de 1.13 segundos)
         * se cancela la llamada de todas las apis y se cancela el job
         */
        delay(1000) //Cambia el valor para ver como se cancelan las llamadas a las apis
        job.cancel()

        //await para esperar a que terminen las llamadas
        val catFact = catDeferred.await()
        val dogImage = dogImageDeferred.await()
        val activity = activityDeferred.await()
        val bitcoinPrice = bitcoinPriceDeferred.await()
        val randomUser = randomUserDeferred.await()
        val randomJoke = randomJokeDeferred.await()


        //imprimir los resultaados a la vez
        println("Cat Fact: $catFact")
        println("Dog Image: $dogImage")
        println("Activity: $activity")
        println("Bitcoin Price: $bitcoinPrice")
        println("Random User: $randomUser")
        println("Random Joke: $randomJoke")
    } catch (e: CancellationException) {
        println("Todas las cosultas a las apis han sido canceladas, " +
                "debido a que las llamadas a las apis tardaron demasiado")
    }
}