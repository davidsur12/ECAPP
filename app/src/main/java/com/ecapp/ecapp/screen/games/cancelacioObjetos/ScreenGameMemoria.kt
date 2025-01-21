package com.ecapp.ecapp.screen.games.cancelacioObjetos

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Aod
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones
import com.ecapp.ecapp.utils.DateUser

/*
El siguiente juego consta de encontrar las imagenes iguales a la imagen principal
en una cuadricula cuando se encuentren todas las imagenes se pasara al siguiente nivel
este juego consta de 3 niveles por cadan error se pierde una vida.
*/



@Composable
fun ScreenGameMemoria(navController: NavController) {
    BackHandler {
        navController.navigate("screenGames") {
            popUpTo("screenMemoria") { inclusive = true }
        }
    }
    var nivel by remember { mutableStateOf(1) }
    var errores by remember { mutableStateOf(0) }
    var vidas by remember { mutableStateOf(5) }
    val imagenesCorrectasSeleccionadas = remember { mutableSetOf<Int>() }

    // Define 20 niveles, cada uno con una lista de 16 íconos y un ícono objetivo
    val iconosPorNivel = listOf(
        Pair(listOf(Icons.Default.Favorite, Icons.Default.Home, Icons.Default.Star, Icons.Default.Favorite,
            Icons.Default.Star, Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Star,
            Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Home,
            Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Home, Icons.Default.Star), Icons.Default.Favorite),

        Pair(listOf(Icons.Default.Star, Icons.Default.AccessAlarm, Icons.Default.Favorite, Icons.Default.Star,
            Icons.Default.Favorite, Icons.Default.AccessAlarm, Icons.Default.Star, Icons.Default.Favorite,
            Icons.Default.AccessAlarm, Icons.Default.Star, Icons.Default.Favorite, Icons.Default.AccessAlarm,
            Icons.Default.Star, Icons.Default.Favorite, Icons.Default.AccessAlarm, Icons.Default.Star), Icons.Default.AccessAlarm),

        Pair(listOf(Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Home, Icons.Default.Email,
            Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Favorite,
            Icons.Default.Home, Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Home,
            Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Home, Icons.Default.Favorite), Icons.Default.Email),

        Pair(listOf(Icons.Default.Phone, Icons.Default.Star, Icons.Default.Email, Icons.Default.Phone,
            Icons.Default.Email, Icons.Default.Star, Icons.Default.Phone, Icons.Default.Email,
            Icons.Default.Star, Icons.Default.Phone, Icons.Default.Email, Icons.Default.Star,
            Icons.Default.Phone, Icons.Default.Email, Icons.Default.Star, Icons.Default.Phone), Icons.Default.Phone),

        Pair(listOf(Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Phone, Icons.Default.Favorite,
            Icons.Default.Phone, Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Phone,
            Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Phone, Icons.Default.Email,
            Icons.Default.Favorite, Icons.Default.Phone, Icons.Default.Email, Icons.Default.Favorite), Icons.Default.Phone),

        Pair(listOf(Icons.Default.Home, Icons.Default.Star, Icons.Default.Favorite, Icons.Default.Phone,
            Icons.Default.Home, Icons.Default.Star, Icons.Default.Email, Icons.Default.Favorite,
            Icons.Default.Star, Icons.Default.Home, Icons.Default.Email, Icons.Default.Phone,
            Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Phone, Icons.Default.Star), Icons.Default.Star),

        Pair(listOf(Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Home, Icons.Default.Email,
            Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Star, Icons.Default.Email,
            Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Phone, Icons.Default.Email,
            Icons.Default.Star, Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Phone), Icons.Default.Home),

        Pair(listOf(Icons.Default.Star, Icons.Default.Star, Icons.Default.Email, Icons.Default.Email,
            Icons.Default.Phone, Icons.Default.Phone, Icons.Default.Home, Icons.Default.Home,
            Icons.Default.Favorite, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Star,
            Icons.Default.Home, Icons.Default.Email, Icons.Default.Phone, Icons.Default.Favorite), Icons.Default.Email),

        Pair(listOf(Icons.Default.Favorite, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Email,
            Icons.Default.Phone, Icons.Default.Home, Icons.Default.Email, Icons.Default.Favorite,
            Icons.Default.Star, Icons.Default.Star, Icons.Default.Favorite, Icons.Default.Email,
            Icons.Default.Phone, Icons.Default.Home, Icons.Default.Star, Icons.Default.Favorite), Icons.Default.Phone),

        Pair(listOf(Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Email,
            Icons.Default.Star, Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Phone,
            Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Email, Icons.Default.Star,
            Icons.Default.Favorite, Icons.Default.Phone, Icons.Default.Email, Icons.Default.Home), Icons.Default.Star),

        // Continúa agregando listas similares para los niveles del 11 al 20...

        Pair(listOf(Icons.Default.Home, Icons.Default.Phone, Icons.Default.Star, Icons.Default.Email,
            Icons.Default.Favorite, Icons.Default.Home, Icons.Default.Star, Icons.Default.Phone,
            Icons.Default.Email, Icons.Default.Star, Icons.Default.Email, Icons.Default.Favorite,
            Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Home, Icons.Default.Star), Icons.Default.Home),

        Pair(listOf(Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Phone,
            Icons.Default.Email, Icons.Default.Star, Icons.Default.Home, Icons.Default.Phone,
            Icons.Default.Star, Icons.Default.Email, Icons.Default.Home, Icons.Default.Favorite,
            Icons.Default.Phone, Icons.Default.Star, Icons.Default.Favorite, Icons.Default.Home), Icons.Default.Favorite),

        Pair(listOf(Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Home,
            Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Email,
            Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Phone,
            Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Phone, Icons.Default.Home), Icons.Default.Email),

        Pair(listOf(Icons.Default.Star, Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Home,
            Icons.Default.Phone, Icons.Default.Star, Icons.Default.Home, Icons.Default.Favorite,
            Icons.Default.Phone, Icons.Default.Email, Icons.Default.Home, Icons.Default.Star,
            Icons.Default.Email, Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Star), Icons.Default.Phone),

        Pair(listOf(Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Email,
            Icons.Default.Phone, Icons.Default.Star, Icons.Default.Favorite, Icons.Default.Email,
            Icons.Default.Star, Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Home,
            Icons.Default.Email, Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Star), Icons.Default.Star),

        Pair(listOf(Icons.Default.Email, Icons.Default.Home, Icons.Default.Phone, Icons.Default.Star,
            Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Home, Icons.Default.Email,
            Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Phone, Icons.Default.Favorite,
            Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Home), Icons.Default.Phone),

        Pair(listOf(Icons.Default.Favorite, Icons.Default.Phone, Icons.Default.Email, Icons.Default.Star,
            Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Phone,
            Icons.Default.Email, Icons.Default.Home, Icons.Default.Star, Icons.Default.Favorite,
            Icons.Default.Phone, Icons.Default.Home, Icons.Default.Email, Icons.Default.Star), Icons.Default.Email),

        Pair(listOf(Icons.Default.Star, Icons.Default.Email, Icons.Default.Phone, Icons.Default.Favorite,
            Icons.Default.Home, Icons.Default.Star, Icons.Default.Email, Icons.Default.Favorite,
            Icons.Default.Star, Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Star,
            Icons.Default.Email, Icons.Default.Home, Icons.Default.Phone, Icons.Default.Favorite), Icons.Default.Home),

        Pair(listOf(Icons.Default.Email, Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Star,
            Icons.Default.Home, Icons.Default.Phone, Icons.Default.Star, Icons.Default.Favorite,
            Icons.Default.Star, Icons.Default.Email, Icons.Default.Phone, Icons.Default.Favorite,
            Icons.Default.Home, Icons.Default.Star, Icons.Default.Email, Icons.Default.Favorite), Icons.Default.Star),

        Pair(listOf(Icons.Default.Favorite, Icons.Default.Email, Icons.Default.Star, Icons.Default.Home,
            Icons.Default.Phone, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Email,
            Icons.Default.Phone, Icons.Default.Star, Icons.Default.Favorite, Icons.Default.Home,
            Icons.Default.Email, Icons.Default.Favorite, Icons.Default.Star, Icons.Default.Home), Icons.Default.Star)
    )
    // Asigna el conjunto de íconos y el ícono objetivo para el nivel actual
    val icons = iconosPorNivel.getOrElse(nivel - 1) { iconosPorNivel[0] }.first
    val targetIcon = iconosPorNivel.getOrElse(nivel - 1) { iconosPorNivel[0] }.second
    val totalImagenesCorrectas = icons.count { it == targetIcon }

    // Lista para almacenar el estado de selección de cada ícono
    val iconSelectedStates = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Texto de nivel actual
        Text(
            text = "Nivel: $nivel",
            fontSize = Configuraciones.fontSizeTitulos.sp,
            style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
         //   color = Color.Black,
            textAlign = TextAlign.Center
        )

        Text(
            "Cancelación de Objetos",
            fontSize = Configuraciones.fontSizeTitulos.sp,
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
          //  color = Color.White
        )

        Text("Total de Vidas: $vidas",  style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold), fontSize = Configuraciones.fontSizeNormal.sp)

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),  // Cambiamos a 4 columnas para 4x4
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
            items(icons.size) { index ->
                Box(
                    modifier = Modifier
                        .size(80.dp)  // Ajusta el tamaño de los iconos según sea necesario
                        .padding(8.dp)
                        .clickable {
                            if (!iconSelectedStates[index].value) {
                                iconSelectedStates[index].value = true  // Cambia a rojo solo el ícono seleccionado

                                if (icons[index] == targetIcon) {
                                    imagenesCorrectasSeleccionadas.add(index)

                                    if (imagenesCorrectasSeleccionadas.size == totalImagenesCorrectas) {
                                        if (nivel < iconosPorNivel.size) {
                                            nivel++
                                            imagenesCorrectasSeleccionadas.clear()
                                            iconSelectedStates.replaceAll { mutableStateOf(false) }  // Resetea los colores para el nuevo nivel
                                        } else {
                                            DateUser.calificacionGameMemoria =vidas
                                            DateUser.vidasGameMemoria =vidas
                                            navController.navigate(AppScreens.screenFelicitacionesMemoria.route)
                                        }
                                    }
                                } else {
                                    errores++
                                    vidas--
                                    if (vidas <= 0) {
                                        navController.navigate(AppScreens.screenGameOverMemoria.route)
                                    }
                                }
                            }
                        }
                ) {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = "Icon",
                        tint = if (iconSelectedStates[index].value) Color.Red else Color.White,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(8.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Por favor Encuentra la Siguiente Imagen",
                modifier = Modifier.align(Alignment.Center),
                fontSize = Configuraciones.fontSizeNormal.sp,
                style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
                //color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = targetIcon,
                contentDescription = "Target Icon",
                tint = Color.Red,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameMemoria2(navController: NavController){
    Scaffold {
//scaffold se llama comienza en el nivel 1
        screenGameMemoriaNivel1(navController)
    }
}
@Composable
fun screenGameMemoriaNivel1(navController: NavController) {
    BackHandler {
        navController.navigate("screenGames") {
            popUpTo("screenMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    //la siguiente variable es un contador que permiten llevar un control para reducir una vida segun los errores
    var errores by remember { mutableStateOf(0) }
    var vidas by remember { mutableStateOf(5) }//inicia con 5 vidas

    // Set para almacenar las imágenes correctas seleccionadas
    val imagenesCorrectasSeleccionadas = remember { mutableSetOf<Int>() }

    // Lista de iconos  de la cuadricula
    val icons = listOf(
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.Home, Icons.Default.Anchor,
        Icons.Default.Star, Icons.Default.AccessAlarm,
        Icons.Default.Favorite, Icons.Default.Star,
        Icons.Default.Anchor, Icons.Default.Home,
        Icons.Outlined.Aod, Icons.Outlined.Aod,
        Icons.Default.AccessibilityNew, Icons.Default.AcUnit,
        Icons.Default.AcUnit, Icons.Default.AccessibilityNew
    )
//variables que permiten colorear el fondo en rojo uando se selecione una imagen
    var isCircleVisible = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var estados1 = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var estadosColor = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }

    // Determina cuántas imágenes correctas hay en total
    val totalImagenesCorrectas = icons.count { it == Icons.Default.Favorite }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "Cancelacion de Objetos", fontSize = Configuraciones.fontSizeTitulos.sp, textAlign = TextAlign.Center,
            color = Color.White
        )
        Text("Total de Vidas $vidas", color = Color.White, fontSize = Configuraciones.fontSizeNormal.sp)

        LazyVerticalGrid(
            //este es un componente grafico que permite crear una cuadricula vertical
            columns = GridCells.Fixed(4), // Ajustar el número de columnas según el diseño
            modifier = Modifier.fillMaxWidth(), //todo el ancho de la pantalla
            verticalArrangement = Arrangement.Center,//se alinea de forma central
            horizontalArrangement = Arrangement.Center//se alinea de forma central
        ) {
            //se usa para iterar una lista de elementos
            items(estadosColor.size) { index ->
                //le agrego el color de fondo segun si es selecionado o no
                val estado = estadosColor[index]
                if (iconVisibility[index]) {

                    //si es verdadero no asido selecionado por lotanto solo se muestra en icono en blanco
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)
                            .clickable {
                                estado.value = !estado.value
                                estados1[index].value = true
                                isCircleVisible[index].value = !isCircleVisible[index].value

                                if (Icons.Default.Favorite == icons[index]) {
                                    // Si se selecciona la imagen correcta, se añade a la lista de imágenes correctas
                                    imagenesCorrectasSeleccionadas.add(index)


                                    // Verificar si se han seleccionado todas las imágenes correctas
                                    if (imagenesCorrectasSeleccionadas.size == totalImagenesCorrectas) {
                                        DateUser.vidasGameMemoria = vidas
                                        navController.navigate(AppScreens.screenGameMmeorianivel2.route)
                                    }
                                } else {
                                    // Si la imagen es incorrecta, reducir una vida
                                    /*
                                    el contador de errores se da ya que debe completarse  cirto numero de errores
                                    para reducir una vida
                                    */
                                    errores++
                                    vidas-- // Resta una vida
                                    DateUser.erroresGameMemoria = errores//los errores se siguen contando
                                    DateUser.vidasGameMemoria = vidas

                                    if (vidas <= 0) { // Si termina con las vidas
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

                        // Icono
                        Icon(
                            imageVector = icons[index],
                            contentDescription = "Icon",
                            tint = if (estadosColor[index].value) Color.White else Color.White,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
//texto informativo
        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el espacio disponible
        ) {
            Text(
                text = "Por favor Encuentra la Siguiente Imagen",
                modifier = Modifier.align(Alignment.Center),
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el espacio disponible
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Icon",
                tint = Color.Red,
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun screenGameMemoriaNivel2(navController: NavController) {
    BackHandler {
        navController.navigate("screenGames") {
            popUpTo("screenGameMemorianivel2") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    val context = LocalContext.current
    var errores: Int = DateUser.erroresGameMemoria
    var vidas by remember { mutableStateOf(DateUser.vidasGameMemoria) }

    // Set para almacenar las imágenes correctas seleccionadas
    val imagenesCorrectasSeleccionadas = remember { mutableSetOf<Int>() }
    // Set para almacenar las imágenes incorrectas ya seleccionadas
    val selectedIncorrectIndices = remember { mutableSetOf<Int>() }

     //lista de imagenes de la cuadricula
    val icons = listOf(
        com.ecapp.ecapp.R.drawable.siete, com.ecapp.ecapp.R.drawable.ocho,
        com.ecapp.ecapp.R.drawable.cinco, com.ecapp.ecapp.R.drawable.seis,
        com.ecapp.ecapp.R.drawable.uno, com.ecapp.ecapp.R.drawable.dos,
        com.ecapp.ecapp.R.drawable.siete, com.ecapp.ecapp.R.drawable.ocho,
        com.ecapp.ecapp.R.drawable.tres, com.ecapp.ecapp.R.drawable.cuatro,
        com.ecapp.ecapp.R.drawable.siete, com.ecapp.ecapp.R.drawable.ocho,
        com.ecapp.ecapp.R.drawable.nueve, com.ecapp.ecapp.R.drawable.uno,
        com.ecapp.ecapp.R.drawable.siete, com.ecapp.ecapp.R.drawable.dos,
        com.ecapp.ecapp.R.drawable.cinco, com.ecapp.ecapp.R.drawable.seis,
        com.ecapp.ecapp.R.drawable.ocho, com.ecapp.ecapp.R.drawable.dos
    )

    var isCircleVisible = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var estados1 = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    val estadosColor = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }

    // Determina cuántas imágenes correctas (R.drawable.ocho) hay en total
    val totalImagenesCorrectas = icons.count { it == com.ecapp.ecapp.R.drawable.ocho }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("Cancelación de Objetos",fontSize = Configuraciones.fontSizeTitulos.sp, textAlign = TextAlign.Center, color = Color.White)
        Text("Total de Vidas $vidas", color = Color.White, fontSize = Configuraciones.fontSizeNormal.sp)

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
            items(estadosColor.size) { index ->
                val estado = estadosColor[index]
                if (iconVisibility[index]) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)
                            .clickable {
                                estado.value = !estado.value
                                estados1[index].value = true
                                isCircleVisible[index].value = !isCircleVisible[index].value

                                if (com.ecapp.ecapp.R.drawable.ocho == icons[index]) {
                                    // Si se selecciona la imagen correcta, se añade a la lista de imágenes correctas
                                    imagenesCorrectasSeleccionadas.add(index)
                                   // Toast.makeText(context, "Lista correcta: ${imagenesCorrectasSeleccionadas}", Toast.LENGTH_SHORT).show()

                                    // Verificar si se han seleccionado todas las imágenes correctas
                                    if (imagenesCorrectasSeleccionadas.size == totalImagenesCorrectas) {
                                        DateUser.vidasGameMemoria = vidas
                                        navController.navigate(AppScreens.screenGameMmeorianivel3.route)
                                    }
                                } else {
                                    // Reducir una vida si la imagen es incorrecta
                                    if (!selectedIncorrectIndices.contains(index)) {
                                        vidas-- // Resta una vida
                                        selectedIncorrectIndices.add(index) // Marcar la imagen como seleccionada incorrectamente
                                        errores++
                                        DateUser.erroresGameMemoria = errores
                                        DateUser.vidasGameMemoria = vidas

                                       // Toast.makeText(context, "Imágenes falsas", Toast.LENGTH_SHORT).show()
                                        if (vidas <= 0) { // Si termina con las vidas
                                            navController.navigate(AppScreens.screenGameOverMemoria.route)
                                        }
                                    }
                                }
                            }
                    ) {
                        // Círculo rojo que rodea la imagen
                        if (isCircleVisible[index].value) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(Color.Red, shape = CircleShape)
                            )
                        }

                        // Imagen o Icono
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = "Icon",
                            tint = if (estadosColor[index].value) Color.White else Color.White,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Por favor Encuentra el Siguiente Número",
                modifier = Modifier.align(Alignment.Center),
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = com.ecapp.ecapp.R.drawable.ocho),
                contentDescription = "Icon",
                tint = Color.Red,
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
@Composable
fun screenGameMemoriaNivel3(navController: NavController) {
    BackHandler {
        navController.navigate("screenGames") {
            popUpTo("screenMemoria") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    val context = LocalContext.current
    var errores by remember { mutableStateOf(0) }
    var vidas by remember { mutableStateOf(5) }
vidas=DateUser.vidasGameMemoria
    // Set para almacenar las imágenes correctas seleccionadas
    val imagenesCorrectasSeleccionadas = remember { mutableSetOf<Int>() }

    // Lista de iconos (simula las parejas)
    val icons = listOf(
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.Home, Icons.Default.Anchor,
        Icons.Default.AccessAlarm, Icons.Default.AccessAlarm,
        Icons.Default.Favorite, Icons.Default.Star,
        Icons.Default.Anchor, Icons.Default.AccessAlarm,
        Icons.Default.Favorite, Icons.Default.AccessAlarm,
        Icons.Default.AccessAlarm, Icons.Outlined.Aod,
        Icons.Default.AccessibilityNew, Icons.Default.AcUnit,
        Icons.Default.AcUnit, Icons.Default.AccessAlarm
    )

    var isCircleVisible = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var estados1 = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    var estadosColor = remember { mutableStateListOf(*Array(icons.size) { mutableStateOf(false) }) }
    val iconVisibility = remember { mutableStateListOf(*Array(icons.size) { true }) }

    // Determina cuántas imágenes correctas (Icons.Default.AccessAlarm) hay en total
    val totalImagenesCorrectas = icons.count { it == Icons.Default.AccessAlarm }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "Cancelacion de Objetos", fontSize = Configuraciones.fontSizeTitulos.sp, textAlign = TextAlign.Center,
            color = Color.White
        )
        Text("Total de Vidas $vidas", color = Color.White,  fontSize = Configuraciones.fontSizeNormal.sp)

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
            items(estadosColor.size) { index ->
                val estado = estadosColor[index]
                if (iconVisibility[index]) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(16.dp)
                            .clickable {
                                estado.value = !estado.value
                                estados1[index].value = true
                                isCircleVisible[index].value = !isCircleVisible[index].value

                                if (Icons.Default.AccessAlarm == icons[index]) {
                                    // Si se selecciona la imagen correcta, se añade a la lista de imágenes correctas
                                    imagenesCorrectasSeleccionadas.add(index)
                                   // Toast.makeText(context, "Ítem correcto", Toast.LENGTH_SHORT).show()

                                    // Verificar si se han seleccionado todas las imágenes correctas
                                    if (imagenesCorrectasSeleccionadas.size == totalImagenesCorrectas) {
                                        DateUser.vidasGameMemoria = vidas
                                        navController.navigate(AppScreens.screenFelicitacionesMemoria.route)
                                    }
                                } else {
                                    // Reducir una vida si la imagen es incorrecta
                                    errores++
                                    vidas-- // Resta una vida
                                   // Toast.makeText(context, "Ítem erróneo", Toast.LENGTH_SHORT).show()
                                    DateUser.erroresGameMemoria = errores
                                    DateUser.vidasGameMemoria = vidas

                                    if (vidas <= 0) { // Si termina con las vidas
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
                            imageVector = icons[index],
                            contentDescription = "Icon",
                            tint = if (estadosColor[index].value) Color.White else Color.White,
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
                .fillMaxWidth()
        ) {
            Text(
                text = "Por favor Encuentra la Siguiente Imagen",
                modifier = Modifier.align(Alignment.Center),
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.AccessAlarm,
                contentDescription = "Icon",
                tint = Color.Red,
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }
    }
}



















