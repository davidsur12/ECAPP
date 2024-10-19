package com.ecapp.ecapp.screen

import android.content.Intent
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.screen.home.Home
import com.ecapp.ecapp.screen.login.LoginScreenViewModel
import com.ecapp.ecapp.utils.DateUser

@Composable
fun loginScreen(navController: NavController){
    var isLoggedIn by remember { mutableStateOf(false) }
    // Botón para alternar el estado
    var viewModel: LoginScreenViewModel // = LoginScreenViewModel();
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isLoggedIn = !isLoggedIn }) {
            Text(text = if (isLoggedIn) "Cerrar sesión" else "Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar pantalla según el estado de isLoggedIn
        if (isLoggedIn) {
            HomeScreen()
        } else {
            Formulario2(navController)
        }
    }


}

@Composable
fun Formulario2(navController: NavController) {
    val context = LocalContext.current
    var viewModel: LoginScreenViewModel = LoginScreenViewModel();


    var correo by remember { mutableStateOf("") }//correo Ingresado
    var password by remember { mutableStateOf("") }// password ingresado




    Column(    modifier = Modifier
        .fillMaxSize().background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            stringResource( com.ecapp.ecapp.R.string.inicio_session),
            modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold // Cambiar el peso de la fuente (negrita)
            ))

        Image(painter = painterResource(id =  com.ecapp.ecapp.R.drawable.nutricionista, ),
            contentDescription = null,
            // modifier = Modifier.background(Color.Red).padding(50.dp)
        )



        Column  {
            Text("Correo:" ,
                modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),
                color = colorResource(com.ecapp.ecapp.R.color.white),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp, // Cambiar tamaño de la fuente
                    fontWeight = FontWeight.Bold, // Cambiar el peso de la fuente (negrita)


                ))

            TextField(
                value = correo,

                onValueChange = { newText -> correo = newText },
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
                onValueChange = { newPassword -> password = newPassword },
                label = { Text("Contraseña") },
                // visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {

                },
                modifier = Modifier.width(250.dp)
            )





        }
        btnInicioSession(onClick = {
            if(correo.isEmpty() || password.isEmpty()){
                //sin correo y contraseña estan vacios
                Toast.makeText(context, "Porfavor Ingrese su Correo Y Contraseña", Toast.LENGTH_SHORT).show()
            }else{

                Log.d("Informacion" , "correo = $correo ")
                Log.d("Informacion2" , "password = $password")
                DateUser.correo=correo
                val home = {
                    navController.navigate(AppScreens.screenUser.route)
                   // val intent = Intent(context, Home::class.java)
                    //context.startActivity(intent)
                }
               if(esCorreoValido(correo)){//verifico si la cadena es una direccion de correo validad

                   viewModel.signInWithEmailAndPassword(correo, password, home, context );
               }else{
                   Toast.makeText(context, "Porfavor Ingrese Correctamente los Datos", Toast.LENGTH_SHORT).show()
               }

            }




        })
    }



}

fun esCorreoValido(email: String): Boolean {
    val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(regex)
}