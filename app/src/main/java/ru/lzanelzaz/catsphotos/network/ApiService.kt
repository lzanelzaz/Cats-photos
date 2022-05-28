package ru.lzanelzaz.catsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.lzanelzaz.catsphotos.BuildConfig

private const val BASE_URL = "https://api.thecatapi.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    // Use your own api key
    @Headers("X-Api-Key: " + BuildConfig.API_KEY)
    @GET("v1/images/search")
    suspend fun getCatPhoto() : List<CatPhoto>
}

object Api {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
