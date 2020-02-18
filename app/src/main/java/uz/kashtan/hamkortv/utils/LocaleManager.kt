package uz.kashtan.hamkortv.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.Log

import java.util.Locale

object LocaleManager {

    val LANGUAGE_RUSSIAN = "ru"
    val LANGUAGE_UZBEK = "uz"
    val INITIALIZATED = "inited"
    private val LANGUAGE_KEY = "language_key"
    private val LANGUAGE_PREFERENCE = "Language"
    var LANGUAGE_CURRENT = "ru"

    fun setLocale(c: Context): Context {
        return updateResources(c, getLanguage(c))
    }
    fun updateCurrentLang(c: Context) {
        val prefs = c.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE)
        val lang = prefs.getString(LANGUAGE_KEY, LANGUAGE_RUSSIAN) ?: LANGUAGE_RUSSIAN
        LANGUAGE_CURRENT = lang
    }
    fun setNewLocale(c: Context, language: String): Context {
        val langLowerCase = language.toLowerCase()
        if(getLanguage(c) == langLowerCase && isInitialed(c)) return c

        initial(c)
        persistLanguage(c, langLowerCase)
        LANGUAGE_CURRENT = langLowerCase
        return updateResources(c, langLowerCase)
    }

    fun getLanguage(c: Context): String {
        val prefs = c.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE)
        val lang = prefs.getString(LANGUAGE_KEY, LANGUAGE_RUSSIAN) ?: LANGUAGE_RUSSIAN
        LANGUAGE_CURRENT = lang
        return lang
    }
    fun initial(c: Context) {
        val prefs = c.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(INITIALIZATED, true).apply()
    }
    fun isInitialed(c: Context): Boolean {
        val prefs = c.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE)
        return prefs.getBoolean(INITIALIZATED, false)
    }
    fun restoreLang(c: Context) {
        val prefs = c.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(INITIALIZATED, false).apply()
    }
    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(c: Context, language: String) {
        val prefs = c.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE)
        prefs.edit().putString(LANGUAGE_KEY, language).apply()
    }

    private fun updateResources(context: Context, language: String): Context {
        var context = context
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale)
            context = context.createConfigurationContext(config)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
        }
        return context
    }

    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
    }
}