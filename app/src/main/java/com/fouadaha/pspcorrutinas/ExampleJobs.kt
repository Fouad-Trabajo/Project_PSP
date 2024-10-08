import kotlinx.coroutines.*

fun main() {
    println("INICIO")
    runBlocking {
        val job =
            launch {
                repeat(3) {
                    println("Launch - repetición #$it : ¡Estoy activo!")
                    delay(1000)
                }
                println("Launch : Estoy acabando.")
            }
        delay(2500)
        while (job.isActive) {
            println("RunBlocking : Job está activo")
            delay(1000)
            println("RunBlocking : Cancelando el job")
            job.cancel()
        }
        if (job.isCancelled) {
            println("RunBlocking : Job está CANCELADO")
        }else{
            println("RunBlocking : Job NO está CANCELADO")
        }
    }
    println("FIN")
}