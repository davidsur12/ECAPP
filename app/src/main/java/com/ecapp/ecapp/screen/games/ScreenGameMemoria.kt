package com.ecapp.ecapp.screen.games

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Aod
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Button
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.DateUser


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameMemoria(navController: NavController){
    Scaffold {

        // GameRompecabesas(navController)
        //GameMemoria( navController)
      //  IconPairsGame(navController)
       // ImageWithClickableCircle()

        ImageWithSurroundingCircle(navController)
    }
}





@Composable
fun IconPairsGame(navController: NavController) {
    val context = LocalContext.current
    var cont:Int=0;//contador de emparejamiento
    var contParejasCompletadas:Int =0
    var errores:Int=0
    var  columnaFelicitaciones by remember { mutableStateOf(false) }
    // Lista de iconos (simula las parejas)
    val icons = listOf(
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.Home,  Icons.Default.Anchor,
        Icons.Default.Star,Icons.Default.AccessAlarm ,
        Icons.Default.Favorite,Icons.Default.Star,
        Icons.Default.Anchor, Icons.Default.Home,
        Icons.Outlined.Aod,Icons.Outlined.Aod,
        Icons.Default.AccessibilityNew,Icons.Default.AcUnit,
        Icons.Default.AcUnit, Icons.Default.AccessibilityNew,

    )



    var estados1= remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var indiceSelectAnterior:Int=0
    val estadosColor = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    // Estado para controlar la visibilidad de cada ícono
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }
    val isSelected = remember { mutableStateOf(false) }
    // Icono que cambia de color cuando es clickeado


    var isColumnVisible by remember { mutableStateOf(false) }


    Spacer(modifier = Modifier.height(50.dp))
    Text("Encuentra la pareja")

    LazyVerticalGrid(//divido en dos columnas las imagenes a mostrar
        columns = GridCells.Fixed(2), // Ajustar el número de columnas según el diseño
        modifier = Modifier.fillMaxSize()//todo el ancho de la pantalla
    ) {

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
                            estados1[index].value = true
                            cont++

                            //Toast.makeText(context, "index icono $index  select icon $indiceSelectAnterior ", Toast.LENGTH_SHORT).show()
                            if(icons[index] == icons[indiceSelectAnterior] && index != indiceSelectAnterior && cont==2){
                                //pareja unida correctamente se procede a eliminar
                                contParejasCompletadas++
                                iconVisibility[index] = false
                                iconVisibility[indiceSelectAnterior] = false

                                estadosColor[index].value = false
                                estadosColor[indiceSelectAnterior].value = false


                                cont=0//reinicio el contador
                                if(contParejasCompletadas == 8){
                                    //juego terminado me dirijo a la pantalla de calificaciones
                                    isColumnVisible = true

                                    Toast.makeText(context, "Felicitaciones completaste la actividad", Toast.LENGTH_SHORT).show()
                                    navController.navigate(AppScreens.screenFelicitacionesMemoria.route)
                                    DateUser.erroresGameMemoria=errores//si son 0 errores  3 estrella
                                    //1 error 2 estrellas
                                    //sin
                                    errores=0
                                }
                            }


                            if(cont == 2){
                                estadosColor[index].value=false
                                estadosColor[indiceSelectAnterior].value=false
                                cont=0
                                errores++

                            }
                            indiceSelectAnterior=index
                        }

                )
            }


        }
    }
}
//cronometro
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}
@Composable
fun Stopwatch() {
    var time by remember { mutableStateOf(0) } // Estado para llevar el tiempo en segundos
    var isRunning by remember { mutableStateOf(false) }
    // Efecto que se ejecuta cuando el cronómetro está corriendo
    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (true) {
                kotlinx.coroutines.delay(1000L) // Esperar 1 segundo
                time++
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = formatTime(time), // Mostrar el tiempo en formato mm:ss
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { isRunning = true }
            ) {
                Text("Start")
            }

            Button(
                onClick = { isRunning = false }
            ) {
                Text("Stop")
            }

            Button(
                onClick = {
                    isRunning = false
                    time = 0
                }
            ) {
                Text("Reset")
            }
        }
    }
}


@Composable
fun ImageWithClickableCircle() {
    // Estado para controlar la visibilidad del círculo
    var isCircleVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(100.dp) // Tamaño del Box
            .padding(16.dp) // Espacio alrededor del Box
    ) {
        // Imagen o Icono
        Image(
            painter = painterResource(id = com.ecapp.ecapp.R.drawable.uno), // Reemplaza con tu recurso de imagen
            contentDescription = "Your Image",
            modifier = Modifier
                .fillMaxSize() // Ocupa todo el espacio del Box
                .clickable { // Manejar el clic
                    isCircleVisible = !isCircleVisible // Cambia la visibilidad del círculo
                }
        )

        // Círculo rojo, solo se muestra si isCircleVisible es true
        if (isCircleVisible) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center) // Alinear al centro del Box
                    .size(40.dp) // Tamaño del círculo
                    .background(Color.Red, shape = CircleShape) // Color y forma del círculo
            )
        }
    }
}



@Composable
fun ImageWithSurroundingCircle(navController: NavController) {


    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    val context = LocalContext.current
    var cont:Int=0;//contador de emparejamiento
    var contParejasCompletadas:Int =0
    var errores:Int=0
    var  columnaFelicitaciones by remember { mutableStateOf(false) }
    // Lista de iconos (simula las parejas)
    val icons = listOf(
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.Home,  Icons.Default.Anchor,
        Icons.Default.Star,Icons.Default.AccessAlarm ,
        Icons.Default.Favorite,Icons.Default.Star,
        Icons.Default.Anchor, Icons.Default.Home,
        Icons.Outlined.Aod,Icons.Outlined.Aod,
        Icons.Default.AccessibilityNew,Icons.Default.AcUnit,
        Icons.Default.AcUnit, Icons.Default.AccessibilityNew,

        )

    var imagenesSeleccionadas  = mutableListOf<Int>()
    var vidas:Int=5
    var isCircleVisible = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }

    var estados1= remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var indiceSelectAnterior:Int=0
    val estadosColor = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    // Estado para controlar la visibilidad de cada ícono
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }
    val isSelected = remember { mutableStateOf(false) }
    // Icono que cambia de color cuando es clickeado


    var isColumnVisible by remember { mutableStateOf(false) }


    Spacer(modifier = Modifier.height(50.dp))
    Text("Encuentra la pareja")
Column {
    LazyVerticalGrid(//divido en dos columnas las imagenes a mostrar
    columns = GridCells.Fixed(4), // Ajustar el número de columnas según el diseño

    modifier = Modifier.fillMaxWidth()//todo el ancho de la pantalla
        , verticalArrangement = Arrangement.Center
        , horizontalArrangement = Arrangement.Center
) {

    items(estadosColor.size)
    //Recorro la lista de imagenes
    { index ->
        //por cada imagen verifico si la puedo ver o ocultar segun la lista de iconVisibility
        val estado = estadosColor[index]
        if (iconVisibility[index]){
            //se muestra el icono si a un no se a encontrado la pareja de lo contrario se vuelven invisibles los dos imagenes

            Box(
                modifier = Modifier
                    .size(100.dp) // Tamaño del Box
                    .padding(16.dp) // Espacio alrededor del Box
                    .clickable { // Manejar el clic


                        estado.value = !estado.value
                        estados1[index].value = true
                        isCircleVisible[index].value = !isCircleVisible[index].value // Cambia la visibilidad del círculo

                   if(Icons.Default.Favorite == icons[index]){
                       //si se escojen las imagenes correctas se agrega a la lista para su conteo
                       imagenesSeleccionadas.add(index)
                       if(imagenesSeleccionadas.size==2){
                           navController.navigate(AppScreens.screenGameMmeorianivel2.route)
                       }
                   }else{
                       //si se equivoca pierde un intento
                       //imagenesSeleccionadas.clear()//limpio la lista
                       vidas-- // menos una vida
                       DateUser.erroresGameMemoria=vidas


                       if(vidas == 0){//si termina con las 5 vidas
                           navController.navigate(AppScreens.screenGameOverMemoria.route)
                       }



                   }
                    }
            ) {
                // Círculo rojo que rodea la imagen
                if (isCircleVisible[index].value) {
                    Box(
                        modifier = Modifier
                            .matchParentSize() // Ocupa todo el tamaño del Box

                            .background(Color.Red, shape = CircleShape) // Color y forma del círculo
                    )
                }

                // Imagen o Icono
                Icon(
                    // cargo la imagen o icono a mostrar seguan la lista
                    imageVector = icons[index],
                    contentDescription = "Icon",
                    tint = if (estadosColor[index].value) Color.White else Color.Gray,//si la imagen es selecionada  cambia de color a verde
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)

                )
            }


        }


    }
}

    Box(
        modifier = Modifier
            .fillMaxWidth() // Ocupa todo el espacio disponible
    ) {
        Text(
            text = "Porfavor Encuentra la Siguiente Imagen",
            modifier = Modifier.align(Alignment.Center), // Centra el texto en el Box
            fontSize = 20.sp,
            color = Color.Black
        )

    }

    Spacer(modifier = Modifier.height(20.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth() // Ocupa todo el espacio disponible
    ) {


        Icon(
            // cargo la imagen o icono a mostrar seguan la lista
            imageVector = Icons.Default.Favorite,
            contentDescription = "Icon",
            tint = Color.Red ,//si la imagen es selecionada  cambia de color a verde
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp).align(Alignment.Center)


        )
    }
}



}


@Composable
fun screenGameMemoriaNivel2(navController: NavController) {


    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenGameMemorianivel2") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    val context = LocalContext.current
    var cont:Int=0;//contador de emparejamiento
    var contParejasCompletadas:Int =0
    var errores:Int=DateUser.erroresGameMemoria
    var  columnaFelicitaciones by remember { mutableStateOf(false) }
    // Lista de iconos (simula las parejas)
    val icons = listOf(
        com.ecapp.ecapp.R.drawable.siete,com.ecapp.ecapp.R.drawable.ocho,
        com.ecapp.ecapp.R.drawable.cinco,com.ecapp.ecapp.R.drawable.seis,
        com.ecapp.ecapp.R.drawable.uno, com.ecapp.ecapp.R.drawable.dos,
        com.ecapp.ecapp.R.drawable.siete,com.ecapp.ecapp.R.drawable.ocho,
        com.ecapp.ecapp.R.drawable.tres,  com.ecapp.ecapp.R.drawable.cuatro,
        com.ecapp.ecapp.R.drawable.siete,com.ecapp.ecapp.R.drawable.ocho,
        com.ecapp.ecapp.R.drawable.nueve, com.ecapp.ecapp.R.drawable.uno,
        com.ecapp.ecapp.R.drawable.siete,com.ecapp.ecapp.R.drawable.dos,
        com.ecapp.ecapp.R.drawable.cinco,com.ecapp.ecapp.R.drawable.seis,
        com.ecapp.ecapp.R.drawable.ocho,com.ecapp.ecapp.R.drawable.dos,




        )


    var imagenesSeleccionadas  = mutableListOf<Int>()
    var vidas:Int=5
    var isCircleVisible = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }

    var estados1= remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var indiceSelectAnterior:Int=0
    val estadosColor = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    // Estado para controlar la visibilidad de cada ícono
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }
    val isSelected = remember { mutableStateOf(false) }
    // Icono que cambia de color cuando es clickeado


    var isColumnVisible by remember { mutableStateOf(false) }


    Spacer(modifier = Modifier.height(50.dp))
    Text("Encuentra la pareja")
    Column {
        LazyVerticalGrid(//divido en dos columnas las imagenes a mostrar
            columns = GridCells.Fixed(4), // Ajustar el número de columnas según el diseño

            modifier = Modifier.fillMaxWidth()//todo el ancho de la pantalla
            , verticalArrangement = Arrangement.Center
            , horizontalArrangement = Arrangement.Center
        ) {

            items(estadosColor.size)
            //Recorro la lista de imagenes
            { index ->
                //por cada imagen verifico si la puedo ver o ocultar segun la lista de iconVisibility
                val estado = estadosColor[index]
                if (iconVisibility[index]){
                    //se muestra el icono si a un no se a encontrado la pareja de lo contrario se vuelven invisibles los dos imagenes

                    Box(
                        modifier = Modifier
                            .size(100.dp) // Tamaño del Box
                            .padding(16.dp) // Espacio alrededor del Box
                            .clickable { // Manejar el clic


                                estado.value = !estado.value
                                estados1[index].value = true
                                isCircleVisible[index].value = !isCircleVisible[index].value // Cambia la visibilidad del círculo

                                if(com.ecapp.ecapp.R.drawable.ocho== icons[index]){
                                    //si se escojen las imagenes correctas se agrega a la lista para su conteo
                                    imagenesSeleccionadas.add(index)
                                    if(imagenesSeleccionadas.size==4){
                                        navController.navigate(AppScreens.screenGameMmeorianivel3.route)
                                    }
                                }else{
                                    //si se equivoca pierde un intento
                                    //imagenesSeleccionadas.clear()//limpio la lista
                                    errores++ // menos una vida
                                    DateUser.erroresGameMemoria=errores


                                    if(errores == 5){//si termina con las 5 vidas
                                        navController.navigate(AppScreens.screenGameOverMemoria.route)
                                    }



                                }
                            }
                    ) {
                        // Círculo rojo que rodea la imagen
                        if (isCircleVisible[index].value) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize() // Ocupa todo el tamaño del Box

                                    .background(Color.Red, shape = CircleShape) // Color y forma del círculo
                            )
                        }

                        // Imagen o Icono

                        Icon(
                            // cargo la imagen o icono a mostrar seguan la lista
                            painter = painterResource(id = icons[index]),
                            //imageVector = icons[index],
                            contentDescription = "Icon",
                            tint = if (estadosColor[index].value) Color.White else Color.Gray,//si la imagen es selecionada  cambia de color a verde
                            modifier = Modifier
                                .size(100.dp)
                                .padding(16.dp)

                        )


                    }


                }


            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el espacio disponible
        ) {
            Text(
                text = "Porfavor Encuentra la Siguiente Numero",
                modifier = Modifier.align(Alignment.Center), // Centra el texto en el Box
                fontSize = 20.sp,
                color = Color.Black
            )

        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el espacio disponible
        ) {


            Icon(
                // cargo la imagen o icono a mostrar seguan la lista
               // imageVector = icons[5],
                painter = painterResource(id = icons[2]),
                contentDescription = "Icon",
                tint = Color.Red ,//si la imagen es selecionada  cambia de color a verde
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp).align(Alignment.Center)


            )
        }
    }



}


@Composable
fun screenGameMemoriaNivel3(navController: NavController) {


    BackHandler{
        navController.navigate("screenGames") {
            popUpTo("screenGameMemorianivel3") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    val context = LocalContext.current
    var cont:Int=0;//contador de emparejamiento
    var contParejasCompletadas:Int =0
    var errores:Int=DateUser.erroresGameMemoria
    var  columnaFelicitaciones by remember { mutableStateOf(false) }
    // Lista de iconos (simula las parejas)
    val icons = listOf(
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.Home,  Icons.Default.Anchor,
        Icons.Default.AccessAlarm,Icons.Default.AccessAlarm ,
        Icons.Default.Favorite,Icons.Default.Star,
        Icons.Default.Anchor, Icons.Default.AccessAlarm,
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.AccessAlarm,Icons.Outlined.Aod,
        Icons.Default.AccessibilityNew,Icons.Default.AcUnit,
        Icons.Default.AcUnit, Icons.Default.AccessAlarm,

        )

    var imagenesSeleccionadas  = mutableListOf<Int>()
    var vidas:Int=5
    var isCircleVisible = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }

    var estados1= remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var indiceSelectAnterior:Int=0
    val estadosColor = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    // Estado para controlar la visibilidad de cada ícono
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }
    val isSelected = remember { mutableStateOf(false) }
    // Icono que cambia de color cuando es clickeado


    var isColumnVisible by remember { mutableStateOf(false) }


    Spacer(modifier = Modifier.height(50.dp))
    Text("Encuentra la pareja")
    Column {
        LazyVerticalGrid(//divido en dos columnas las imagenes a mostrar
            columns = GridCells.Fixed(4), // Ajustar el número de columnas según el diseño

            modifier = Modifier.fillMaxWidth()//todo el ancho de la pantalla
            , verticalArrangement = Arrangement.Center
            , horizontalArrangement = Arrangement.Center
        ) {

            items(estadosColor.size)
            //Recorro la lista de imagenes
            { index ->
                //por cada imagen verifico si la puedo ver o ocultar segun la lista de iconVisibility
                val estado = estadosColor[index]
                if (iconVisibility[index]){
                    //se muestra el icono si a un no se a encontrado la pareja de lo contrario se vuelven invisibles los dos imagenes

                    Box(
                        modifier = Modifier
                            .size(100.dp) // Tamaño del Box
                            .padding(16.dp) // Espacio alrededor del Box
                            .clickable { // Manejar el clic


                                estado.value = !estado.value
                                estados1[index].value = true
                                isCircleVisible[index].value = !isCircleVisible[index].value // Cambia la visibilidad del círculo

                                if(Icons.Default.AccessAlarm == icons[index]){
                                    //si se escojen las imagenes correctas se agrega a la lista para su conteo
                                    imagenesSeleccionadas.add(index)
                                    if(imagenesSeleccionadas.size==7){
                                        navController.navigate(AppScreens.screenFelicitacionesMemoria.route)
                                    }
                                }else{
                                    //si se equivoca pierde un intento
                                    //imagenesSeleccionadas.clear()//limpio la lista
                                    errores++
                                    vidas-- // menos una vida
                                    DateUser.erroresGameMemoria=vidas



                                    if(errores == 5){//si termina con las 5 vidas
                                        navController.navigate(AppScreens.screenGameOverMemoria.route)
                                    }



                                }
                            }
                    ) {
                        // Círculo rojo que rodea la imagen
                        if (isCircleVisible[index].value) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize() // Ocupa todo el tamaño del Box

                                    .background(Color.Red, shape = CircleShape) // Color y forma del círculo
                            )
                        }

                        // Imagen o Icono
                        Icon(
                            // cargo la imagen o icono a mostrar seguan la lista
                            imageVector = icons[index],
                            contentDescription = "Icon",
                            tint = if (estadosColor[index].value) Color.White else Color.Gray,//si la imagen es selecionada  cambia de color a verde
                            modifier = Modifier
                                .size(100.dp)
                                .padding(16.dp)

                        )
                    }


                }


            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el espacio disponible
        ) {
            Text(
                text = "Porfavor Encuentra la Siguiente Imagen",
                modifier = Modifier.align(Alignment.Center), // Centra el texto en el Box
                fontSize = 20.sp,
                color = Color.Black
            )

        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el espacio disponible
        ) {


            Icon(
                // cargo la imagen o icono a mostrar seguan la lista
                imageVector = Icons.Default.AccessAlarm,
                contentDescription = "Icon",
                tint = Color.Red ,//si la imagen es selecionada  cambia de color a verde
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp).align(Alignment.Center)


            )
        }
    }



}











