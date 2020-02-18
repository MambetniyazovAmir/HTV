package com.app.basemodule.helpers

import android.content.Context
import android.content.SharedPreferences

open class BaseSettings(val context: Context) {
    companion object {
        val NAME = "EvoStringPref"
    }

    val preferences: SharedPreferences by lazy { context.getSharedPreferences(NAME, Context.MODE_PRIVATE); }
    fun prefString(key: String? = null) = PrefStringDelegate(preferences)
    fun prefLong(key: String? = null) = PrefLongDelegate(preferences)
    fun prefInt(defVal: Int = -1) = PrefIntDelegate(preferences, defVal)
    fun prefBoolean() = PrefBooleanDelegate(preferences)
}