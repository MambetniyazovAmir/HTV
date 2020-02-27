package uz.kashtan.hamkortv.ui.main.login

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.ApiService
import uz.kashtan.hamkortv.retrofit.ConnectivityInterceptor
import uz.kashtan.hamkortv.retrofit.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.retrofit.LoginNetworkDataSourceImpl
import uz.kashtan.hamkortv.ui.main.login.adapter.HistoryAdapter

class LoginActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_login

    private val adapter = HistoryAdapter()
    private lateinit var apiService: ApiService
    private lateinit var loginNetworkDataSource: LoginNetworkDataSourceImpl
    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.auth)
        enableToolbarBackButton()
    }

    var code = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        code = intent.getStringExtra("code")
        history_list.adapter = adapter
        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
        loginNetworkDataSource = LoginNetworkDataSourceImpl(apiService)
        loginNetworkDataSource.downloadedLogin.observe(this, Observer {
            adapter.setData(it)
        })
        GlobalScope.launch(Dispatchers.Main) {
            loginNetworkDataSource.fetch("2020", code)
        }
    }
}
