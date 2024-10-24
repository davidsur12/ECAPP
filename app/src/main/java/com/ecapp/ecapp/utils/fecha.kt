package com.ecapp.ecapp.utils

object DateUser {


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


  var calificacionGameMemoria=0
    var erroresGameMemoria=0;
    var vidasGameMemoria=0;
    var totalVidasGameMemoria=5

    //vidas del juego secuencia

    var vidasSecuencia=5
    var GameSecuenciaNivel=0
    var erroresGameSecuencia = 0


    //vidas de la sopa de letras

    var vidasSopaLetras = 5
    var erroresSopaLetras=0
    var nivelSopaLetras = 1

    //vidas rompecabezas

    var vidasRompecabesas = 5
    var erroresRompecabezas = 0
    var nivelRompeCabezas = 1











}