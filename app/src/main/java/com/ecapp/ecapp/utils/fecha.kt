package com.ecapp.ecapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUser {

fun reseteoDatos(){

    DateUser.nivelSopaLetras = 1
    DateUser.vidasSopaLetras = 5
    DateUser.GameSecuenciaNivel= 1
    DateUser.vidasSecuencia = 5
    DateUser.vidasSopaLetras = 5
    DateUser.nivelSopaLetras = 1
    DateUser.vidasRompecabesas = 5
    DateUser.nivelRompeCabezas = 1
    DateUser.vidasGameMemoria = 5
}
    //calificaciones de los juegos
    //la calificacion se hara con estellas de oro , plata o bronce
    //dificultad la dificulta  se hara segun la calificacion
    /*
    * para el juego de la memoria seraan las mismas iagenes solo que el tiempo sera diferente
    * nivel bronce 2 min
    * nivel plat 1 min
    * nivel oro 30s
    *cantidad de oportunidades 5 para bronce
    *                          3 para plata
    *                          2  oro
    *
    *
    *
    * */

    var fechaNacimiento: String = ""
    var genero: String = ""
    var nombre:String=""
    var apellido:String=""
    var correo:String=""
    var direccion:String=""
    var telefono:String=""
    var FechaConection:String="";


  var calificacionGameMemoria=0
    var erroresGameMemoria=0;
    var vidasGameMemoria=0;
    var totalVidasGameMemoria=5

    //vidas del juego secuencia
    var calificacionGameSecuencia = 0
    var vidasSecuencia=5
    var GameSecuenciaNivel=0
    var erroresGameSecuencia = 0


    //vidas de la sopa de letras
    var  calificacionGameSopaLetras = 0
    var vidasSopaLetras = 5
    var erroresSopaLetras=0
    var nivelSopaLetras = 1

    //vidas rompecabezas
    var  calificacionGameRompeCabezas = 0
    var vidasRompecabesas = 5
    var erroresRompecabezas = 0
    var nivelRompeCabezas = 1


    //vidas  laberinto
    var calificacionGameLaberinto = 5
    var vidasLaberinto = 5
    var nivelLaberinto = 0
    var calificacionLaberinto = 1



    @RequiresApi(Build.VERSION_CODES.O)
    fun getFecha():String {
        val fechaHoraActual = LocalDateTime.now();
        val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")
        val fechaHoraFormateada = fechaHoraActual.format(formato)

        println("Fecha y hora actual: $fechaHoraFormateada")

        return fechaHoraFormateada.toString();
    }







}