package uz.kashtan.hamkortv.retrofit.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import uz.kashtan.hamkortv.room.models.*

interface ApiService {
    @GET("Channels")
    fun getChannelsAsync(): Deferred<List<ChannelsModel>>

    @PUT("CreateCreditHistory")
    fun signUpAsync(@Body abonent: Abonent): Deferred<RegisterModel>

    @POST("SetRating")
    fun setRating(@Body rating: RatingModel): Deferred<RatingResponse>

    @GET("GetRequests")
    fun getRequestsAsync(@Query("id") codeId: String): Deferred<List<Requests>>

    @GET("auth")
    fun getAuthAsync(
        @Query("Dom") dom: String,
        @Query("Kvartal") kvartal: String,
        @Query("Kvartira") kvartira: String,
        @Query("ID") id: String,
        @Query("Token") token: String
    ): Deferred<List<AuthModel>>

    @GET("GetQuarters")
    fun getQuartersAsync(): Deferred<List<Quarter>>

    @GET("GetHouses")
    fun getHousesAsync(): Deferred<List<House>>

    @GET("GetApartments")
    fun getApartmentsAsync(): Deferred<List<Apartment>>

    @GET("GetCreditHistory")
    fun getLoginAsync(
        @Query("id") codeClient: String,
        @Query("Year") year: String
    ): Deferred<List<LoginModel>>

    @GET("Complaints")
    fun getComplaintsAsync(
        @Query("CodeClient") codeClient: String,
        @Query("Theme") theme: String,
        @Query("Text") text: String
    ): Deferred<List<ComplaintModel>>

    @GET("CreateRequest")
    fun getRequestMessageAsync(
        @Query("Theme") theme: String,
        @Query("CodeClient") codeClient: String
    ): Deferred<List<RequestModel>>

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