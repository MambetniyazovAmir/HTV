package uz.kashtan.hamkortv.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import uz.kashtan.hamkortv.data.pref.Preferences

class MyFirebaseInstanceIdService: FirebaseInstanceIdService() {
    val TAG = "PushNotifService"
    lateinit var name: String

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Preferences.setToken(token)
        Log.d(TAG, "Token $token")
    }
}