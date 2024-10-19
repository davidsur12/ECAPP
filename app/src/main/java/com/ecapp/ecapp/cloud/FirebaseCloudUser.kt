package com.ecapp.ecapp.cloud

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirebaseCloudUser {

    val db = Firebase.firestore

    fun crearUser(documento: String, user: HashMap<String,String> ){
        // Inicializa Firestore
        val db = FirebaseFirestore.getInstance()




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

    fun getdatos(){

        // Obtén una instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Accede a la colección "usuarios"
        db.collection("users")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // Muestra los datos del documento
                    Log.d("Firestoree", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error al obtener documentos: ", exception)
            }
    }

    suspend fun getUserById(userId: String): HashMap<String, Any>? {
        val db = FirebaseFirestore.getInstance()

        return try {
            val document = db.collection("users").document(userId).get().await()
            if (document.exists()) {
                document.data as HashMap<String, Any>
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}

