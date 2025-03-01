package ie.setu.wishfulgames.data

data class GameModel(var title : String = "",
                     var description : String = "",
                     var genre : String = "",
                     var rating : Int = 0,
                     var price : Int = 0)

val libraryList = List(20) { _ ->
    GameModel()
}
