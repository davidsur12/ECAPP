package com.ecapp.ecapp.screen.games.secuencia

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Aod
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.screen.games.gameOverMemoria
import com.ecapp.ecapp.screen.games.screenGameMemoriaNivel1
import com.ecapp.ecapp.utils.DateUser
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameSecuencia(navController: NavController){
    Scaffold {



        gameSecuencia(navController)
    }
}


@Composable
fun gameSecuencia(navController: NavController){
    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenGameSecuencia") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    val context = LocalContext.current




    val menuBotones = remember { mutableStateOf(false) }

//lista de imagenes  de la secuencia
    var iconos = listOf(
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.Home,  Icons.Default.Anchor,
        );

    val iconsOpciones = listOf(
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.Home,  Icons.Default.Anchor,
        Icons.Default.Star, Icons.Default.AccessibilityNew,
        Icons.Outlined.Aod,

        )


    var iconosSecuencia =  iconos.shuffled().take(4)
  when(DateUser.GameSecuenciaNivel){
      0->iconos=listOf(
          Icons.Default.Favorite, Icons.Default.AccessAlarm,
          Icons.Default.Home,  Icons.Default.Anchor,
      )
      1-> iconos=listOf(
          Icons.Default.Home,  Icons.Default.Anchor,
          Icons.Default.Favorite, Icons.Default.AccessAlarm,

      )

      2->  iconos=listOf(
              Icons.Default.Star, Icons.Default.AccessibilityNew,
  Icons.Default.Favorite, Icons.Default.AccessAlarm,

      )
      4 -> iconos=listOf(
          Icons.Default.Star, Icons.Default.AccessibilityNew,
          Icons.Default.Home,  Icons.Default.Anchor,

          )

      5 -> iconos=listOf(
          Icons.Default.Star, Icons.Default.AccessibilityNew,
          Icons.Default.Favorite, Icons.Default.AccessAlarm,
          Icons.Default.Home,  Icons.Default.Anchor,
      )
       6 ->  iconos=listOf(
           Icons.Default.Favorite, Icons.Default.AccessAlarm,
           Icons.Default.Star, Icons.Default.AccessibilityNew,
           Icons.Default.Home,  Icons.Default.Anchor,
       )
      7->  iconos=listOf(
          Icons.Default.Favorite, Icons.Default.AccessAlarm,
          Icons.Default.Home,  Icons.Default.Anchor,
          Icons.Default.Star, Icons.Default.AccessibilityNew,

      )
      8 -> iconos=listOf(
          Icons.Default.Favorite, Icons.Default.AccessAlarm,
          Icons.Default.Home,  Icons.Default.Anchor,
          Icons.Default.Star, Icons.Default.AccessibilityNew,
          Icons.Default.Favorite, Icons.Default.AccessAlarm,
      )
      9-> iconos=listOf(
          Icons.Default.Favorite, Icons.Default.AccessAlarm,
          Icons.Default.Star, Icons.Default.AccessibilityNew,
          Icons.Default.Home,  Icons.Default.Anchor,
          Icons.Default.Favorite, Icons.Default.AccessAlarm,
      )


  }

    var vidas by remember { mutableStateOf(0) }
    val showLazyRow = remember { mutableStateOf(true) }
    val opcionesRow = remember { mutableStateOf(false) }
    val btnInicioGame = remember { mutableStateOf(true) }
    val txtInicio = remember { mutableStateOf(true) }
    val btnPista = remember { mutableStateOf(false) }
    val btnPistaVisibility = remember { mutableStateOf(false) }
    vidas = DateUser.vidasSecuencia
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {

    Spacer(modifier = Modifier.height(50.dp))
    Text("Actividad de Secuencia", fontSize = 25.sp,  textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        Text("Nivel ${DateUser.GameSecuenciaNivel+1}")
        Spacer(modifier = Modifier.height(20.dp))
    Text("Total de Vidas $vidas ")

        Spacer(modifier = Modifier.height(10.dp))
        if(txtInicio.value){
            //si es verdadero muestra el texto de observar las siguientes imagenes de lo contrario
            //el texto de completa la secuencia que acabas de ver
        }
        Text(if(txtInicio.value)"Observa la Secuencia de las Imagenes " else " Completa la Secuencia que Acabas de Ver")
    Spacer(modifier = Modifier.height(20.dp))


        if (showLazyRow.value) {
            //secuencia a seguir
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                items(iconos) { icono ->
                    Image(
                        imageVector = icono,
                        contentDescription = "Icono",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
        if (btnPista.value == true) {
            LaunchedEffect (Unit) {
                delay(2000) // Esperar 3 segundos
                btnPista.value = false // Ocultar el texto después de 3 segundos
            }

            if (btnPista.value == true) {
                //secuencia a seguir
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(iconos) { icono ->
                        Image(
                            imageVector = icono,
                            contentDescription = "Icono",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }



        if(btnInicioGame.value){

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp) // Añade un margen alrededor del botón
            ) {


                Button(onClick = {  showLazyRow.value = false
                    opcionesRow.value = true
                    btnInicioGame.value=false
                    menuBotones.value=!menuBotones.value
                    txtInicio.value = false
                    btnPistaVisibility.value=true

                },    modifier = Modifier.align(Alignment.BottomCenter).width(300.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Estoy Listo")
                }
            }



        }



}

    // Estado para manejar la lista de iconos
    var iconosSelecionados = remember { mutableStateListOf<ImageVector>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Fila para mostrar los iconos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            iconosSelecionados.forEach { icono ->
                Image(
                    imageVector = icono,
                    contentDescription = "Icono",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
            }
        }
//menu de iconos de ociones
        if(menuBotones.value){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                iconsOpciones.forEach { icono ->
                    Image(
                        imageVector = icono,
                        contentDescription = "Icono",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp)
                            .clickable{
                                iconosSelecionados.add(icono)
                            }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
/*
        // Botón para agregar un icono
        Button(onClick = {
            // Añade un icono nuevo a la lista
            iconosSelecionados.add(Icons.Default.Star)
        }) {
            Text("Agregar Icono")
        }*/
if(iconos == iconosSelecionados){

    // silas listas son iguales pasas al siguiente nivel
    if(DateUser.GameSecuenciaNivel==9){
        DateUser.GameSecuenciaNivel=15
        //navController.navigate(AppScreens.screenGameOverSecuencia.route)
        Toast.makeText(context, "Felicitaciones", Toast.LENGTH_SHORT).show()

        navController.navigate(AppScreens.screenFelicitacionesGameSecuencia.route)
    }
    else{

        Toast.makeText(context, "listas similares ", Toast.LENGTH_SHORT).show()
        DateUser.GameSecuenciaNivel = DateUser.GameSecuenciaNivel+1
        DateUser.erroresGameSecuencia = 0
        navController.navigate(AppScreens.screenGameSecuencia.route)

    }
}
        if(iconosSelecionados.size == iconos.size && iconos != iconosSelecionados){
            //si la selecion es incorrecta
            DateUser.erroresGameSecuencia=  DateUser.erroresGameSecuencia+1
            iconosSelecionados.clear()

            if(DateUser.erroresGameSecuencia == 2){
                vidas=DateUser.vidasSecuencia-1
                Toast.makeText(context, "Las Imagenes no Coinciden $vidas", Toast.LENGTH_SHORT).show()
                DateUser.erroresGameSecuencia = 0
                DateUser.vidasSecuencia =  DateUser.vidasSecuencia-1

                if(  vidas == 0){
                    //vamos a la pantalla de game over perdimos las 5 vidas
                    navController.navigate(AppScreens.screenGameOverSecuencia.route)
                }
            }
        }



        Spacer(modifier = Modifier.height(8.dp))
/*
        // Botón para quitar el último icono
        Button(onClick = {
            // Quita el último icono de la lista si hay alguno
            menuBotones.value=!menuBotones.value
            if (iconosSelecionados.isNotEmpty()) {
                iconosSelecionados.removeLast()
            }
        }) {
            Text("Quitar Icono")
        }
        */

/*
        if(DateUser.GameSecuenciaNivel>=8){

            navController.navigate(AppScreens.screenGameOverSecuencia.route)
            //Toast.makeText(context, "Felicitaciones", Toast.LENGTH_SHORT).show()

            // navController.navigate(AppScreens.screenFelicitacionesGameSecuencia.route)
        }
        */
        if(btnPistaVisibility.value){


            Button(onClick = {

                btnPista.value=true
                Toast.makeText(context, "Pista", Toast.LENGTH_SHORT).show()
            }, modifier = Modifier.width(300.dp)) {
                Text("Pista")
            }

            Button(onClick = {
                // Quita el último icono de la lista si hay alguno

                iconosSelecionados.clear()
            }, modifier = Modifier.width(300.dp)) {
                Text("Borrar")
            }


        }

    }
}

