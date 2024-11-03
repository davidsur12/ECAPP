package com.ecapp.ecapp.utils

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.traceEventStart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Configuraciones {

    var ActivateSonido = true
    var fontSizeTitulos = 25f
    var fontSizeNormal = 15.0f


    fun reproducirSonidoConCorrutinas(context: Context, sonidoRawId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mediaPlayer = MediaPlayer.create(context, sonidoRawId)
                mediaPlayer.setOnCompletionListener {
                    mediaPlayer.release()
                }
                mediaPlayer.start()
            } catch (e: Exception) {
                // Manejar errores aquí
                Log.e("ReproductorSonido", "Error al reproducir sonido: ${e.message}")
            }
        }
    }

}