package ie.setu.wishfulgames.firebase.services

import android.net.Uri
import ie.setu.wishfulgames.data.model.GameModel
import kotlinx.coroutines.flow.Flow

typealias Game = GameModel
typealias Games = Flow<List<Game>>

interface FirestoreService {

    suspend fun getAll(email: String) : Games
    suspend fun get(email: String, gameId: String) : Game?
    suspend fun insert(email: String, game: Game)
    suspend fun update(email: String, game: Game)
    suspend fun delete(email: String, gameId: String)
    // suspend fun updatePhotoUris(email: String, uri: Uri)
}
