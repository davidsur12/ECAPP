package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.cloud.LoginScreenViewModel
import com.ecapp.ecapp.utils.DateUser
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar

class SharedViewModel : ViewModel() {
    var fecha by mutableStateOf("")
    var genero by mutableStateOf("")

}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistroUser(navController: NavController){
    //scalfold de  regitro nuevo usuario

        FormularioRegistro(navController)

}

/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun FormularioRegistro(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember {mutableStateOf("") }
    var apellido by remember {mutableStateOf("") }
    var fechaNac by remember {mutableStateOf("") }
    var correo by remember {mutableStateOf("") }
    var password by remember {mutableStateOf("") }
    var direccion by remember {mutableStateOf("") }
    var telefono by remember {mutableStateOf("") }
    var password2 by remember {mutableStateOf("") }
    var genero by remember {mutableStateOf("") }
    var fechaConecion by remember {mutableStateOf("") }
    val genderList = listOf("Masculino", "Femenino", "Otro")
    val scrollState = rememberScrollState()
    var isLoadingDialogVisible by remember { mutableStateOf(false) }
    var isErrorDialogVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") } // Para mostrar mensajes de error

    if (isLoadingDialogVisible) {
        AlertDialog(
            onDismissRequest = {}, // No se puede cerrar tocando fuera
            title = { Text("Registrando...") },
            text = { CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp)) },
            confirmButton = {} // No hay botón para cerrarlo
        )
    }

    if (isErrorDialogVisible) {
        AlertDialog(
            onDismissRequest = { isErrorDialogVisible = false
                isLoadingDialogVisible=false;},
            title = { Text("Error al crear la cuenta. Es probable que este correo ya esté registrado. Inténtalo de nuevo.") },
            text = { Text(errorMessage) },
            confirmButton = {
                TextButton (onClick = { isErrorDialogVisible = false }) {
                    Text("Aceptar")
                }
            }
        )
    }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                colorResource(com.ecapp.ecapp.R.color.morado_fondo))
            .verticalScroll(rememberScrollState(),
            ),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("Registro de Usuarios",
            color = Color.White, // Color del texto
            fontSize = 27.sp,   // Tamaño del texto
            fontWeight = FontWeight.Bold,)
        Spacer(modifier = Modifier.height(25.dp))

        Icon(
            imageVector = Icons.Default.Person, // Icono de usuario
            contentDescription = "Icono de Usuario",
            modifier = Modifier.size(170.dp), // Tamaño del icono
            tint = Color.White // Color del icono

        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },

            modifier =  Modifier.background(Color.White,)
        )






        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier =  Modifier.background(Color.White,),

            )
        DateUser.correo=correo

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { newPassword -> password = newPassword },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Direccion") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Telefono") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        DatePickerTextField()

        Spacer(modifier = Modifier.height(25.dp))
        GenderSelection()
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedButton(
            onClick = {
                //valido que todos los campos no esten vacios
                fechaConecion= DateUser.getFecha()
                if(nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty() || DateUser.fechaNacimiento.isEmpty() || DateUser.genero.isEmpty()
                    || direccion.isEmpty() || telefono.isEmpty() || fechaConecion.isEmpty()){

                    Toast.makeText(context, "Porfavor Ingresa todos los Datos"
                        , Toast.LENGTH_SHORT).show()
                }else{

                    isLoadingDialogVisible = true
                    //DateUser.correo=correo
                    /*
                    Toast.makeText(context, "nombre: $nombre  apellido: $apellido  fecha:${DateUser.fechaNacimiento}  genero: ${DateUser.genero}  " +
                            "correo: ${DateUser.correo}  " , Toast.LENGTH_SHORT).show()
                    */
                    Log.d("info", "nombre: $nombre  apellido: $apellido  fecha:${DateUser.fechaNacimiento}  " +
                            "genero:  ${DateUser.genero} $fechaConecion")

                    try {
              //intento registrar el usuario y guardar los datos en cloud
                        val activity = context as? Activity

                        activity?.let {
                            LoginScreenViewModel().auth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Creando Cuenta", "createUserWithEmail:success")
                                    val user = LoginScreenViewModel().auth.currentUser
                                    DateUser.correo=correo
                                    // Agregar los datos acloud

                                    try{

                                        val user = hashMapOf(
                                            "Nombre" to nombre,
                                            "Apellido" to apellido,
                                            "FechaNac" to DateUser.fechaNacimiento,
                                            "Genero" to DateUser.genero,
                                            "Correo" to DateUser.correo,
                                            "Direccion" to direccion,
                                            "Telefono" to telefono,
                                            "FechaConection" to fechaConecion



                                        )

                                        FirebaseCloudUser().crearUser(correo,user)
                                        DateUser.correo = correo
                                    }catch (ex:Exception){

                                    }
                                    //fin
                                    navController.navigate(AppScreens.screenBienbenida.route)
                                    //updateUI(user)
                                } else {
                                    isErrorDialogVisible = true
                                    isLoadingDialogVisible = false
                                    // If sign in fails, display a message to the user.
                                    Log.w("Creando Cuenta", "No se creo la cuenta", task.exception)
                                /*
                                    Toast.makeText(
                                        context,
                                        "Error al crear la Cuenta Intentalo de Nuevo.",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                    */

                                }
                            }
                        }



                    }catch (ex:Exception){
                        /*
                        Toast.makeText(
                            context,
                            "Error al crear la Cuenta Intentalo de Nuevo.",
                            Toast.LENGTH_SHORT,
                        ).show()
*/
                        isLoadingDialogVisible = false
                        isErrorDialogVisible = true
                        errorMessage = ex.message ?: "Ocurrió un error inesperado"
                    }
                }



                //comprovar y registrar los datos
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(com.ecapp.ecapp.R.color.morado)  ,     // Color de fondo del botón
                contentColor = colorResource(com.ecapp.ecapp.R.color.white)     // Color del texto o contenido
                // .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            ),
            modifier = Modifier.padding(16.dp)
                .width(300.dp)
        ) {
            Text(text = "Registrar", fontSize = 18.sp )
        }
        Spacer(modifier = Modifier.height(150.dp))



    }
}

*/

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioRegistro(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val genderList = listOf("Masculino", "Femenino", "Otro")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(com.ecapp.ecapp.R.color.morado_fondo)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Registro de Usuario",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Color del texto en blanco
            )

            Spacer(modifier = Modifier.height(25.dp))

            // Contenedor blanco para destacar los campos
            Card (
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = nombre.isBlank(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Gray,
                           // textColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(
                        value = apellido,
                        onValueChange = { apellido = it },
                        label = { Text("Apellido") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = apellido.isBlank(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Gray,
                            //textColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Campo para seleccionar la fecha de nacimiento
                    DatePickerTextField()

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(text = "Género:", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

                    genderList.forEach { genderOption ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { genero = genderOption }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = (genero == genderOption),
                                onClick = { genero = genderOption }
                            )
                            Text(text = genderOption, fontSize = 16.sp, color = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = Color.Red,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }

                    Button(
                        onClick = {
                            if (nombre.isBlank() || apellido.isBlank() || DateUser.fechaNacimiento.isBlank() || genero.isBlank()) {

                               print("result nombre $nombre")
                                print("result  apellido $apellido")
                                print("result  fechaNacimineto $fechaNacimiento")
                                print("result  genero $genero")


                                errorMessage = "Todos los campos son obligatorios"
                            } else {
                                errorMessage = null
                                println("Nombre: $nombre, Apellido: $apellido, Fecha de Nacimiento: ${DateUser.fechaNacimiento}, Género: $genero")
                                navController.navigate(AppScreens.screenUser.route)

                                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                // Guardar datos en Firebase
                                LoginScreenViewModel().registrarUsuarioEnFirebase(nombre, apellido, genero, DateUser.fechaNacimiento, context)
                                val viewModel: MiViewModel = MiViewModel()
                                viewModel.crearJuegos()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(text = "Registrar", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioRegistro(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val genderList = listOf("Masculino", "Femenino", "Otro")

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            fechaNacimiento = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo))
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(colorResource(com.ecapp.ecapp.R.color.morado_fondo)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Registro de Usuario",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                isError = nombre.isBlank()
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth(),
                isError = apellido.isBlank()
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Campo para seleccionar la fecha de nacimiento
            DatePickerTextField()

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "Género:", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            genderList.forEach { genderOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { genero = genderOption }
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (genero == genderOption),
                        onClick = { genero = genderOption }
                    )
                    Text(text = genderOption, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            Button(
                onClick = {
                    if (nombre.isBlank() || apellido.isBlank() || DateUser.fechaNacimiento.isBlank() || genero.isBlank()) {
                        errorMessage = "Todos los campos son obligatorios"
                    } else {
                        errorMessage = null
                        println("Nombre: $nombre, Apellido: $apellido, Fecha de Nacimiento: ${DateUser.fechaNacimiento}, Género: $genero")
                        navController.navigate(AppScreens.screenUser.route)

                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        // Guardar datos en Firebase
                        LoginScreenViewModel().registrarUsuarioEnFirebase(nombre, apellido, genero, DateUser.fechaNacimiento, context)
                        val viewModel: MiViewModel = MiViewModel()
                        viewModel.crearJuegos()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Registrar", fontSize = 18.sp)
            }
        }
    }
}

*/

/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormularioRegistro(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val genderList = listOf("Masculino", "Femenino", "Otro")

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            fechaNacimiento = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE7F6)) // Fondo lila claro
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Registro de Usuario",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = nombre.isBlank()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth(),
            isError = apellido.isBlank()
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Campo para seleccionar la fecha de nacimiento
        DatePickerTextField()

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Género:", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        genderList.forEach { genderOption ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { genero = genderOption }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (genero == genderOption),
                    onClick = { genero = genderOption }
                )
                Text(text = genderOption, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

        Button(
            onClick = {
                if (nombre.isBlank() || apellido.isBlank() || DateUser.fechaNacimiento.isBlank() || genero.isBlank()) {
                    errorMessage = "Todos los campos son obligatorios"
                } else {
                    errorMessage = null
                    println("Nombre: $nombre, Apellido: $apellido, Fecha de Nacimiento: ${DateUser.fechaNacimiento}, Género: $genero")
                    navController.navigate(AppScreens.screenUser.route);

                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    // Guardar datos en Firebase
                    LoginScreenViewModel().registrarUsuarioEnFirebase(nombre, apellido, genero, DateUser.fechaNacimiento , context)
                    var viewModel: MiViewModel = MiViewModel()
                    viewModel.crearJuegos()


                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Registrar", fontSize = 18.sp)
        }
    }
}
*/

/*
@Composable
fun FormularioRegistro(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val genderList = listOf("Masculino", "Femenino", "Otro")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE7F6)) // Fondo lila claro
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Registro de Usuario",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            isError = nombre.isBlank()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth(),
            isError = apellido.isBlank()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Género:", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        genderList.forEach { genderOption ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { genero = genderOption }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (genero == genderOption),
                    onClick = { genero = genderOption }
                )
                Text(text = genderOption, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

        Button(
            onClick = {
                if (nombre.isBlank() || apellido.isBlank() || genero.isBlank()) {
                    errorMessage = "Todos los campos son obligatorios"
                } else {
                    errorMessage = null
                    println("Nombre: $nombre apellido: $apellido genero: $genero")
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí puedes agregar la lógica para guardar los datos
                    LoginScreenViewModel().registrarUsuarioEnFirebase(nombre, apellido, genero, context)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Registrar", fontSize = 18.sp)
        }
    }
}
*/

/*
@Composable
private fun FormularioRegistro(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember {mutableStateOf("") }
    var apellido by remember {mutableStateOf("") }
    var fechaNac by remember {mutableStateOf("") }
    var correo by remember {mutableStateOf("") }
    var password by remember {mutableStateOf("") }
    var direccion by remember {mutableStateOf("") }
    var telefono by remember {mutableStateOf("") }
    var password2 by remember {mutableStateOf("") }
    var genero by remember {mutableStateOf("") }
    val genderList = listOf("Masculino", "Femenino", "Otro")
    val scrollState = rememberScrollState()
    var isButtonEnabled by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                colorResource(com.ecapp.ecapp.R.color.morado_fondo))
            .verticalScroll(rememberScrollState(),
            ),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("Registro de Usuarios",
            color = Color.White, // Color del texto
            fontSize = 27.sp,   // Tamaño del texto
            fontWeight = FontWeight.Bold,)
        Spacer(modifier = Modifier.height(25.dp))

        Icon(
            imageVector = Icons.Default.Person, // Icono de usuario
            contentDescription = "Icono de Usuario",
            modifier = Modifier.size(170.dp), // Tamaño del icono
            tint = Color.White // Color del icono

        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },

            modifier =  Modifier.background(Color.White,)
        )






        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier =  Modifier.background(Color.White,),

            )
        DateUser.correo=correo

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { newPassword -> password = newPassword },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Direccion") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Telefono") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        DatePickerTextField()

        Spacer(modifier = Modifier.height(25.dp))
        GenderSelection()

     //   Spacer(modifier = Modifier.height(25.dp))


        OutlinedButton(
            onClick = {
                if (isButtonEnabled) {
                    isButtonEnabled = false // Deshabilita el botón al iniciar el proceso
                    if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty() ||
                        DateUser.fechaNacimiento.isEmpty() || DateUser.genero.isEmpty() || direccion.isEmpty() || telefono.isEmpty()
                    ) {
                        Toast.makeText(context, "Por favor, ingresa todos los datos", Toast.LENGTH_SHORT).show()
                        isButtonEnabled = true // Reactiva el botón si faltan datos
                    } else {
                        showDialog = true // Muestra el AlertDialog

                        try {
                            val activity = context as? Activity
                            activity?.let {
                                LoginScreenViewModel().auth.createUserWithEmailAndPassword(correo, password)
                                    .addOnCompleteListener(activity) { task ->
                                        if (task.isSuccessful) {

                                            showDialog = false
                                            Log.d("Creando Cuenta", "createUserWithEmail:success")
                                            val user = LoginScreenViewModel().auth.currentUser
                                            DateUser.correo = correo
                                            navController.navigate(AppScreens.screenBienbenida.route)
                                        } else {
                                            showDialog = false
                                            Log.w("Creando Cuenta", "No se creó la cuenta", task.exception)
                                            Toast.makeText(
                                                context,
                                                "Error al crear la cuenta. Inténtalo de nuevo.",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            //creando cuenta
                                            Log.d("info", "nombre: $nombre  apellido: $apellido  fecha:${DateUser.fechaNacimiento}  " +
                                                    "genero:  ${DateUser.genero}")

                                            try {
//intento registrar el usuario y guardar los datos en cloud
                                                val activity = context as? Activity

                                                activity?.let {
                                                    LoginScreenViewModel().auth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(activity) { task ->
                                                        if (task.isSuccessful) {
                                                            // Sign in success, update UI with the signed-in user's information
                                                            Log.d("Creando Cuenta", "createUserWithEmail:success")
                                                            val user = LoginScreenViewModel().auth.currentUser
                                                            DateUser.correo=correo
                                                            navController.navigate(AppScreens.screenBienbenida.route)
                                                            //updateUI(user)
                                                        } else {
                                                            // If sign in fails, display a message to the user.
                                                            Log.w("Creando Cuenta", "No se creo la cuenta", task.exception)
                                                            Toast.makeText(
                                                                context,
                                                                "Error al crear la Cuenta Intentalo de Nuevo.",
                                                                Toast.LENGTH_SHORT,
                                                            ).show()

                                                        }
                                                    }
                                                }

                                                // Agregar los datos acloud

                                                try{

                                                    val user = hashMapOf(
                                                        "Nombre" to nombre,
                                                        "Apellido" to apellido,
                                                        "FechaNac" to DateUser.fechaNacimiento,
                                                        "Genero" to DateUser.genero,
                                                        "Correo" to DateUser.correo,
                                                        "Direccion" to direccion,
                                                        "Telefono" to telefono



                                                    )

                                                    FirebaseCloudUser().crearUser(correo,user)
                                                    DateUser.correo = correo
                                                }catch (ex:Exception){

                                                }
                                                //fin

                                            }catch (ex:Exception){
                                                Toast.makeText(
                                                    context,
                                                    "Error al crear la Cuenta Intentalo de Nuevo.",
                                                    Toast.LENGTH_SHORT,
                                                ).show()
                                            }

                                            //fin
                                        }
                                        showDialog = false
                                        isButtonEnabled = true // Reactiva el botón al finalizar
                                    }
                            }
                        } catch (ex: Exception) {
                            Toast.makeText(
                                context,
                                "Error al crear la cuenta. Inténtalo de nuevo.",
                                Toast.LENGTH_SHORT
                            ).show()
                            isButtonEnabled = true // Reactiva el botón en caso de error
                        }
                    }
                }
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .padding(16.dp)
                .width(300.dp),
            enabled = isButtonEnabled // Desactiva el botón según el estado
        ) {
            Text(text = "Registrar", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(70.dp))

        // AlertDialog que muestra el mensaje de "Registrando..."
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { /* No permite cerrar el dialogo al tocar fuera */ },
                title = { Text("Registrando") },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text("Por favor, espera...")
                    }
                },
                confirmButton = {  }
            )
        }




    }
}

*/

/*
@Composable
private fun FormularioRegistro(navController: NavController) {
    val context = LocalContext.current
    var nombre by remember {mutableStateOf("") }
    var apellido by remember {mutableStateOf("") }
    var fechaNac by remember {mutableStateOf("") }
    var correo by remember {mutableStateOf("") }
    var password by remember {mutableStateOf("") }
    var direccion by remember {mutableStateOf("") }
    var telefono by remember {mutableStateOf("") }
    var password2 by remember {mutableStateOf("") }
    var genero by remember {mutableStateOf("") }
    val genderList = listOf("Masculino", "Femenino", "Otro")
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                colorResource(com.ecapp.ecapp.R.color.morado_fondo))
            .verticalScroll(rememberScrollState(),
                ),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("Registro de Usuarios",
            color = Color.White, // Color del texto
            fontSize = 27.sp,   // Tamaño del texto
            fontWeight = FontWeight.Bold,)
        Spacer(modifier = Modifier.height(25.dp))

        Icon(
            imageVector = Icons.Default.Person, // Icono de usuario
            contentDescription = "Icono de Usuario",
            modifier = Modifier.size(170.dp), // Tamaño del icono
            tint = Color.White // Color del icono

        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },

            modifier =  Modifier.background(Color.White,)
        )






        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier =  Modifier.background(Color.White,),

        )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier =  Modifier.background(Color.White,),

            )
        DateUser.correo=correo

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { newPassword -> password = newPassword },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Direccion") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(

            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Telefono") },
            modifier =  Modifier.background(Color.White,),

            )

        Spacer(modifier = Modifier.height(25.dp))
        DatePickerTextField()

        Spacer(modifier = Modifier.height(25.dp))
        GenderSelection()
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedButton(
            onClick = {
                //valido que todos los campos no esten vacios

                if(nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty() || DateUser.fechaNacimiento.isEmpty() || DateUser.genero.isEmpty()
                    || direccion.isEmpty() || telefono.isEmpty()){

                    Toast.makeText(context, "Porfavor Ingresa todos los Datos"
                            , Toast.LENGTH_SHORT).show()
                }else{
                    //DateUser.correo=correo
                    /*
                    Toast.makeText(context, "nombre: $nombre  apellido: $apellido  fecha:${DateUser.fechaNacimiento}  genero: ${DateUser.genero}  " +
                            "correo: ${DateUser.correo}  " , Toast.LENGTH_SHORT).show()
                    */
                    Log.d("info", "nombre: $nombre  apellido: $apellido  fecha:${DateUser.fechaNacimiento}  " +
                            "genero:  ${DateUser.genero}")

                    try {
//intento registrar el usuario y guardar los datos en cloud
                        val activity = context as? Activity

                        activity?.let {
                            LoginScreenViewModel().auth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Creando Cuenta", "createUserWithEmail:success")
                                    val user = LoginScreenViewModel().auth.currentUser
                                    DateUser.correo=correo
                                    navController.navigate(AppScreens.screenBienbenida.route)
                                    //updateUI(user)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Creando Cuenta", "No se creo la cuenta", task.exception)
                                    Toast.makeText(
                                        context,
                                        "Error al crear la Cuenta Intentalo de Nuevo.",
                                        Toast.LENGTH_SHORT,
                                    ).show()

                                }
                            }
                        }

                        // Agregar los datos acloud

                       try{

                           val user = hashMapOf(
                               "Nombre" to nombre,
                               "Apellido" to apellido,
                               "FechaNac" to DateUser.fechaNacimiento,
                               "Genero" to DateUser.genero,
                               "Correo" to DateUser.correo,
                               "Direccion" to direccion,
                               "Telefono" to telefono



                           )

                           FirebaseCloudUser().crearUser(correo,user)
                           DateUser.correo = correo
                       }catch (ex:Exception){

                       }
                        //fin

                    }catch (ex:Exception){
                        Toast.makeText(
                            context,
                            "Error al crear la Cuenta Intentalo de Nuevo.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }



                //comprovar y registrar los datos
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White // Cambiar el fondo a blanco
            ),
            modifier = Modifier.padding(16.dp)
                .width(300.dp)
        ) {
            Text(text = "Registrar", fontSize = 18.sp )
        }




    }
}
*/

@Composable
fun DatePickerTextField() {

    // Contexto para obtener acceso a recursos del sistema
    var fecha by remember { mutableStateOf("Hola desde Parent!") }
    val context = LocalContext.current

    // Estado para la fecha seleccionada
    var selectedDate by remember { mutableStateOf("") }

    // Variables para la fecha actual
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Crear el DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear" // Actualizar la fecha seleccionada
        }, year, month, day
    )

    // Interfaz del TextField con el DatePicker
    Column {
        TextField(
            value = selectedDate,
            onValueChange = {
                fecha= selectedDate.toString()
            },
            label = { Text(text = "Fecha de Nacimiento") },
            modifier = Modifier
                .width(280.dp)
                .clickable { datePickerDialog.show() }, // Mostrar el DatePicker al hacer clic en el TextField
            enabled = false // Deshabilitar edición manual
        )
    }

    DateUser.fechaNacimiento=selectedDate.toString()
}

@Composable
fun GenderSelection() {
    // Estados para controlar los checkbox
    var isMaleChecked by remember { mutableStateOf(false) }
    var isFemaleChecked by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Opción Masculino
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Masculino", color = Color.White, fontSize = 18.sp)
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue, // Color cuando está marcado
                    uncheckedColor = Color.Black, // Color cuando está desmarcado
                    checkmarkColor = Color.White // Color de la marca de verificación
                ),
                checked = isMaleChecked,
                onCheckedChange = { isChecked ->
                    isMaleChecked = isChecked
                    if (isChecked) {
                        isFemaleChecked = false // Desmarcar Femenino si se marca Masculino
                    }
                }
            )
        }

        // Opción Femenino
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Femenino", color = Color.White, fontSize = 18.sp)
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue, // Color cuando está marcado
                    uncheckedColor = Color.Black, // Color cuando está desmarcado
                    checkmarkColor = Color.White // Color de la marca de verificación
                ),
                checked = isFemaleChecked,
                onCheckedChange = { isChecked ->
                    isFemaleChecked = isChecked
                    if (isChecked) {
                        isMaleChecked = false // Desmarcar Masculino si se marca Femenino
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la opción seleccionada
        val selectedGender = when {
            isMaleChecked -> "Masculino"
            isFemaleChecked -> "Femenino"
            else -> "Ninguno"
        }

        //Text(text = "Género seleccionado: $selectedGender")
        DateUser.genero=selectedGender
    }
}

class MiViewModel : ViewModel() {
    fun crearJuegos() {
        viewModelScope.launch {
            FirebaseCloudUser().crearDocumentosGames()
        }
    }
}



