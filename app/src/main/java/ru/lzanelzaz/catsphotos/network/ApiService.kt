package ru.lzanelzaz.catsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.lzanelzaz.catsphotos.BuildConfig

private const val BASE_URL = "https://api.thecatapi.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    // Use your own api key
    @Headers("X-Api-Key: " + BuildConfig.API_KEY)
    @GET("v1/images/search")
    suspend fun getCatPhoto() : String
}

object Api {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
