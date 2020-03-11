package uz.kashtan.hamkortv.ui.main.complaint

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_complaint.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ComplaintsNetworkDataSourceImpl
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl

class ComplaintActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_complaint

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.complaints)
        enableToolbarBackButton()
    }

    var codeClient = ""
    private lateinit var apiService: ApiService
    private lateinit var complaintsNetworkDataSource: ComplaintsNetworkDataSourceImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
        complaintsNetworkDataSource = ComplaintsNetworkDataSourceImpl(apiService)
        complaintsNetworkDataSource.downloadedComplaints.observe(this, Observer {
            if (it[0].code == "1") {
                Toast.makeText(this, it[0].message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong complaint", Toast.LENGTH_SHORT).show()
            }
        }
        )
        codeClient = intent.getStringExtra("code")
        tvSend.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                complaintsNetworkDataSource.fetchComplaint(
                    codeClient,
                    etTheme.text.toString(),
                    etTextMessage.text.toString()
                )
            }
        }
    }
}
