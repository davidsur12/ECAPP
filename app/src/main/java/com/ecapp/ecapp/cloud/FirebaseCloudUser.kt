package com.ecapp.ecapp.cloud

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.ecapp.ecapp.utils.DateUser
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
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

    suspend fun getDataUser(userId: String): HashMap<String, Any>? {
        //obtine los datos con los que se registro el usuario
        //se usa la constate users ya que este es el nombre de la collection
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


    fun crearDocumentosGames2() {
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
                    // Crear el subdocumento vacío solo si no existe
                    docRef.set(hashMapOf<String, Any>()) // Enviar un HashMap vacío
                        .addOnSuccessListener {
                            Log.d("info", "Subdocumento '$id' creado exitosamente en '$idDocumentoPrincipal/Juegos'.")
                        }
                        .addOnFailureListener { e ->
                            Log.d("info", "Error al crear el subdocumento '$id': ${e.message}")
                        }
                } else {
                    Log.d("info", "El subdocumento '$id' ya existe, no se crea de nuevo.")
                }
            }.addOnFailureListener { e ->
                Log.d("info", "Error al verificar el subdocumento '$id': ${e.message}")
            }
        }
    }

    suspend fun crearDocumentosGames() {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null) {
            Log.d("info", "❌ No hay usuario autenticado.")
            return
        }

        val db = FirebaseFirestore.getInstance()
        val userUid = user.uid

        val identificadores = listOf("cancelación_objetos", "secuencia", "rompecabezas", "sopa_letras", "laberinto")

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
                        Log.d("info", "✅ Subdocumento '$id' creado exitosamente en '$userUid/Juegos'.")
                    } else {
                        Log.d("info", "⚠️ El subdocumento '$id' ya existe, no se crea de nuevo.")
                    }
                } catch (e: Exception) {
                    Log.e("info", "❌ Error al procesar el subdocumento '$id': ${e.message}")
                }
            }
        }
    }

/*
    fun crearDocumentosGames() {
        val db = FirebaseFirestore.getInstance()
        val evaluacion = hashMapOf(
            "fecha" to "2024-11-04T02:09:52.534238",
            "calificacion" to 5
        )
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
                 /*
                    val datos = hashMapOf(
                        "fecha" to id,
                        "Calificacion" to "Descripción del juego $id" // Agrega más campos según sea necesario
                    )
                    */

                    docRef.set(evaluacion)
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
*/
    fun agregarCalificacion1( fecha: String, calificacion: Int, juego: String) {
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
    /*
    fun agregarCalificacion(fecha: String, calificacion: Int, juego: String) {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null) {
            println("No hay usuario autenticado.")
            return
        }

        val db = FirebaseFirestore.getInstance()

        // Crear el mapa con fecha y calificación
        val evaluacion = hashMapOf(
            "fecha" to fecha,
            "calificacion" to calificacion
        )

        val docRef = db.collection("usuarios")
            .document(user.uid)
            .collection("Juegos")
            .document(juego)

        // Verificar si el documento del juego existe antes de actualizarlo
        docRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                // Si el documento existe, actualizamos el campo "evaluaciones"
                docRef.update("evaluaciones", FieldValue.arrayUnion(evaluacion))
                    .addOnSuccessListener {
                        println("✅ Evaluación agregada correctamente.")
                    }
                    .addOnFailureListener { e ->
                        println("❌ Error al agregar evaluación: ${e.message}")
                    }
            } else {
                // Si no existe, creamos un nuevo documento con el array
                val nuevoJuego = hashMapOf(
                    "evaluaciones" to listOf(evaluacion)
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

*/

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
                val evaluaciones = document.get("evaluaciones") as? List<HashMap<String, Any>> ?: emptyList()

                // Verificar si ya existe una evaluación con la misma fecha y calificación
                val yaExiste = evaluaciones.any { it["fecha"] == fecha && it["calificacion"] == calificacion }

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


    @RequiresApi(Build.VERSION_CODES.O)
    fun getDatosGameDia(juego:String){

        val db = FirebaseFirestore.getInstance()

// Referencia a la colección 'juegos' dentro del documento del usuario 'franco@gmail.com'
        val juegosCollectionRef = db.collection("users").document("franco@gmail.com").collection("juegos")
        juegosCollectionRef.get().addOnSuccessListener { querySnapshot ->
            querySnapshot.documents.forEach { document ->
                // Obtén el arreglo 'evaluaciones' de cada documento
                val evaluaciones = document.get("evaluaciones") as? List<Map<String, Any>> ?: emptyList()

                // Recorrer cada evaluación en el arreglo y mostrar sus valores
                evaluaciones.forEach { evaluacion ->
                    val fecha = evaluacion["fecha"] as? String
                    val calificacion = evaluacion["calificacion"] as? Long
println(fecha)
                    // Mostrar los valores obtenidos
                    Log.d("infofire", "Fecha: $fecha, Calificación: $calificacion")
                }
            }
        }.addOnFailureListener { exception ->
            Log.e("infofire", "Error obteniendo evaluaciones: ", exception)
        }

        fun getRompecabezasData() {
            // Obtén la instancia de Firebase Firestore
            val db = FirebaseFirestore.getInstance()

            // Ruta al documento "rompecabezas"
            val docRef = db.collection("users")
                .document("franco@gmail.com")
                .collection("juegos")
                .document("rompecabezas")

            // Obtener los datos del documento
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        // El documento se ha obtenido correctamente
                        val data = document.data
                        // Aquí puedes trabajar con los datos obtenidos
                        println("Datos de 'rompecabezas': $data")
                    } else {
                        println("El documento no existe.")
                    }
                }
                .addOnFailureListener { exception ->
                    // Error al obtener el documento
                    println("Error al obtener el documento: $exception")
                }
        }


        /*
                val db = FirebaseFirestore.getInstance()

        // Acceder a la colección 'users' y luego al documento con el correo 'franco@gmail.com'
        // Dentro de este documento, acceder a la colección 'juegos'
                val userDocRef = db.collection("users").document("franco@gmail.com").collection("juegos")
        // Obtener la fecha actual en el mismo formato que en Firebase
                val currentDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

                // Filtrar las evaluaciones por la fecha actual
                userDocRef.get().addOnSuccessListener { querySnapshot ->
                    querySnapshot.documents.forEach { document ->
                        // Accedemos al campo 'evaluaciones' dentro de cada documento de la colección 'juegos'
                        val evaluaciones = document.get("evaluaciones") as? List<Map<String, Any>> ?: emptyList()

                        // Filtrar las evaluaciones por la fecha actual
                        val evaluacionesHoy = evaluaciones.filter { evaluacion ->
                            evaluacion["fecha"] == currentDate // Compara con la fecha actual
                        }

                        // Mostrar las evaluaciones obtenidas
                        evaluacionesHoy.forEach {
                            val fecha = it["fecha"] as String
                            val calificacion = it["calificacion"] as Long
                            Log.d("Firestore", "Fecha: $fecha, Calificación: $calificacion")
                        }
                    }
                }

        */
    }


    fun getEvaluaciones2(callback: (List<Map<String, Any>>?) -> Unit, juego: String) {
        // Obtén la instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Ruta al documento "rompecabezas"
        val docRef = db.collection("users")
            .document(DateUser.correo)
            .collection("Juegos")
            .document(juego)

        // Obtener los datos del documento
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // El documento se ha obtenido correctamente
                    val evaluaciones = document.get("evaluaciones") as? List<Map<String, Any>>
                    // Devolver el array "evaluaciones" a través del callback
                    callback(evaluaciones)
                } else {
                    println("El documento no existe.")
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                // Error al obtener el documento
                println("Error al obtener el documento: $exception")
                callback(null)
            }
    }

    fun getEvaluaciones( callback: (List<Map<String, Any>>) -> Unit, juego: String,) {
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
                    val evaluaciones = document.get("evaluaciones") as? List<Map<String, Any>> ?: emptyList()
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


    @RequiresApi(Build.VERSION_CODES.O)
    fun updateFechaConecion(correoUsuario: String  ){


        val db = FirebaseFirestore.getInstance()

        val userDocRef = db.collection("users").document(correoUsuario)
        userDocRef.update("FechaConection",DateUser.getFecha().toString())
            .addOnSuccessListener {
                println("FechaConection actualizada correctamente.")
            }
            .addOnFailureListener { e ->
                println("Error al actualizar FechaConection: ${e.message}")
            }

    }

}





