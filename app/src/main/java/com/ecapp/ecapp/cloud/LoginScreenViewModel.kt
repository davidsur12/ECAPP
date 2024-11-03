package com.ecapp.ecapp.cloud

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

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


}

