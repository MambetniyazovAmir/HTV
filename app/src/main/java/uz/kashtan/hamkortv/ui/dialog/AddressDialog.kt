package uz.kashtan.hamkortv.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.dialog_address.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.AuthNetworkDataSourceImpl
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.ui.main.user.UserRegistrationActivity

class AddressDialog(context: Context, private var codeClient: MutableLiveData<AuthModel>, private var activity: UserRegistrationActivity, private var onInputListener: OnInputListener) :
    Dialog(context) {
    private lateinit var apiService: ApiService
    private lateinit var authNetworkDataSource: AuthNetworkDataSourceImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_address)
//        apiService = ApiService(ConnectivityInterceptorImpl(context.applicationContext))
//        authNetworkDataSource = AuthNetworkDataSourceImpl(apiService)
//        authNetworkDataSource.downloadedAuth.observe(activity, Observer {
//            if (it[0].codeInfo == "0") {
//                Toast.makeText(context, it[0].message, Toast.LENGTH_SHORT).show()
//            } else {
//                codeClient.postValue(it[0])
//                dismiss()
//            }
//        })
        okBtn.setOnClickListener {
//            GlobalScope.launch(Dispatchers.Main) {
//                authNetworkDataSource.fetchAuth(
//                    houseNumber.text.toString(),
//                    streetAddress.text.toString(),
//                    homeAddress.text.toString(),
//                    etUserId.text.toString()
//                )
//            }
            onInputListener.sendText(streetAddress.text.toString(), houseNumber.text.toString(), homeAddress.text.toString())
            dismiss()
        }
        cancelBtn.setOnClickListener {
            dismiss()
        }
    }
}