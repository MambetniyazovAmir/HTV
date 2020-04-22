package uz.kashtan.hamkortv.data.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Preferences {
    var pref: SharedPreferences? = null
    fun initial(context: Context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context)
    }

    var firstOpened: Boolean
        get() = pref?.getBoolean(Preferences::firstOpened.name, true) ?: true
        set(value) {
            pref?.edit()?.putBoolean(Preferences::firstOpened.name, value)?.apply()
        }

    const val USER_QUARTER: String = "userQuarter"
    const val USER_HOUSE: String = "userHouse"
    const val USER_APARTMENT: String = "userApartment"
    const val USER_ID: String = "userId"
    const val SAVE_DATA: String = "saveData"
    const val CODE_QUARTER: String = "codeQuarter"
    const val CODE_HOUSE: String = "codeHouse"
    const val CODE_APARTMENT: String = "codeApartment"
    const val TOKEN: String = "token"

    fun setToken(token: String?){
        pref?.edit()?.putString(TOKEN, token)?.apply()
    }

    fun setUserQuarter(quarter: String) {
        pref?.edit()?.putString(USER_QUARTER, quarter)?.apply()
    }

    fun setUserHouse(house: String) {
        pref?.edit()?.putString(USER_HOUSE, house)?.apply()
    }

    fun setUserApartment(apartment: String) {
        pref?.edit()?.putString(USER_APARTMENT, apartment)?.apply()
    }

    fun setUserId(id: String) {
        pref?.edit()?.putString(USER_ID, id)?.apply()
    }

    fun setSaveData(save: Boolean) {
        pref?.edit()?.putBoolean(SAVE_DATA, save)?.apply()
    }

    fun setCodeQuarter(codeQuarter: String) {
        pref?.edit()?.putString(CODE_QUARTER, codeQuarter)?.apply()
    }

    fun setCodeHouse(codeHouse: String) {
        pref?.edit()?.putString(CODE_HOUSE, codeHouse)?.apply()
    }

    fun setCodeApartment(codeApartment: String) {
        pref?.edit()?.putString(CODE_APARTMENT, codeApartment)?.apply()
    }

    fun getToken(): String = pref?.getString(TOKEN, "")!!
    fun getUserQuarter(): String = pref?.getString(USER_QUARTER, "")!!
    fun getUserHouse(): String = pref?.getString(USER_HOUSE, "")!!
    fun getUserApartment(): String = pref?.getString(USER_APARTMENT, "")!!
    fun getUserId(): String = pref?.getString(USER_ID, "")!!
    fun isSaveData(): Boolean = pref?.getBoolean(SAVE_DATA, false)!!
    fun getCodeQuarter(): String = pref?.getString(CODE_QUARTER, "")!!
    fun getCodeHouse(): String = pref?.getString(CODE_HOUSE, "")!!
    fun getCodeApartment(): String = pref?.getString(CODE_APARTMENT, "")!!
}