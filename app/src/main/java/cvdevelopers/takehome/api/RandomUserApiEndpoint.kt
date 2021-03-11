package cvdevelopers.takehome.api

import cvdevelopers.takehome.models.ApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiEndpoint {

    @GET("/api/")
    fun getClients(
        @Query("page") page: String,
        @Query("results") results: String = "15"
    ): Single<ApiResponse>

    companion object {
        const val SERVER = "https://randomuser.me"
    }
}