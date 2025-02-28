package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await



@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenn(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //backgroundColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo)
    ) {
        app(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun app(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val db = FirebaseFirestore.getInstance()
   // var isRegistered by remember { mutableStateOf<Boolean?>(null) }
    var isRegistered = remember { mutableStateOf<Boolean?>(null) }


    // Verifica si el usuario está registrado en Firestore
    LaunchedEffect(user) {
        if (user != null) {
            try {
                val document = db.collection("usuarios").document(user.uid).get().await()
                isRegistered.value = document.exists() // Si el documento existe, el usuario está registrado
            } catch (e: Exception) {
                isRegistered.value = false
            }
        } else {
            isRegistered.value = false
        }
    }

    // Si ya se verificó que el usuario no está en Firestore, cerrar sesión
    LaunchedEffect(isRegistered) {
        if (isRegistered.value == false) {
            auth.signOut() // Cerrar sesión si el usuario no está registrado
        }
    }

    BackHandler {
        navController.navigate("screenHome") {
            popUpTo("screenHome") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
            .verticalScroll(rememberScrollState()),  // Permite desplazamiento vertical
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "ECAPP",
            modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Image(
            painter = painterResource(id = com.ecapp.ecapp.R.drawable.nutricionista),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Text(
            "Aplicación de Estimulación Cognitiva para Personas Adultas Mayores",
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),
            color = colorResource(com.ecapp.ecapp.R.color.white),
            style = androidx.compose.ui.text.TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .padding(vertical = 10.dp)
        ) {
            drawLine(
                color = Color.Blue,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2f
            )
        }

        // Botón según el estado de la verificación
        when (isRegistered.value) {
            true -> btnRegistro(
                onClick = { navController.navigate(AppScreens.screenUser.route) },
                nombre = "Actividades"
            )
            false -> btnRegistro(
                onClick = { navController.navigate(AppScreens.screenRegisterUser.route) },
                nombre = "Registrarse"
            )
            else -> LoadingIndicator()
        }
    }
}


@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 4.dp
        )
    }
}


@Composable
fun btnRegistro(onClick: () -> Unit, nombre: String) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(300.dp)
    ) {
        Text(text = nombre, fontSize = 18.sp)
    }
}



