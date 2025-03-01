package ie.setu.wishfulgames.data.repository

import ie.setu.wishfulgames.data.GameModel
import ie.setu.wishfulgames.data.room.GameDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject
constructor(private val gameDAO: GameDAO) {
    fun getAll(): Flow<List<GameModel>>
            = gameDAO.getAll()

    suspend fun insert(game: GameModel)
    { gameDAO.insert(game) }

    suspend fun update(game: GameModel)
    { gameDAO.update(game.id,game.title,game.description,game.genre) }

    suspend fun delete(game: GameModel)
    { gameDAO.delete(game) }

    fun get(id: Int) = gameDAO.get(id)
}
