package com.ecapp.ecapp.cloud

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FirebaseCloudUser {

    val db = Firebase.firestore

    fun crearUser(documento: String, user: HashMap<String,String> ){
        // Inicializa Firestore
        val db = FirebaseFirestore.getInstance()

        // Crea un objeto de datos (un mapa en este caso)
        val userr = hashMapOf(
            "firstName" to "John",
            "lastName" to "Doe",
            "age" to 30
        )

        // Añadir un nuevo documento a la colección "users"
        // Añadir o sobrescribir un documento con ID específico
        db.collection("users").document(documento).set(user)
            .addOnSuccessListener {
                println("Documento sobrescrito exitosamente!")
            }
            .addOnFailureListener { e ->
                println("Error al sobrescribir el documento: $e")
            }
    }

}