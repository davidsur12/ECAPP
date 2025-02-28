package com.ecapp.ecapp.screen.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ecapp.ecapp.MainActivity
import com.ecapp.ecapp.screen.home.ui.theme.ECAPPTheme
import com.ecapp.ecapp.cloud.LoginScreenViewModel

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Button(
        onClick = {
            // Acción al hacer clic
            println("Cerra session")
            try{
                LoginScreenViewModel().outSession()
                Toast.makeText(context, "Session cerrada", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java)


            }catch(ex : Exception){
                Toast.makeText(context, "Error al Cerrar la Session", Toast.LENGTH_SHORT).show()

            }

        },
        modifier = Modifier.padding(16.dp) // Margen alrededor del botón
    ) {
        Text(text = "Cerrando session")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ECAPPTheme {
        Greeting2("Android")
    }
}