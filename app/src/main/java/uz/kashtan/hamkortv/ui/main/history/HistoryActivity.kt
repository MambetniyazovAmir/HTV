package uz.kashtan.hamkortv.ui.main.history

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.retrofit.network.LoginNetworkDataSourceImpl
import uz.kashtan.hamkortv.ui.main.history.adapter.HistoryAdapter

class HistoryActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_history

    private val adapter = HistoryAdapter()
    private lateinit var apiService: ApiService
    private lateinit var loginNetworkDataSource: LoginNetworkDataSourceImpl
    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.auth)
        enableToolbarBackButton()
        tvPay.onClick {
            try {
                val pm: PackageManager = this.packageManager
                val launchIntent = pm.getLaunchIntentForPackage("uz.dida.payme")
                launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(launchIntent)
            } catch (e: Exception){
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse("https://payme.uz")
                startActivity(openUrl)
            }
        }
    }

    var code = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        code = intent.getStringExtra("code")
        history_list.adapter = adapter
        apiService = ApiService(
            ConnectivityInterceptorImpl(this.applicationContext)
        )
        loginNetworkDataSource =
            LoginNetworkDataSourceImpl(
                apiService
            )
        loginNetworkDataSource.downloadedLogin.observe(this, Observer {
            adapter.setData(it)
        })
        GlobalScope.launch(Dispatchers.Main) {
            loginNetworkDataSource.fetch("2020", code)
        }
    }
}
