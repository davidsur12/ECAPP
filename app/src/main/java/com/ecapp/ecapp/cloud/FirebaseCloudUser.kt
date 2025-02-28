package com.ecapp.ecapp.cloud

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseCloudUser {


    suspend fun getDataUser2(): HashMap<String, Any>? {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null) {
            println("No hay usuario autenticado.")
            return null
        }

        val db = FirebaseFirestore.getInstance()

        return try {
            val document = db.collection("usuarios").document(user.uid).get()
                .await() // ✅ Usamos el UID correcto
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

    suspend fun crearDocumentosGames() {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null) {
            Log.d("info", " No hay usuario autenticado.")
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userUid = user.uid

        val identificadores =
            listOf("cancelación_objetos", "secuencia", "rompecabezas", "sopa_letras", "laberinto")

        withContext(Dispatchers.IO) {
            for (id in identificadores) {
                try {
                    val docRef = db.collection("usuarios")
                        .document(userUid)
                        .collection("Juegos")
                        .document(id)

                    val documentSnapshot = docRef.get().await()
                    if (!documentSnapshot.exists()) {
                        docRef.set(hashMapOf<String, Any>()).await()
                        Log.d(
                            "info",
                            " Subdocumento '$id' creado exitosamente en '$userUid/Juegos'."
                        )
                    } else {
                        Log.d("info", "️ El subdocumento '$id' ya existe, no se crea de nuevo.")
                    }
                } catch (e: Exception) {
                    Log.e("info", " Error al procesar el subdocumento '$id': ${e.message}")
                }
            }
        }
    }


    fun agregarCalificacion(fecha: String, calificacion: Int, juego: String) {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null) {
            println("No hay usuario autenticado.")
            return
        }

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("usuarios")
            .document(user.uid)
            .collection("Juegos")
            .document(juego)

        val nuevaEvaluacion = hashMapOf(
            "fecha" to fecha,
            "calificacion" to calificacion
        )

        docRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val evaluaciones =
                    document.get("evaluaciones") as? List<HashMap<String, Any>> ?: emptyList()

                // Verificar si ya existe una evaluación con la misma fecha y calificación
                val yaExiste =
                    evaluaciones.any { it["fecha"] == fecha && it["calificacion"] == calificacion }

                if (!yaExiste) {
                    docRef.update("evaluaciones", FieldValue.arrayUnion(nuevaEvaluacion))
                        .addOnSuccessListener {
                            println("✅ Evaluación agregada correctamente.")
                        }
                        .addOnFailureListener { e ->
                            println("❌ Error al agregar evaluación: ${e.message}")
                        }
                } else {
                    println("⚠️ La evaluación ya existe, no se agrega nuevamente.")
                }
            } else {
                val nuevoJuego = hashMapOf(
                    "evaluaciones" to listOf(nuevaEvaluacion)
                )

                docRef.set(nuevoJuego)
                    .addOnSuccessListener {
                        println("✅ Nuevo juego creado y evaluación agregada correctamente.")
                    }
                    .addOnFailureListener { e ->
                        println("❌ Error al crear el documento del juego: ${e.message}")
                    }
            }
        }.addOnFailureListener { e ->
            println("❌ Error al verificar la existencia del juego: ${e.message}")
        }
    }


    fun getEvaluaciones(callback: (List<Map<String, Any>>) -> Unit, juego: String) {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null) {
            Log.e("Firebase", "Usuario no autenticado")
            callback(emptyList()) // Retorna una lista vacía en lugar de null
            return
        }

        // Obtiene la instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Ruta al documento del juego dentro del usuario
        val docRef = db.collection("usuarios")
            .document(user.uid)
            .collection("Juegos")
            .document(juego)

        // Obtener los datos del documento
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val evaluaciones =
                        document.get("evaluaciones") as? List<Map<String, Any>> ?: emptyList()
                    callback(evaluaciones)
                } else {
                    Log.w("Firebase", "El documento no existe.")
                    callback(emptyList())
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error al obtener el documento", exception)
                callback(emptyList())
            }
    }


}





