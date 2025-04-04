package ie.setu.wishfulgames.data.api

import ie.setu.wishfulgames.data.GameModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitRepository @Inject
constructor(private val serviceApi: GameService)  {

    suspend fun getAll(): List<GameModel>
    {
        return withContext(Dispatchers.IO) {
            val donations = serviceApi.getall()
            donations.body() ?: emptyList()
        }
    }

    suspend fun get(id: String): List<GameModel>
    {
        return withContext(Dispatchers.IO) {
            val donation = serviceApi.get(id)
            donation.body() ?: emptyList()
        }
    }

    suspend fun insert(donation: GameModel) : GameWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(donation)
            wrapper
        }
    }

    suspend fun update(donation: GameModel) : GameWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(donation._id,donation)
            wrapper
        }
    }

    suspend fun delete(donation: GameModel) : GameWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(donation._id)
            wrapper
        }
    }
}
