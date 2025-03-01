package ie.setu.wishfulgames.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ie.setu.wishfulgames.data.GameModel

@Database(entities = [GameModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGameDAO(): GameDAO
}
