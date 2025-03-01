package ie.setu.wishfulgames.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import ie.setu.wishfulgames.data.GameModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDAO {
    @Query("SELECT * FROM gamemodel")
    fun getAll(): Flow<List<GameModel>>

    @Insert
    suspend fun insert(game: GameModel)

    @Query("UPDATE gamemodel SET title=:title, description=:description, genre=:genre WHERE id = :id")
    suspend fun update(id: Int, title: String, description: String, genre: String)

    @Delete
    suspend fun delete(game: GameModel)

    @Query("SELECT * FROM gamemodel WHERE id=:id")
    fun get(id: Int): Flow<GameModel>
}
