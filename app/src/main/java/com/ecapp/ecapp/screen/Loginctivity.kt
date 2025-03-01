package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecapp.ecapp.screen.home.Home
import com.ecapp.ecapp.cloud.LoginScreenViewModel

import com.ecapp.ecapp.ui.theme.ECAPPTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class Loginctivity : ComponentActivity() {

    //private lateinit var auth: FirebaseAuth
 var viewModel: LoginScreenViewModel = LoginScreenViewModel();

    public override fun onStart() {
        super.onStart()

        viewModel = LoginScreenViewModel();
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = viewModel.auth.currentUser
        if (currentUser != null) {
            Log.d("login", "Usuario Inicio session")
        }
        else{
            Log.d("login", "Usuario No inicio session")
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            ECAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    //Formulario()
                    MyApp()
                }
            }
        }
    }
}



@Composable
fun MyApp(){
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
            Formulario()
        }
    }


}


@Composable
fun HomeScreen(){

    Button(onClick = {
        Firebase.auth.signOut()
    }) {
        Text(text =  "Cerrar sesión" )

    }


}

@Composable
fun btnInicioSession(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White // Cambiar el fondo a blanco
        ),
        modifier = Modifier.padding(16.dp)
            .width(300.dp)
    ) {
        Text(text = "Iniciar Session")
    }
}

@Composable
fun Formulario() {
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
            Log.d("Informacion" , "correo = $correo ")
            Log.d("Informacion2" , "password = $password")

            val home = {
                val intent = Intent(context, Home::class.java)
                context.startActivity(intent)
            }

             viewModel.signInWithEmailAndPassword(correo, password, home , context);


        })
    }



}


