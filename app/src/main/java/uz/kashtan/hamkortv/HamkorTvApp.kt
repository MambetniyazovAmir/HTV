package uz.kashtan.hamkortv

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import uz.kashtan.hamkortv.data.pref.Preferences
import uz.kashtan.hamkortv.retrofit.network.*
import uz.kashtan.hamkortv.retrofit.repository.HTVRepository
import uz.kashtan.hamkortv.retrofit.repository.HTVRepositoryImpl
import uz.kashtan.hamkortv.room.HTVDatabase
import uz.kashtan.hamkortv.utils.LocaleManager

class HamkorTvApp : Application(), KodeinAware {

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

    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidModule(this@HamkorTvApp))
            bind() from singleton { HTVDatabase(instance()) }
            bind() from singleton { instance<HTVDatabase>().authDao() }
            bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
            bind() from singleton { ApiService(instance()) }
            bind<QuarterNetworkDataSource>() with singleton { QuarterNetworkDataSourceImpl(instance()) }
            bind<HTVRepository>() with singleton { HTVRepositoryImpl(instance(), instance()) }
        }
}