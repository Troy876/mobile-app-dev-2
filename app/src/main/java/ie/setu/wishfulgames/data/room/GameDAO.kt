package ie.setu.wishfulgames.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ie.setu.wishfulgames.data.GameModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDAO {
    @Query("SELECT * FROM gamemodel")
    fun getAll(): Flow<List<GameModel>>

    @Insert
    suspend fun insert(game: GameModel)

    @Update
    suspend fun update(game: GameModel)

    @Delete
    suspend fun delete(game: GameModel)
}
