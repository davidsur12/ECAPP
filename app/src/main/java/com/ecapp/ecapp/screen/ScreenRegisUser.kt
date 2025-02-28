package com.ecapp.ecapp.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ecapp.ecapp.cloud.FirebaseCloudUser
import com.ecapp.ecapp.navegation.AppScreens
import com.ecapp.ecapp.cloud.LoginScreenViewModel
import com.ecapp.ecapp.utils.DateUser
import kotlinx.coroutines.launch
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistroUser(navController: NavController){
    //scalfold de  regitro nuevo usuario

        FormularioRegistro(navController)

}



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
                                navController.navigate(AppScreens.screenBienbenida.route)

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



class MiViewModel : ViewModel() {
    fun crearJuegos() {
        //vamos a la siguiente pantalla
        viewModelScope.launch {
            FirebaseCloudUser().crearDocumentosGames()
        }
    }
}



