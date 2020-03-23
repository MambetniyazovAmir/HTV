package uz.kashtan.hamkortv.ui.main.register

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.retrofit.network.HouseNetworkDataSourceImpl
import uz.kashtan.hamkortv.retrofit.network.QuarterNetworkDataSourceImpl
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.Quarter
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterListDialog

class RegisterActivity : BaseActivity() {

    private lateinit var quarterDialog: QuarterListDialog
    private lateinit var apiService: ApiService
    private lateinit var quarterNetworkDataSource: QuarterNetworkDataSourceImpl
    private lateinit var houseNetworkDataSource: HouseNetworkDataSourceImpl
    private var quarterList: MutableLiveData<List<Quarter>> = MutableLiveData()
    private var houseList: MutableLiveData<List<House>> = MutableLiveData()
    override val layoutResource: Int
        get() = R.layout.activity_register

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
        quarterNetworkDataSource = QuarterNetworkDataSourceImpl(apiService)
        houseNetworkDataSource = HouseNetworkDataSourceImpl((apiService))
        quarterNetworkDataSource.downloadedStreets.observe(this, Observer {
            quarterList.postValue(it)
        })
        houseNetworkDataSource.downloadedHouses.observe(this, Observer {
            houseList.postValue(it)
        })
        quarterList.observe(this, Observer {
            quarterDialog = QuarterListDialog(this, it)
            quarterDialog.show()
        })
        val k: String = ""
        houseList.observe(this, Observer {list->
            list.forEach {
                if (it.codeQuarter == k) {

                }
            }
        })
        userQuarter.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                quarterNetworkDataSource.fetchStreets()
            }
        }
    }
}
