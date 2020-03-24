package uz.kashtan.hamkortv.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_address.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.retrofit.network.QuarterNetworkDataSourceImpl
import uz.kashtan.hamkortv.room.models.Quarter


class AddressDialog(
    context: Context,
    private var onInputListener: OnInputListener
) :
    Dialog(context) {

    private var streets: List<Quarter> = arrayListOf()
    private lateinit var apiService: ApiService
    private lateinit var streetOrQuarter: QuarterNetworkDataSourceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_address)
        apiService = ApiService(ConnectivityInterceptorImpl(context.applicationContext))
        streetOrQuarter = QuarterNetworkDataSourceImpl(apiService)

        positiveButton.setOnClickListener {
            onInputListener.sendText(
                streetAddress.text.toString(),
                houseNumber.text.toString(),
                homeAddress.text.toString()
            )
            dismiss()
        }
        negativeButton.setOnClickListener {
            dismiss()
        }
    }
}