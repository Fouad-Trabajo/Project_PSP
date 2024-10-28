package com.fouadaha.pspcorrutinas.ejerciciogordo

import android.os.Bundle
import android.widget.*


import androidx.appcompat.app.AppCompatActivity

import com.fouadaha.pspcorrutinas.R
import com.fouadaha.pspcorrutinas.databinding.Tarea6Binding
import kotlinx.coroutines.*

class Tarea6 : AppCompatActivity() {

    /**
     * Para que el binding funcione correctamente,
     * debes tener esto en el build.gradle.kts(app)
     * ```
     * buildFeatures {
     *         viewBinding =  true
     *     }
     *
     */
    private lateinit var binding: Tarea6Binding // la view tarea6.xml
    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Tarea6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //llamada al método que inicia la descarga pulsando en el botón "descargar"
        binding.buttonDownload.setOnClickListener {
            startDownload()
        }


        // llamada al método que para la descarga pulsando en el botón "cancelar"
        binding.buttonCancelDownload.setOnClickListener {
            cancelDownload()
        }

    }

    private fun startDownload() {
        //canelar las descargas anteriores si existen
        job?.cancel()

        //iniciar una descarga
        job = GlobalScope.launch(Dispatchers.IO) {
            for (i in 1..100) { //barra de descarga de 100%

                delay(200) //tiempo de espera entre cada % de la barra

                //actualizar el texView con el progreso de la descarga
                withContext(Dispatchers.Main) {
                    binding.downloadProgress.text = "Descargando: $i%"
                }

            }
            //mostrarr mensaje de descarga completa
            withContext(Dispatchers.Main) {
                binding.downloadProgress.text = "Descarga completada"
            }
        }


    }

    private fun cancelDownload() {
        //canelar descarga y mostrar mensaje de cancelación
        job?.cancel()
        binding.downloadProgress.text = "Descarga cancelada"
    }

}





