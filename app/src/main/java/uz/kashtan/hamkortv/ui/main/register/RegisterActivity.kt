package uz.kashtan.hamkortv.ui.main.register

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.app.basemodule.extensions.onClick
import com.app.basemodule.extensions.toastLN
import kotlinx.android.synthetic.main.activity_register.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.ApartmentNetworkDataSourceImpl
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.HouseNetworkDataSourceImpl
import uz.kashtan.hamkortv.retrofit.network.QuarterNetworkDataSourceImpl
import uz.kashtan.hamkortv.room.HTVDatabase
import uz.kashtan.hamkortv.room.models.Apartment
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.Quarter
import uz.kashtan.hamkortv.ui.dialog.apartment.ApartmentDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.apartment.ApartmentListDialog
import uz.kashtan.hamkortv.ui.dialog.house.HouseDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.house.HouseListDialog
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterListDialog
import uz.kashtan.hamkortv.utils.DataHolder

class RegisterActivity() :
    BaseActivity(), QuarterDialogButtonClickListener, HouseDialogButtonClickListener,
    ApartmentDialogButtonClickListener {

    private lateinit var quarterDialog: QuarterListDialog
    private lateinit var houseDialog: HouseListDialog
    private lateinit var apartmentDialog: ApartmentListDialog
    private lateinit var apiService: ApiService
    private lateinit var selectedQuarter: Quarter
    private lateinit var selectedHouse: House
    private lateinit var selectedApartment: Apartment

    private var quarterList: List<Quarter> = arrayListOf()
    private var houseList: List<House> = arrayListOf()
    private var apartmentList: List<Apartment> = arrayListOf()

    private lateinit var quarterNetworkDataSource: QuarterNetworkDataSourceImpl
    private lateinit var houseNetworkDataSource: HouseNetworkDataSourceImpl
    private lateinit var apartmentNetworkDataSource: ApartmentNetworkDataSourceImpl
    private var filteredHouseList: MutableLiveData<List<House>> = MutableLiveData()
    private var filteredApartmentList: MutableLiveData<List<Apartment>> = MutableLiveData()
    override val layoutResource: Int
        get() = R.layout.activity_register

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quarterList = DataHolder.quarterList
        houseList = DataHolder.houseList
        apartmentList = DataHolder.apartmentList
//        val quarterDao = HTVDatabase.invoke(this).quarterDao()
//        val houseDao = HTVDatabase.invoke(this).houseDao()
//        val apartmentDao = HTVDatabase.invoke(this).apartmentDao()
//        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
//        quarterNetworkDataSource = QuarterNetworkDataSourceImpl(apiService)
//        houseNetworkDataSource = HouseNetworkDataSourceImpl(apiService)
//        apartmentNetworkDataSource = ApartmentNetworkDataSourceImpl(apiService)
//
//        loading.visibility = View.VISIBLE
//
//        GlobalScope.launch(Dispatchers.Main) {
//            quarterNetworkDataSource.fetchStreets()
//        }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            houseNetworkDataSource.fetchHouses()
//        }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            apartmentNetworkDataSource.fetchApartments()
//        }
//
//        quarterNetworkDataSource.downloadedStreets.observe(this, Observer {
//            quarterList.postValue(it)
//        })
//        houseNetworkDataSource.downloadedHouses.observe(this, Observer { list->
//            houseList.postValue(list)
//        })
//        apartmentNetworkDataSource.downloadedApartment.observe(this, Observer { list->
//            apartmentList.postValue(list)
//            loading.visibility = View.GONE
//        })
        userQuarter.onClick {
            if (!quarterList.isNullOrEmpty()) {
                quarterDialog = QuarterListDialog(this, this, quarterList)
                quarterDialog.show()
            }
        }

        userHouse.onClick {
            if (::selectedQuarter.isInitialized) {
                houseDialog = HouseListDialog(this, this, filteredHouseList.value!!)
                houseDialog.show()
            } else {
                toastLN("Вы еще не выбрали квартал")
            }
        }

        userApartment.onClick {
            if (!apartmentList.isNullOrEmpty() && ::selectedHouse.isInitialized) {
                apartmentDialog = ApartmentListDialog(this, this, filteredApartmentList.value!!)
                apartmentDialog.show()
            } else {
                toastLN("Вы еще не выбрали дом")
            }
        }
    }

    override fun onPositiveButtonClick(quarter: Quarter) {
        selectedQuarter = quarter
        filteredHouseList.value = houseList.filter {
            it.codeQuarter == selectedQuarter.code
        }
        tvChooseQuarter.text = "Квартал: ${selectedQuarter.name}"
        quarterList.forEach { it.isSelected = false }
        quarterDialog.dismiss()
    }

    override fun onNegativeButtonClick() {
        quarterList.forEach { it.isSelected = false }
        quarterDialog.dismiss()
    }

    override fun onHousePositiveButtonClick(house: House) {
        selectedHouse = house
        filteredApartmentList.value =
            apartmentList.filter { it.codeHouse == selectedHouse.code }
        tvChooseHouse.text = "Дом: ${selectedHouse.name}"
        houseList.forEach { it.isSelected = false }
        houseDialog.dismiss()
    }

    override fun onHouseNegativeButtonClick() {
        houseList.forEach { it.isSelected = false }
        houseDialog.dismiss()
    }

    override fun onApartmentPositiveButtonClick(apartment: Apartment) {
        selectedApartment = apartment
        apartmentDialog.dismiss()
        tvChooseApartment.text = "Квартира: ${apartment.name}"
        apartmentList.forEach { it.isSelected = false }
    }

    override fun onApartmentNegativeButtonClick() {
        apartmentList.forEach { it.isSelected = false }
        apartmentDialog.dismiss()
    }
}
