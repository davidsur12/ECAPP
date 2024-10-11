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
import com.ecapp.ecapp.screen.home.Home

class LoginScreenViewModel: ViewModel() {
      val auth: FirebaseAuth = Firebase.auth;
   // private val _loading: MutableLiveData();

    fun signInWithEmailAndPassword(email: String , pasword:String, home:()->Unit)= viewModelScope.launch {

        try {
auth.signInWithEmailAndPassword(email , pasword).addOnCompleteListener{
    task-> if(task.isSuccessful){
        Log.d("login", "Usuario logeado");
        home();
    }
    else{
    Log.d("login", "Usuario no logeado ${task.result.toString()}")
    }
}

        }catch (ex : Exception){
Log.d("logeo", "${ex.message}")

        }
    }

   fun outSession(){
       Firebase.auth.signOut();
   }
}