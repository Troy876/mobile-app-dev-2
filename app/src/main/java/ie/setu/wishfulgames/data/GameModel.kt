package ie.setu.wishfulgames.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameModel(@PrimaryKey(autoGenerate = true)
                     var id : Int = 0,
                     var title : String = "",
                     var description : String = "",
                     var genre : String = "",
                     var rating : Int = 0,
                     var price : Int = 0)

val libraryList = List(20) { _ ->
    GameModel()
}
