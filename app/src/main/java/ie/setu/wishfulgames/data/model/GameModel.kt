package ie.setu.wishfulgames.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

@Entity
data class GameModel(@DocumentId var _id : String = "N/A",
                     var title : String = "N/A",
                     var description : String = "N/A",
                     var genre : String = "N/A",
                     var rating : Int = 0,
                     var price : Int = 0,
                     var email: String = "joe@bloggs.com")

val libraryList = List(30) { _ ->
    GameModel()
}
