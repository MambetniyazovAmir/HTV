package uz.kashtan.hamkortv.ui.intro

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.basemodule.extensions.onClick
import com.app.basemodule.extensions.toastLN
import com.jakewharton.rxbinding2.view.enabled
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.data.pref.Preferences
import uz.kashtan.hamkortv.retrofit.network.*
import uz.kashtan.hamkortv.room.HTVDatabase
import uz.kashtan.hamkortv.room.models.Apartment
import uz.kashtan.hamkortv.ui.main.CategoryPickActivity
import uz.kashtan.hamkortv.utils.AnimationTemplateUtils
import uz.kashtan.hamkortv.utils.LocaleManager

class IntroActivity: BaseActivity(){
    override val layoutResource: Int
        get() = R.layout.activity_intro

    override fun init(savedInstanceState: Bundle?) {

        AnimationTemplateUtils.animateStepByStepVisible(
           arrayOf(
               tvApplicationName,
               ivLogo,
               tvAppDiscription,
               tvPickLanguage,
               tvRus,
               tvUzbek,
               tvNext
           )
        )

        val apiService = ApiService(
            ConnectivityInterceptorImpl(this.applicationContext)
        )

        val connectivityInterceptorImpl = ConnectivityInterceptorImpl(this.applicationContext)

        if (!connectivityInterceptorImpl.isOnline()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("There is no internet connection")
            builder.setMessage("Do you want to restart the app?")
            builder.setPositiveButton("Yes", { dialogInterface: DialogInterface, i: Int ->
                refreshActivity()
            })
            builder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int ->
                finish()
            })
            builder.setCancelable(false)
            builder.show()
        }

        val apartmentDao = HTVDatabase.invoke(this).apartmentDao()
        val quarterDao = HTVDatabase.invoke(this).quarterDao()
        val houseDao = HTVDatabase.invoke(this).houseDao()
        val quarterNetworkDataSource = QuarterNetworkDataSourceImpl(apiService)
        val houseNetworkDataSource = HouseNetworkDataSourceImpl(apiService)
        val apartmentNetworkDataSource = ApartmentNetworkDataSourceImpl(apiService)
        if(quarterDao.getAllQuarters().isEmpty()){
            tvRus.isEnabled = false
            tvUzbek.isEnabled = false
            tvNext.isEnabled = false
            progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.Main) {
                quarterNetworkDataSource.fetchStreets()
                houseNetworkDataSource.fetchHouses()
                apartmentNetworkDataSource.fetchApartments()
            }

            quarterNetworkDataSource.downloadedStreets.observe(this, Observer {
                quarterDao.insertToDb(it)
            })

            houseNetworkDataSource.downloadedHouses.observe(this, Observer {
                houseDao.insert(it)
            })

            apartmentNetworkDataSource.downloadedApartment.observe(this, Observer {
                apartmentDao.insert(it)
                progressBar.visibility = View.GONE
                tvNext.isEnabled = true
                tvUzbek.isEnabled = true
                tvRus.isEnabled = true
            })
        }
        if(LocaleManager.getLanguage(this) == LocaleManager.LANGUAGE_RUSSIAN){
            tvRus.setTextColor(Color.parseColor("#212121"))
            tvUzbek.setTextColor(Color.parseColor("#99212121"))
            tvUzbek.onClick {
                LocaleManager.setNewLocale(this, LocaleManager.LANGUAGE_UZBEK)
                refreshActivity()
            }
        } else {
            tvRus.setTextColor(Color.parseColor("#99212121"))
            tvUzbek.setTextColor(Color.parseColor("#212121"))
            tvRus.onClick {
                LocaleManager.setNewLocale(this, LocaleManager.LANGUAGE_RUSSIAN)
                refreshActivity()
            }
        }

        tvNext.onClick {
            Preferences.firstOpened = false
            startActivity(Intent(this, CategoryPickActivity::class.java))
            finish()
        }
    }

    private fun refreshActivity(){
        val intent = intent
        finish()
        startActivity(intent)
    }
}