package com.ecapp.ecapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecapp.ecapp.ui.theme.ECAPPTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.ecapp.ecapp.navegation.AppNavegation
import com.ecapp.ecapp.screen.HomeScreenn
import com.ecapp.ecapp.screen.Loginctivity
import com.ecapp.ecapp.screen.login.LoginScreenViewModel


class MainActivity : ComponentActivity() {


    var viewModel: LoginScreenViewModel  = LoginScreenViewModel();

    public override fun onStart() {
        super.onStart()

        viewModel = LoginScreenViewModel();
        // verifico si tengo alguna  session activa

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
                Scaffold(modifier = Modifier.fillMaxSize().background(Color(0xba00ca))) {
                    AppNavegation()
                //app()
                   /* Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    */
                }
            }


        }
    }
}


@Composable
fun app(){

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize().background(colorResource(R.color.morado_fondo)),  // Ocupa todo el espacio disponible
        verticalArrangement = Arrangement.Top,  // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally   // Centra los elementos verticalmente
         ) {

            Text("ECAAP",
                modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 45.sp, // Cambiar tamaño de la fuente
                    fontWeight = FontWeight.Bold // Cambiar el peso de la fuente (negrita)
                ))



        Image(painter = painterResource(id = R.drawable.nutricionista, ),
            contentDescription = null,
           // modifier = Modifier.background(Color.Red).padding(50.dp)
             )


        Text("Aplicación Estimulación Cognitiva" ,
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),
            color = colorResource(R.color.white),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 18.sp, // Cambiar tamaño de la fuente
                fontWeight = FontWeight.Bold, // Cambiar el peso de la fuente (negrita)


            ))
        // Dibujar una línea horizontal
        Canvas(
            modifier = Modifier
                .fillMaxWidth() // Ocupar el ancho completo
                .height(15.dp) // Altura de la línea
                .padding(5.dp)
        ) {

            drawLine(
                color = Color.Blue, // Color de la línea
                start = androidx.compose.ui.geometry.Offset(0f, 0f), // Punto de inicio (x, y)
                end = androidx.compose.ui.geometry.Offset(size.width, 0f), // Punto final (x, y)
                strokeWidth = 2f // Grosor de la línea
            )
        }


        btnRegistro(onClick = {
            // Acción para el botón simple
            println("Botón Simple presionado")

try{
    val intent = Intent(context, Loginctivity::class.java)
    context.startActivity(intent)
}catch(ex: Exception){
    Log.d("error" , "${ex.message}");

}


            // Crear Intent para iniciar SecondActivity

        }, "Iniciar Session")

        btnRegistro(onClick = {
            // Acción para el botón simple
            println("Botón Simple presionado")
        }, "Registrarse")
    }
    }




@Composable
fun btnRegistro(onClick: () -> Unit, nombre: String) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White // Cambiar el fondo a blanco
        ),
        modifier = Modifier.padding(16.dp)
            .width(300.dp)
    ) {
        Text(text = nombre)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ECAPPTheme {
        Greeting("Android")
    }


}