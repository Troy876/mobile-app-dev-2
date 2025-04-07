package ie.setu.wishfulgames.firebase.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import ie.setu.wishfulgames.data.rules.Constants.GAME_COLLECTION
import ie.setu.wishfulgames.data.rules.Constants.USER_EMAIL
import ie.setu.wishfulgames.firebase.services.AuthService
import ie.setu.wishfulgames.firebase.services.FirestoreService
import ie.setu.wishfulgames.firebase.services.Game
import ie.setu.wishfulgames.firebase.services.Games
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore
) : FirestoreService {

    override suspend fun getAll(email: String): Games {

        return firestore.collection(GAME_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .dataObjects()
    }

    override suspend fun get(email: String,
                             gameId: String): Game? {
        return firestore.collection(GAME_COLLECTION)
            .document(gameId).get().await().toObject()
    }

    override suspend fun insert(email: String,
                                game: Game)
    {
        val gameWithEmail = game.copy(email = email)

        firestore.collection(GAME_COLLECTION)
            .add(gameWithEmail)
            .await()

    }

    override suspend fun update(email: String,
                                game: Game) {
        val gameUpdate =
            game.copy()

        firestore.collection(GAME_COLLECTION)
            .document(game._id)
            .set(gameUpdate).await()
    }

    override suspend fun delete(email: String,
                                gameId: String) {
        firestore.collection(GAME_COLLECTION)
            .document(gameId)
            .delete().await()
    }
}
