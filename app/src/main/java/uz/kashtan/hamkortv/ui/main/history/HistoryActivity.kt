package uz.kashtan.hamkortv.ui.main.history

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
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
import uz.kashtan.hamkortv.room.models.LoginModel
import uz.kashtan.hamkortv.ui.main.history.adapter.HistoryAdapter
import uz.kashtan.hamkortv.ui.main.history.adapter.ItemClickListener

class HistoryActivity : BaseActivity(), ItemClickListener {
    override val layoutResource: Int
        get() = R.layout.activity_history

    private val adapter = HistoryAdapter(this)
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
            } catch (e: Exception) {
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
        history_list.addItemDecoration(DividerItemDecoration(this,  DividerItemDecoration.VERTICAL))
        apiService = ApiService(
            ConnectivityInterceptorImpl(this.applicationContext)
        )
        loginNetworkDataSource =
            LoginNetworkDataSourceImpl(
                apiService
            )
        loginNetworkDataSource.downloadedLogin.observe(this, Observer {
            tvPay.isEnabled = true
            loading.visibility = View.GONE
            adapter.setData(it)
        })
        tvPay.isEnabled = false
        loading.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            loginNetworkDataSource.fetch("2020", code)
        }
    }

    override fun onItemClick(model: LoginModel) {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setTitle("Общая сумма которая вы должны оплатить составляет")
        dialog.setMessage(model.sumToPay)
        dialog.setPositiveButton("Оплатить") { _: DialogInterface, _: Int ->
            try {
                val pm: PackageManager = this.packageManager
                val launchIntent = pm.getLaunchIntentForPackage("uz.dida.payme")
                launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(launchIntent)
            } catch (e: Exception) {
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse("https://payme.uz")
                startActivity(openUrl)
            }
        }
        dialog.setNegativeButton("Позже") { _: DialogInterface?, _: Int ->

        }
        dialog.show()
    }
}
