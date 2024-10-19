package com.ecapp.ecapp.screen.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.widget.Toast
import com.ecapp.ecapp.screen.home.Home
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser

class LoginScreenViewModel: ViewModel() {
      val auth: FirebaseAuth = Firebase.auth;
   // private val _loading: MutableLiveData();

    fun signInWithEmailAndPassword(email: String , pasword:String, home:()->Unit,  context: Context)= viewModelScope.launch {
email.toString().trim()
        pasword.toString().trim()//conbierte todo a cadena y quita los espacios en blanco
        try {
auth.signInWithEmailAndPassword(email , pasword).addOnCompleteListener{
    task-> if(task.isSuccessful){
        Log.d("login exitoso", "Usuario logeado");
        home();
    }else {
    try {
        throw task.exception!!
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        Toast.makeText(context, "Credenciales incorrectas o mal formadas", Toast.LENGTH_SHORT).show()
        Log.e("Error", "Credenciales incorrectas o mal formadas")
    } catch (e: FirebaseAuthInvalidUserException) {
        Toast.makeText(context, "Usuario no encontrado o deshabilitado", Toast.LENGTH_SHORT).show()
        Log.e("Error", "Usuario no encontrado o deshabilitado")
    } catch (e: Exception) {
        Toast.makeText(context, "Error al Iniciar Session Revisa el Correo y tu Contraseña", Toast.LENGTH_SHORT).show()
        Log.e("Error", e.message.toString())
    }
}
    /*
    else{
    Log.d("login no logeado", "Usuario no logeado ${task.result.toString()}")
    }*/
}

        }catch (ex : Exception){
Log.d("login error", "${ex.message}")

        }
    }
    fun outSession(){
       Firebase.auth.signOut();
   }



}

