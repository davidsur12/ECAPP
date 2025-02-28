package com.ecapp.ecapp.cloud

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale


class LoginScreenViewModel : ViewModel() {
    val auth: FirebaseAuth = Firebase.auth;
    // private val _loading: MutableLiveData();

    fun signInWithEmailAndPassword(
        email: String,
        pasword: String,
        home: () -> Unit,
        context: Context
    ) = viewModelScope.launch {
        email.toString().trim()
        pasword.toString().trim()//conbierte todo a cadena y quita los espacios en blanco
        try {
            auth.signInWithEmailAndPassword(email, pasword).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //si las credenciales son correctas el usuario se logea y se dirigue  a la pantalla de home

                    Log.d("login exitoso", "Usuario logeado");
                    home();
                } else {
                    try {
                        throw task.exception!!
                    }
                    catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            context,
                            "Credenciales incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("Error", "Por favor Revisa la Informacion Ingresada")
                    }
                    catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(
                            context,
                            "Usuario no Encontrado o Deshabilitado",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("Error", "Usuario no encontrado o deshabilitado")
                    }
                    catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Error al Iniciar Session Revisa el Correo y tu Contraseña",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("Error", e.message.toString())
                    }
                }

            }

        }
        catch (ex: Exception) {
            Log.d("login error", "${ex.message}")

        }
    }

    fun outSession() {
        Firebase.auth.signOut();
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun registrarUsuarioEnFirebase(nombre: String, apellido: String, genero: String, fechaNac: String, context: Context) {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        val currentUser = auth.currentUser

        if (currentUser != null) {

            guardarDatosEnFirestore(currentUser.uid, nombre, apellido, fechaNac, genero)
        } else {
            auth.signInAnonymously()
                .addOnSuccessListener { authResult ->
                    val uid = authResult.user?.uid
                    if (uid != null) {
                        guardarDatosEnFirestore(uid, nombre, apellido, fechaNac, genero)
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(context, "Error al registrar: ${exception.message}", Toast.LENGTH_LONG).show()
                }
        }
    }


    // Función para guardar los datos en Firestore
    @RequiresApi(Build.VERSION_CODES.O)
    private fun guardarDatosEnFirestore(uid: String, nombre: String, apellido: String, fechaNac: String, genero: String) {
        val db = FirebaseFirestore.getInstance()
        val fechaConecion = LocalDate.now();
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechaCon = sdf.format(Date())

        val usuario = hashMapOf(
            "uid" to uid,
            "nombre" to nombre,
            "apellido" to apellido,
            "fechaNc" to fechaNac,
            "genero" to genero,
            "ultimaconecion" to fechaCon
        )

        db.collection("usuarios").document(uid)
            .set(usuario)
            .addOnSuccessListener {
                println("Usuario registrado correctamente en Firestore.")
            }
            .addOnFailureListener { exception ->
                println("Error al registrar usuario: ${exception.message}")
            }
    }


}

