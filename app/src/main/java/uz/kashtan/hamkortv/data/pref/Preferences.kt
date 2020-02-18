package uz.kashtan.hamkortv.data.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Preferences{
    var pref: SharedPreferences? = null
    fun initial(context: Context){
        pref = PreferenceManager.getDefaultSharedPreferences(context)
    }

    var firstOpened : Boolean
    get() = pref?.getBoolean(Preferences::firstOpened.name, true) ?: true
    set(value) {
        pref?.edit()?.putBoolean(Preferences::firstOpened.name, value)?.apply()
    }

}