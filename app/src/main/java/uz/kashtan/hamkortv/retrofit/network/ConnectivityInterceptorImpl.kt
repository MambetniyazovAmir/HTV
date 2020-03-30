package uz.kashtan.hamkortv.retrofit.network

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Response
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptor

class ConnectivityInterceptorImpl(context: Context) :
    ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()){
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    fun isOnline(): Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo =connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}