package uz.kashtan.hamkortv.ui.main.offer

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_offer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.retrofit.network.RequestNetworkDataSourceImpl
import uz.kashtan.hamkortv.ui.dialog.HelpDialog
import java.lang.Exception


class OfferActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_offer
    private lateinit var apiService: ApiService
    private lateinit var requestNetworkDataSource: RequestNetworkDataSourceImpl
    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.offers)
        enableToolbarBackButton()
        fioEmployee.text = intent.getStringExtra("name")
        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
        requestNetworkDataSource = RequestNetworkDataSourceImpl(apiService)
        requestNetworkDataSource.downloadedRequest.observe(this, Observer {
            if (it[0].code == "1") {
                Toast.makeText(this, it[0].message, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, it[0].message, Toast.LENGTH_SHORT).show()
            }
        })
        val codeClient = intent.getStringExtra("code")
        tvSend.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                requestNetworkDataSource.fetchRequest(
                    codeClient,
                    etTextMessage.text.toString()
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val photoStr = intent.getStringExtra("photo")
            val imageBytes = Base64.decode(photoStr, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            photoEmployee.setImageBitmap(decodedImage)
        } catch (e: Exception){
            Log.d("photo of master", e.message)
        }

        info.setOnClickListener {
            val dialog = HelpDialog(this)
            dialog.show()
        }
    }
}
