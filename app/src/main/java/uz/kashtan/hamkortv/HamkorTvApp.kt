package uz.kashtan.hamkortv

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import uz.kashtan.hamkortv.data.pref.Preferences
import uz.kashtan.hamkortv.utils.LocaleManager

class HamkorTvApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Preferences.initial(this)
        LocaleManager.updateCurrentLang(this)

    }
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }
}