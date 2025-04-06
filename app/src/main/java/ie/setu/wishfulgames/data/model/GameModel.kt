package ie.setu.wishfulgames.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameModel(@PrimaryKey(autoGenerate = true)
                     var id : Int = 0,
                     val _id: String = "N/A",
                     @SerializedName("gametitle")
                     var title : String = "N/A",
                     @SerializedName("gamedescription")
                     var description : String = "N/A",
                     @SerializedName("gamegenre")
                     var genre : String = "N/A",
                     var rating : Int = 0,
                     var _rating : String = "N/A",
                     var price : Int = 0,
                     var _price : String = "N/A",
                     var email: String = "joe@bloggs.com")

val libraryList = List(30) { _ ->
    GameModel()
}
