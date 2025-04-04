package ie.setu.wishfulgames.data.api

import ie.setu.wishfulgames.data.GameModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GameService {
    @GET(ServiceEndPoints.GAMES_ENDPOINT)
    suspend fun getall(): Response<List<GameModel>>

    @GET(ServiceEndPoints.GAMES_ENDPOINT + "/{id}")
    suspend fun get(@Path("id") id: String): Response<List<GameModel>>

    @DELETE(ServiceEndPoints.GAMES_ENDPOINT + "/{id}")
    suspend fun delete(@Path("id") id: String): GameWrapper

    @POST(ServiceEndPoints.GAMES_ENDPOINT)
    suspend fun post(@Body donation: GameModel): GameWrapper

    @PUT(ServiceEndPoints.GAMES_ENDPOINT + "/{id}")
    suspend fun put(@Path("id") id: String,
                    @Body donation: GameModel
    ): GameWrapper
}
