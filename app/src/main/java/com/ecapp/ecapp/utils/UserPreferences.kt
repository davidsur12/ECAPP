import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private val USERS_KEY = stringPreferencesKey("users")
    }

    // Obtener usuarios almacenados
    val users: Flow<Map<String, String>> = dataStore.data.map { preferences ->
        val usersString = preferences[USERS_KEY] ?: "{}"
        jsonToMap(usersString)
    }

    // Guardar usuario en DataStore
    suspend fun saveUser(email: String, password: String) {
        dataStore.edit { preferences ->
            val currentUsers = jsonToMap(preferences[USERS_KEY] ?: "{}").toMutableMap()
            currentUsers[email] = password
            preferences[USERS_KEY] = mapToJson(currentUsers)
        }
    }

    // Eliminar usuario de DataStore
    suspend fun deleteUser(email: String) {
        dataStore.edit { preferences ->
            val currentUsers = jsonToMap(preferences[USERS_KEY] ?: "{}").toMutableMap()
            currentUsers.remove(email) // Elimina el usuario de la lista
            preferences[USERS_KEY] = mapToJson(currentUsers)
        }
    }

    // Convertir JSON a Map
    private fun jsonToMap(json: String): Map<String, String> {
        return try {
            json.split(";").associate {
                val (key, value) = it.split("=")
                key to value
            }
        } catch (e: Exception) {
            emptyMap()
        }
    }

    // Convertir Map a JSON
    private fun mapToJson(map: Map<String, String>): String {
        return map.entries.joinToString(";") { "${it.key}=${it.value}" }
    }
}
