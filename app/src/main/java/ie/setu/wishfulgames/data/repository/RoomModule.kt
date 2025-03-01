package ie.setu.wishfulgames.data.repository

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ie.setu.wishfulgames.data.room.AppDatabase
import ie.setu.wishfulgames.data.room.GameDAO
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context):
            AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "game_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideGameDAO(appDatabase: AppDatabase):
            GameDAO = appDatabase.getGameDAO()

    @Provides
    fun provideRoomRepository(game: GameDAO):
            RoomRepository = RoomRepository(game)
}
