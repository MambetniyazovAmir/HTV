package uz.kashtan.hamkortv.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_address.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.retrofit.network.LoginNetworkDataSourceImpl
import uz.kashtan.hamkortv.retrofit.network.StreetNetworkDataSourceImpl
import uz.kashtan.hamkortv.room.models.StreetOrQuarter
import uz.kashtan.hamkortv.ui.main.login.LoginActivity
import java.security.acl.Owner


class AddressDialog(
    context: Context,
    private var onInputListener: OnInputListener
) :
    Dialog(context) {

    private var streets: List<StreetOrQuarter> = arrayListOf()
    private lateinit var apiService: ApiService
    private lateinit var streetOrQuarter: StreetNetworkDataSourceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_address)
        apiService = ApiService(ConnectivityInterceptorImpl(context.applicationContext))
        streetOrQuarter = StreetNetworkDataSourceImpl(apiService)


        val list = arrayOf(
            "Bogishamol",
            "Amir Temur",
            "Yunusobod",
            "Chilonzor",
            "KOtlin",
            "Java",
            "Android studio",
            "NewVersion",
            "12435122",
            "Bogishamol",
            "Amir Temur",
            "Yunusobod",
            "Chilonzor",
            "KOtlin",
            "Java",
            "Android studio",
            "NewVersion",
            "12435122"
        )
        val adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, list)
        spinner.adapter = adapter
        streetOrQuarter.downloadedStreets.observe(LoginActivity(), Observer {
            streets = it
        })
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(context, list[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some actioan when nothing is selected
            }
        }

        okBtn.setOnClickListener {
            onInputListener.sendText(
                streetAddress.text.toString(),
                houseNumber.text.toString(),
                homeAddress.text.toString()
            )
            dismiss()
        }
        cancelBtn.setOnClickListener {
            dismiss()
        }
    }
}