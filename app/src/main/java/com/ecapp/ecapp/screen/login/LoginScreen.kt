package com.ecapp.ecapp.screen.login

import UserPreferences
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.LoginScreenViewModel
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.screen.HomeScreen
import com.ecapp.ecapp.screen.btnInicioSession
import com.ecapp.ecapp.utils.DateUser

import kotlinx.coroutines.launch

@Composable
fun loginScreen(navController: NavController) {
    //pantalla de login
    var isLoggedIn by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState() // Estado del scroll
    // Botón para alternar el estado
    var viewModel: LoginScreenViewModel // = LoginScreenViewModel();
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)), // Habilita el scroll
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
    val userPreferences = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showUserDialog by remember { mutableStateOf(false) }
    var selectedUser by remember { mutableStateOf<String?>(null) }
    var users by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var isFirstLaunch by remember { mutableStateOf(true) } // Nuevo estado

    // Obtener usuarios guardados y mostrar el AlertDialog solo en el primer inicio
    LaunchedEffect(Unit) {
        userPreferences.users.collect { storedUsers ->
            users = storedUsers
            if (isFirstLaunch && storedUsers.isNotEmpty()) {
                showUserDialog = true
                isFirstLaunch = false // Evita que se vuelva a abrir automáticamente después de iniciar sesión
            }
        }
    }

    if (showUserDialog) {
        AlertDialog(
            onDismissRequest = { showUserDialog = false },
            title = { Text("Cuentas Guardadas") },
            text = {
                Column {
                    users.forEach { (email, _) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedUser = email }
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedUser == email,
                                onCheckedChange = { selectedUser = email }
                            )
                            Text(email, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Button(
                        onClick = {
                            selectedUser?.let { email ->
                                correo = email
                                password = users[email] ?: ""
                                showUserDialog = false
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Seleccionar cuenta")
                    }
                }
            },
            dismissButton = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Button(
                        onClick = {
                            if (selectedUser != null) {
                                coroutineScope.launch {
                                    userPreferences.deleteUser(selectedUser!!)
                                    selectedUser = null
                                    showUserDialog = false // Cierra el AlertDialog después de eliminar
                                }
                            } else {
                                Toast.makeText(context, "No seleccionaste ninguna cuenta", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Eliminar cuenta")
                    }
                }
            }
        )

    }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text("Inicio de sesión", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 16.dp, top = 90.dp, end = 16.dp, bottom = 20.dp))

        // Botón para mostrar usuarios guardados manualmente


        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = com.ecapp.ecapp.R.drawable.nutricionista),//cargo la imagen
            contentDescription = null,//descripcion nula
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Text("Correo:", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Ingrese su Correo") },
                modifier = Modifier.width(250.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Text("Contraseña:", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.width(250.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Botón para iniciar sesión
        Button(
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .width(300.dp),
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Blue),
            onClick = {
                if (correo.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Ingrese su correo y contraseña", Toast.LENGTH_SHORT).show()
                } else {
                    if (esCorreoValido(correo)) {
                        val home = {
                            DateUser.correo = correo
                            navController.navigate(AppScreens.screenUser.route)
                        }

                        val viewModel = LoginScreenViewModel()
                        viewModel.signInWithEmailAndPassword(correo, password, home, context)

                        // Guardar usuario después del login exitoso
                        coroutineScope.launch {
                            userPreferences.saveUser(correo, password)
                            isFirstLaunch = false // Evita que el AlertDialog se muestre justo después de guardar
                        }
                    } else {
                        Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text("Iniciar sesión", fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (users.isNotEmpty()) {
            Button(
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .width(300.dp),
                onClick = { showUserDialog = true }) {
                Text("Cuentas guardadas", fontSize = 15.sp)
            }
        }

        Spacer(modifier = Modifier.height(70.dp))
    }
}

fun esCorreoValido(email: String): Boolean {
    //esta funcion nos devuelve verdadeo o falso si el correo ingresado tiene el formato de un correo convecional
    val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(regex)
}