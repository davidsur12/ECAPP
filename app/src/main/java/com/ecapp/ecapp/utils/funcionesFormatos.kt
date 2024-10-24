package com.ecapp.ecapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun obtenerFechaHoraConFormato(): String {
    val fechaHoraActual = LocalDateTime.now()
    val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val fechaHoraFormateada = fechaHoraActual.format(formato)
    println("Fecha y Hora Formateada: $fechaHoraFormateada")
    return fechaHoraFormateada
}