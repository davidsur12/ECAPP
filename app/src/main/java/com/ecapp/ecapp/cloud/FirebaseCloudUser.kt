package com.ecapp.ecapp.cloud

import android.util.Log
import com.ecapp.ecapp.utils.DateUser
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



    fun crearDocumentosGames() {
        val db = FirebaseFirestore.getInstance()

        // ID del documento principal existente
        val idDocumentoPrincipal = DateUser.correo

        // Lista de identificadores de subdocumentos que deseas crear
        val identificadores = listOf("cancelación_objetos", "secuencia", "rompecabezas", "sopa_letras", "laberinto")

        for (id in identificadores) {
            // Referencia a la subcolección dentro del documento principal
            val docRef = db.collection("users")
                .document(idDocumentoPrincipal)
                .collection("Juegos")
                .document(id)

            // Verificar si el subdocumento ya existe
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (!documentSnapshot.exists()) {
                    // Crear el subdocumento solo si no existe
                    val datos = hashMapOf(
                        "fecha" to id,
                        "Calificacion" to "Descripción del juego $id" // Agrega más campos según sea necesario
                    )

                    docRef.set(datos)
                        .addOnSuccessListener {
                            Log.d("info","Subdocumento '$id' creado exitosamente en '$idDocumentoPrincipal/tiposDeJuegos'.")
                        }
                        .addOnFailureListener { e ->
                            Log.d("info","Error al crear el subdocumento '$id': ${e.message}")
                        }
                } else {
                    Log.d("info","El subdocumento '$id' ya existe, no se crea de nuevo.")
                }
            }.addOnFailureListener { e ->
                Log.d("info","Error al verificar el subdocumento '$id': ${e.message}")
            }
        }
    }

    fun agregarCalificacion( fecha: String, calificacion: Int, juego: String) {
        val db = FirebaseFirestore.getInstance()

        // Crear el mapa con fecha y calificación
        val evaluacion = hashMapOf(
            "fecha" to fecha,
            "calificacion" to calificacion
        )
        val docRef = db.collection("users")
            .document(DateUser.correo)
            .collection("Juegos")
            .document(juego)
        // Referencia al documento donde se quieren agregar las evaluaciones
        // val docRef = db.collection("users").document(idDocumento)

        // Agregar la evaluación al array usando arrayUnion
        docRef.update(
            "evaluaciones", com.google.firebase.firestore.FieldValue.arrayUnion(evaluacion)
        ).addOnSuccessListener {
            ("Evaluación agregada correctamente.")
        }.addOnFailureListener { e ->
            println("Error al agregar evaluación: ${e.message}")
        }
    }

}





