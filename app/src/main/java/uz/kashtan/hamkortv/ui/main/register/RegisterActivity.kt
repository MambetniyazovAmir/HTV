package uz.kashtan.hamkortv.ui.main.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.basemodule.extensions.onClick
import com.app.basemodule.extensions.toastLN
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.*
import uz.kashtan.hamkortv.room.models.Apartment
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.Quarter
import uz.kashtan.hamkortv.ui.dialog.apartment.ApartmentDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.apartment.ApartmentListDialog
import uz.kashtan.hamkortv.ui.dialog.house.HouseDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.house.HouseListDialog
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterListDialog

class RegisterActivity : BaseActivity(), QuarterDialogButtonClickListener, HouseDialogButtonClickListener, ApartmentDialogButtonClickListener {

    private lateinit var quarterDialog: QuarterListDialog
    private lateinit var houseDialog: HouseListDialog
    private lateinit var apartmentDialog: ApartmentListDialog
    private lateinit var apiService: ApiService
    private lateinit var selectedQuarter: Quarter
    private lateinit var selectedHouse: House
    private lateinit var selectedApartment: Apartment
    private lateinit var quarterNetworkDataSource: QuarterNetworkDataSourceImpl
    private lateinit var houseNetworkDataSource: HouseNetworkDataSourceImpl
    private lateinit var apartmentNetworkDataSource: ApartmentNetworkDataSourceImpl
    private var quarterList: MutableLiveData<List<Quarter>> = MutableLiveData()
    private var houseList: MutableLiveData<List<House>> = MutableLiveData()
    private var apartmentList: MutableLiveData<List<Apartment>> = MutableLiveData()
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
        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
        quarterNetworkDataSource = QuarterNetworkDataSourceImpl(apiService)
        houseNetworkDataSource = HouseNetworkDataSourceImpl(apiService)
        apartmentNetworkDataSource = ApartmentNetworkDataSourceImpl(apiService)

        loading.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.Main) {
            quarterNetworkDataSource.fetchStreets()
        }

        GlobalScope.launch(Dispatchers.Main) {
            houseNetworkDataSource.fetchHouses()
        }

        GlobalScope.launch(Dispatchers.Main) {
            apartmentNetworkDataSource.fetchApartments()
        }

        quarterNetworkDataSource.downloadedStreets.observe(this, Observer {
            quarterList.postValue(it)
        })
        houseNetworkDataSource.downloadedHouses.observe(this, Observer { list->
            houseList.postValue(list)
        })
        apartmentNetworkDataSource.downloadedApartment.observe(this, Observer { list->
            apartmentList.postValue(list)
            loading.visibility = View.GONE
        })

        userQuarter.onClick {

            if (!quarterList.value.isNullOrEmpty()) {
                quarterDialog = QuarterListDialog(this, this, quarterList.value!!)
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
            if (!apartmentList.value.isNullOrEmpty() && ::selectedHouse.isInitialized) {
                apartmentDialog = ApartmentListDialog(this, this, filteredApartmentList.value!!)
                apartmentDialog.show()
            } else {
                toastLN("Вы еще не выбрали дом")
            }
        }

    }

    override fun onPositiveButtonClick(quarter: Quarter) {
        selectedQuarter = quarter
        filteredHouseList.value = houseList.value?.filter {
            it.codeQuarter == selectedQuarter.code
        }
        tvChooseQuarter.text = "Квартал: ${selectedQuarter.name}"
        quarterList.value?.forEach { it.isSelected = false }
        quarterDialog.dismiss()
    }

    override fun onNegativeButtonClick() {
        quarterList.value?.forEach { it.isSelected = false }
        quarterDialog.dismiss()
    }

    override fun onHousePositiveButtonClick(house: House) {
        selectedHouse = house
        filteredApartmentList.value = apartmentList.value?.filter { it.codeHouse == selectedHouse.code }
        tvChooseHouse.text = "Дом: ${selectedHouse.name}"
        houseList.value?.forEach { it.isSelected = false }
        houseDialog.dismiss()
    }

    override fun onHouseNegativeButtonClick() {
        houseList.value?.forEach { it.isSelected = false }
        houseDialog.dismiss()
    }

    override fun onApartmentPositiveButtonClick(apartment: Apartment) {
        selectedApartment = apartment
        tvChooseApartment.text = "Квартира: ${apartment.name}"
        apartmentList.value?.forEach { it.isSelected = false }
        apartmentDialog.dismiss()
    }

    override fun onApartmentNegativeButtonClick() {
        apartmentList.value?.forEach { it.isSelected = false }
        apartmentDialog.dismiss()
    }
}
