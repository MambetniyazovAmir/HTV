package uz.kashtan.hamkortv.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.room.models.ChannelsModel
import uz.kashtan.hamkortv.room.models.LoginModel

interface ApiService {
    @GET("Channels")
    fun getChannelsAsync(): Deferred<List<ChannelsModel>>

    @GET("auth")
    fun getAuthAsync(
        @Query("Dom") dom: String,
        @Query("Kvartal") kvartal: String,
        @Query("Kvartira") kvartira: String,
        @Query("ID") id: String
    ): Deferred<List<AuthModel>>

    @GET("CreditStories")
    fun getLoginAsync(
        @Query("Year") year: String,
        @Query("CodeClient") codeClient: String
    ): Deferred<List<LoginModel>>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiService {
            val requestInterceptor = Interceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor it.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://31.135.214.47/FileWebTest/hs/Mobile/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}