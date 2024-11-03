package com.ecapp.ecapp.screen.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.LoginScreenViewModel
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.screen.HomeScreen
import com.ecapp.ecapp.screen.btnInicioSession
import com.ecapp.ecapp.utils.DateUser

@Composable
fun loginScreen(navController: NavController) {
    //pantalla de login
    var isLoggedIn by remember { mutableStateOf(false) }
    // Botón para alternar el estado
    var viewModel: LoginScreenViewModel // = LoginScreenViewModel();
    Column(
        modifier = Modifier.fillMaxSize(),//ocupo todo el tamaño de la pantalla
        verticalArrangement = Arrangement.Center,//centro el contenido de forma vertical
        horizontalAlignment = Alignment.CenterHorizontally //centro el contenido de forma horizontal
    ) {


        Spacer(modifier = Modifier.height(16.dp))//espacion

        /* Mostrar pantalla según el estado de isLoggedIn si estoy logeado
        me dirigo a la pantalla de perfil de usuario de lo contrario a la pantalla de formulario
        para iniciar session

        */

        if (isLoggedIn) {
            //pantalla de perfil de usuario logeado
            HomeScreen()
        } else {
            //pantalla de perfil de formulario de inicio de session
            Formulario(navController)
        }
    }


}

@Composable
fun Formulario(navController: NavController) {
    val context = LocalContext.current
    var viewModel: LoginScreenViewModel = LoginScreenViewModel();


    var correo by remember { mutableStateOf("") }//controlador de correo Ingresado
    var password by remember { mutableStateOf("") }// controlador de password ingresado



//columna que ocupa toda la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            stringResource(com.ecapp.ecapp.R.string.inicio_session),
            modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold // Cambiar el peso de la fuente (negrita)
            )
        )

        Image(
            painter = painterResource(id = com.ecapp.ecapp.R.drawable.nutricionista),//cargo la imagen
            contentDescription = null,//descripcion nula
        )


//columna pque contiene unn text y un textfield para ingresar el correo
        Column {
            Text(
                "Correo:",
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 20.dp,
                    end = 16.dp,
                    bottom = 20.dp
                ),
                color = colorResource(com.ecapp.ecapp.R.color.white),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp, // Cambiar tamaño de la fuente
                    fontWeight = FontWeight.Bold, // Cambiar el peso de la fuente (negrita)


                )
            )

            TextField(
                value = correo,
                onValueChange = { nuevoText -> correo = nuevoText },//notifico el cambio del textfield
                label = { Text("Ingrese su Correo") },
                modifier = Modifier.width(250.dp)
            )


        }

        Column {
            Text(
                "Contraseña:",
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 20.dp,
                    end = 16.dp,
                    bottom = 20.dp
                ),
                color = colorResource(com.ecapp.ecapp.R.color.white),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp, // Cambiar tamaño de la fuente
                    fontWeight = FontWeight.Bold, // Cambiar el peso de la fuente (negrita)


                )
            )


            TextField(
                value = password,
                visualTransformation = PasswordVisualTransformation(),

                onValueChange = { nuevoPassword -> password = nuevoPassword },
                label = { Text("Contraseña") },
                // visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),//textfield no muestra la contraseña ingresada

                modifier = Modifier.width(250.dp)
            )


        }

        /*
        Boton de inicio de session verifica que los datos se ingresaron de forma correcta
        y de ser asi inteno iniciar session con los usuarios ya registrados de la clase
        LoginScreenViewModel que contienen la funcion signInWithEmailAndPassword
        */
        btnInicioSession(onClick = {
            if (correo.isEmpty() || password.isEmpty()) {
                //sin correo y contraseña estan vacios
                Toast.makeText(
                    context,
                    "Porfavor Ingrese su Correo Y Contraseña",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                Log.d("Informacion", "correo = $correo ")
                Log.d("Informacion2", "password = $password")
                DateUser.correo = correo
                val home = {
                    DateUser.correo = correo
                    navController.navigate(AppScreens.screenUser.route)

                }
                if (esCorreoValido(correo)) {
                    //verifico si la cadena es una direccion de correo validad

                    viewModel.signInWithEmailAndPassword(correo, password, home, context);
                } else {
                    //si los datos se ingresan de forma incorrecta se indica el siguiente toast
                    Toast.makeText(
                        context,
                        "Porfavor Ingrese Correctamente los Datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        })
    }


}

fun esCorreoValido(email: String): Boolean {
    //esta funcion nos devuelve verdadeo o falso si el correo ingresado tiene el formato de un correo convecional
    val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(regex)
}