package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
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
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.screen.login.LoginScreenViewModel
import com.ecapp.ecapp.utils.DateUser
import java.util.Calendar

class SharedViewModel : ViewModel() {
    var fecha by mutableStateOf("")
    var genero by mutableStateOf("")

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistroUser(navController: NavController){
    //scalfold de  regitro nuevo usuario
    Scaffold {

        FormularioRegistro(navController)
    }
}

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

                    Toast.makeText(context, "nombre: $nombre  apellido: $apellido  fecha:${DateUser.fechaNacimiento}  genero: ${DateUser.genero}  " +
                            "correo: ${DateUser.correo}  " , Toast.LENGTH_SHORT).show()
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
            Text(text = "Registrar",  )
        }




    }
}


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
    Column() {
        TextField(
            value = selectedDate,
            onValueChange = {
                fecha= selectedDate.toString();
            },
            label = { Text(text = "Selecciona una fecha") },
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
            Text(text = "Masculino", color = Color.White)
            Checkbox(
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
            Text(text = "Femenino", color = Color.White)
            Checkbox(

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
        DateUser.genero=selectedGender;
    }
}