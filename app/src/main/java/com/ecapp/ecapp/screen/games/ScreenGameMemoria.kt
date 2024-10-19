package com.ecapp.ecapp.screen.games

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameMemoria(navController: NavController){
    Scaffold {

        // GameRompecabesas(navController)
        //GameMemoria( navController)
        IconPairsGame()
    }
}

@Composable
fun  GameMemoria(navController: NavController){

Text("Juego de Memoria")

    Icon(
        imageVector = Icons.Outlined.DirectionsCar, // Usa un icono predeterminado
        contentDescription = "Icono de Corazón",
        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
    )
}

@Preview
@Composable
fun pr(){

    Column{
        Icon(
            tint = Color.White,
            imageVector = Icons.Outlined.Image, // Usa un icono predeterminado
            contentDescription = "Icono de Corazón",
            modifier = Modifier.size(24.dp)
                .size(48.dp) // Ajusta el tamaño del ícono
                .clickable {
                    // Acción al hacer clic en el ícono
                    Log.d("ClickableIcon", "Ícono clickeado")
                }, // Ajusta el tamaño del icono

        )
    }


}

@Composable
fun IconPairsGame() {
    val context = LocalContext.current
    // Lista de iconos (simula las parejas)
    val icons = listOf(
        Icons.Default.Favorite, Icons.Default.Favorite,
        Icons.Default.Home, Icons.Default.Home,
        Icons.Default.Star, Icons.Default.Star
    )
    var estados1= mutableListOf(false, false, false, false, false, false,)
    val estados = remember { mutableStateListOf(*Array(icons.size){estados1} )}
    var indiceSelectAnterior:Int=0
    val listaCodigos= listOf(1,2,3)

    val estadosColor = remember {
        mutableStateListOf(*Array(icons.size) { mutableStateOf(false) })
    }
//color
    // Estado para controlar la visibilidad de cada ícono
    val activadoColor = remember { mutableStateListOf(*Array(icons.size) { false }) }
    // Estado para controlar la visibilidad de cada ícono
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }


    val isSelected = remember { mutableStateOf(false) }

    // Icono que cambia de color cuando es clickeado
    Icon(
        //imageVector = icons[0],
        painter = painterResource(id = com.ecapp.ecapp.R.drawable.uno), // Cambia a tu recurso de ícono
        contentDescription = null,
        tint = if (isSelected.value) Color.Green else Color.Gray, // Cambia el color según el estado
        modifier = Modifier
            .size(48.dp)
            .clickable {
                // Cambia el estado al hacer clic
                isSelected.value = !isSelected.value // Alterna el estado
                Toast.makeText(context, "Cambia de color ${isSelected.value} ", Toast.LENGTH_SHORT).show()
            }
    )

    LazyVerticalGrid(//divido en dos columnas las imagenes a mostrar
        columns = GridCells.Fixed(2), // Ajustar el número de columnas según el diseño
        modifier = Modifier.fillMaxSize()//todo el ancho de la pantalla
    ) {
        var cont:Int=0;
        items(estadosColor.size)
        //Recorro la lista de imagenes
        { index ->
            //por cada imagen verifico si la puedo ver o ocultar segun la lista de iconVisibility
            val estado = estadosColor[index]
            if (iconVisibility[index]){
                //se muestra el icono si a un no se a encontrado la pareja de lo contrario se vuelven invisibles los dos imagenes
                Icon(
                    // cargo la imagen o icono a mostrar seguan la lista
                    imageVector = icons[index],
                    contentDescription = "Icon",
                    tint = if (estadosColor[index].value) Color.Green else Color.Gray,//si la imagen es selecionada  cambia de color a verde
                    modifier = Modifier
                        .size(80.dp)
                        .padding(16.dp)
                        .clickable {
                            //si la imagen es igual a la anterior se elimina de la lista de lo contrario solo vuelven hacer de color gris
                            /*
                            *el usuario tiene un total de 5 vidas tendra un cronometro cada segun la dificulta el tiempo sera menor pero
                            * tendra mas intentos
                            *
                            * colocar mas imagenes y cuando poner un puntaje en firebase y cuando un cronometro
                            * el puntaje denera tener  una calificacion en estrellas oro plata y brince
                            *
                            * poener las imagenes en desorden y varias imagenes
                            * */
                            estado.value = !estado.value
                            estados1[index] = true
                            cont++
                            //handleIconClick(index, icons, iconVisibility)
                            Toast.makeText(context, "index icono $index  select icon $indiceSelectAnterior ", Toast.LENGTH_SHORT).show()
                            if(icons[index] == icons[indiceSelectAnterior] && index != indiceSelectAnterior && cont==2){

                                iconVisibility[index] = false
                                iconVisibility[indiceSelectAnterior] = false

                                estadosColor[index].value = false
                                estadosColor[indiceSelectAnterior].value = false


                                cont=0//reinicio el contador
                            }
                            else{

                            }

                            if(cont == 2){
                                estadosColor[index].value=false
                                estadosColor[indiceSelectAnterior].value=false
                                cont=0

                            }
                            indiceSelectAnterior=index
                        }

                )
            }


        }
    }


    /*
        // Grid para mostrar los íconos
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Se pueden ajustar las columnas según el diseño deseado
            modifier = Modifier.fillMaxSize()
        ) {
            items(icons.size) { index ->
                if (iconVisibility[index]) {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(16.dp)

                           .clickable {
                               estados1[index] = true

                                //handleIconClick(index, icons, iconVisibility)
                               Toast.makeText(context, "index icono $index ", Toast.LENGTH_SHORT).show()
                               if(icons[index] == icons[indiceSelectAnterior] && index != indiceSelectAnterior ){

                                   iconVisibility[index] = false
                                   iconVisibility[indiceSelectAnterior] = false
                               }else{
                                   estados[index] = true
                               }
                               indiceSelectAnterior=index
                            },

                        tint = if (estados[index]) Color.Green else Color.Gray,
                    )
                }
            }
        }

        */


}





