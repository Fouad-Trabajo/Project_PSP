package com.fouadaha

import android.os.Bundle
import android.widget.*


import androidx.appcompat.app.AppCompatActivity

import com.fouadaha.pspcorrutinas.R
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var buttonDownload: Button
    private lateinit var buttonCancel: Button
    private lateinit var textViewProgress: TextView
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tarea6)

        buttonDownload = findViewById(R.id.button_download)
        buttonCancel = findViewById(R.id.button_cancel_download)
        textViewProgress = findViewById(R.id.downoad_progress)

        buttonDownload.setOnClickListener {
            startDownload()
        }

        buttonCancel.setOnClickListener {
            cancelDownload()
        }
    }

    private fun startDownload() {
        // Cancelar cualquier descarga anterior
        job?.cancel()

        // Iniciar una nueva descarga
        job = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..100) {
                // Simular tiempo de descarga
                delay(100) // Simula un retraso en milisegundos (100 ms por cada incremento)

                // Actualizar progreso en el hilo principal
                withContext(Dispatchers.Main) {
                    textViewProgress.text = "Progreso: $i%"
                }
            }
            // Mensaje al completar la descarga
            withContext(Dispatchers.Main) {
                textViewProgress.text = "Descarga Completa"
            }
        }
    }

    private fun cancelDownload() {
        job?.cancel()
        textViewProgress.text = "Descarga Cancelada"
    }
}