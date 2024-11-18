package com.ecapp.ecapp.screen.games.sopa_de_letras

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.utils.Configuraciones

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenGameSopaLetras(navController: NavController) {
    BackHandler {
        navController.navigate("screenGames") {
            popUpTo("ScreenGameRompeCabezas") { inclusive = true } // Elimina la pantalla actual de la pila
        }
    }

    Scaffold {
        gameSopaLetras(navController)
    }
}
@Composable
fun gameSopaLetras(navController: NavController) {
    val niveles = listOf(
        NivelConfig(
            letras = listOf(
                'P', 'E', 'R', 'R', 'O',
                'F', 'G', 'A', 'T', 'O',
                'K', 'L', 'O', 'B', 'O',
                'O', 'B', 'O', 'G', 'O',
                'U', 'L', 'E', 'O', 'N'
            ),
            indicesCorrectos = listOf(0, 1, 2, 3, 4,6, 7, 8, 9,11, 12, 13, 14, 21, 22, 23, 24),
            palabras = "PERRO, GATO, LOBO, LEON"
        ),
        NivelConfig(
            letras = listOf(
                'L', 'O', 'B', 'O', 'X',
                'G', 'A', 'T', 'O', 'P',
                'C', 'D', 'A', 'D', 'M',
                'T', 'O', 'R', 'O', 'Y',
                'X', 'Y', 'C', 'A', 'O'
            ),
            indicesCorrectos = listOf(0, 1, 2, 3, 5,6,7,8,15,16,17,18),
            palabras = "LOBO, GATO, TORO"
        ),
        NivelConfig(
            letras = listOf(
                'O', 'L', 'L', 'A', 'X',
                'C', 'A', 'Z', 'O', 'P',
                'X', 'V', 'A', 'S', 'O',
                'A', 'Z', 'A', 'T', 'Y',
                'M', 'N', 'U', 'E', 'Z'
            ),
            indicesCorrectos = listOf(0, 1, 2, 3, 5, 6, 7, 8, 11, 12, 13, 14,15, 16, 17, 18, 21,22,23,24,),
            palabras = "OLLA, CAZO, VASO, TAZA, NUEZ"
        ),
        NivelConfig(
            letras = listOf(
                'P', 'A', 'P', 'A', 'R',
                'C', 'A', 'C', 'A', 'O',
                'T', 'D', 'V', 'N', 'A',
                'F', 'R', 'I', 'J', 'O',
                'M', 'N', 'U', 'E', 'Z'
            ),
            indicesCorrectos = listOf(0, 1, 2, 3, 5, 6, 7, 8, 9, 21,22,23,24),
            palabras = "PAPA, CACAO, NUEZ"
        ),
        // Agrega más configuraciones de niveles según sea necesario
    )


    var nivelActual by remember { mutableStateOf(0) }
    var vidas by remember { mutableStateOf(5) }
    val context = LocalContext.current
    var gameOverNavigated by remember { mutableStateOf(false) }
    var congratulated by remember { mutableStateOf(false) }

    val nivelConfig = if (nivelActual < niveles.size) niveles[nivelActual] else null
    val selectedIndices = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Sopa de Letras",
            fontSize = Configuraciones.fontSizeNormal.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text("Vidas: $vidas", fontSize = Configuraciones.fontSizeNormal.sp, color = Color.White)
        Text("Nivel: ${nivelActual + 1}",fontSize = Configuraciones.fontSizeNormal.sp, color = Color.White)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Encuentra las palabras:",
            fontSize = Configuraciones.fontSizeNormal.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))

        if (nivelConfig != null) {
            Text(
                nivelConfig.palabras,
                fontSize = Configuraciones.fontSizeNormal.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            nivelConfig?.let {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    itemsIndexed(it.letras) { index, letter ->
                        LetterBox(
                            letter = letter,
                            index = index,
                            selectedIndices = selectedIndices,
                            indicesCorrectos = it.indicesCorrectos
                        ) {
                            if (!it.indicesCorrectos.contains(index)) {
                                vidas--
                                Toast.makeText(context, "¡Letra incorrecta! Perdiste una vida.", Toast.LENGTH_SHORT).show()

                                // Verifica si se han quedado sin vidas
                                if (vidas <= 0 && !gameOverNavigated) {
                                    gameOverNavigated = true // Asegura que la navegación solo ocurra una vez
                                    navController.navigate(AppScreens.screenGameOverSopaLetras.route)
                                }
                            } else if (selectedIndices.containsAll(it.indicesCorrectos)) {
                                // Nivel completado correctamente
                                selectedIndices.clear()
                                nivelActual++

                                if (nivelActual >= niveles.size && !congratulated) {
                                    congratulated = true
                                    navController.navigate(AppScreens.screenFelicitacionesGameSopaLetras.route)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LetterBox(
    letter: Char,
    index: Int,
    selectedIndices: MutableList<Int>,
    indicesCorrectos: List<Int>,
    onSelect: () -> Unit
) {
    val isSelected = selectedIndices.contains(index)
    val isCorrect = indicesCorrectos.contains(index) && isSelected
    val isIncorrect = isSelected && !indicesCorrectos.contains(index)

    Box(
        modifier = Modifier
            .size(48.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(4.dp))
            .clickable {
                if (!isSelected) {
                    selectedIndices.add(index)
                    onSelect()
                }
            }
            .background(
                when {
                    isCorrect -> Color(0xFF00C853) // Verde para correcta
                    isIncorrect -> Color(0xFFD50000) // Rojo para incorrecta
                    else -> Color.White // Fondo blanco para no seleccionada
                },
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            fontSize = Configuraciones.fontSizeNormal.sp,
            fontWeight = FontWeight.Bold,
            color = when {
                isCorrect || isIncorrect -> Color.White
                else -> Color.Black
            }
        )
    }
}

data class NivelConfig(
    val letras: List<Char>,
    val indicesCorrectos: List<Int>,
    val palabras: String
)

@Composable
fun gameSopaLetrasNivel2(navController: NavController){}
@Composable
fun gameSopaLetrasNivel3(navController: NavController){}

