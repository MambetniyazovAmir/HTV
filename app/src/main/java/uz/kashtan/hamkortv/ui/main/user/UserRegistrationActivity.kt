package uz.kashtan.hamkortv.ui.main.user

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_user_registration.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.AuthNetworkDataSourceImpl
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.ui.dialog.AddressDialog
import uz.kashtan.hamkortv.ui.dialog.OnInputListener
import uz.kashtan.hamkortv.ui.main.login.LoginActivity
import java.util.jar.Manifest

class UserRegistrationActivity : BaseActivity(), OnInputListener {
    override val layoutResource: Int
        get() = R.layout.activity_user_registration

    private var codeClient: MutableLiveData<AuthModel> = MutableLiveData()

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
    }

    private lateinit var apiService: ApiService
    private lateinit var authNetworkDataSource: AuthNetworkDataSourceImpl
    private var kvartal = ""
    private var dom = ""
    private var kvartira = ""
    private var requestCall = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialog = AddressDialog(this, codeClient, this, this)
        apiService = ApiService(
            ConnectivityInterceptorImpl(this.applicationContext)
        )
        authNetworkDataSource =
            AuthNetworkDataSourceImpl(
                apiService
            )
        authNetworkDataSource.downloadedAuth.observe(this, Observer {
            if (it[0].codeInfo == "0") {
                Toast.makeText(this, it[0].message, Toast.LENGTH_SHORT).show()
            } else {
                codeClient.postValue(it[0])
            }
        })

        codeClient.observe(this, Observer {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("code", it.code)
            intent.putExtra("photo", it.photo)
            intent.putExtra("name", it.name)
            startActivity(intent)
        })

        userAddress.setOnClickListener {
            dialog.show()
        }

        llForgotPassword.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:712767753")
            val s: () -> String = {android.Manifest.permission.CALL_PHONE}
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) startActivity(intent)
            else ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), requestCall)
        }
//        dialog.setOnDismissListener {
//            codeClient.observe(this, Observer {
//                val intent = Intent(this, HistoryActivity::class.java)
//                intent.putExtra("code", it.code)
//                startActivity(intent)
//            })
//        }

        tvLogin.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                authNetworkDataSource.fetchAuth(
                    dom,
                    kvartal,
                    kvartira,
                    etChooseUserId.text.toString()
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun sendText(kvartal: String, dom: String, kvartira: String) {
        this.kvartal = kvartal
        this.dom = dom
        this.kvartira = kvartira
        tvChooseAddress.text = "$kvartal,$dom,$kvartira"
    }
}