package uz.kashtan.hamkortv.ui.main.user

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.basemodule.extensions.onClick
import com.app.basemodule.extensions.toastLN
import kotlinx.android.synthetic.main.activity_user_registration.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.data.pref.Preferences
import uz.kashtan.hamkortv.retrofit.network.*
import uz.kashtan.hamkortv.room.HTVDatabase
import uz.kashtan.hamkortv.room.dao.ApartmentDao
import uz.kashtan.hamkortv.room.dao.HouseDao
import uz.kashtan.hamkortv.room.dao.QuarterDao
import uz.kashtan.hamkortv.room.models.Apartment
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.Quarter
import uz.kashtan.hamkortv.ui.dialog.apartment.ApartmentDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.apartment.ApartmentListDialog
import uz.kashtan.hamkortv.ui.dialog.house.HouseDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.house.HouseListDialog
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterDialogButtonClickListener
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterListDialog
import uz.kashtan.hamkortv.ui.main.login.LoginActivity
import uz.kashtan.hamkortv.ui.main.register.RegisterActivity
import uz.kashtan.hamkortv.utils.DataHolder

class UserRegistrationActivity : BaseActivity(), QuarterDialogButtonClickListener,
    HouseDialogButtonClickListener, ApartmentDialogButtonClickListener {

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
        get() = R.layout.activity_user_registration

    private var codeClient: MutableLiveData<AuthModel> = MutableLiveData()

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.loginPage)
        enableToolbarBackButton()
    }

    private lateinit var authNetworkDataSource: AuthNetworkDataSourceImpl
    private lateinit var apartmentDao: ApartmentDao
    private lateinit var quarterDao: QuarterDao
    private lateinit var houseDao: HouseDao

    private var requestCall = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = ApiService(
            ConnectivityInterceptorImpl(this.applicationContext)
        )
        apartmentDao = HTVDatabase.invoke(this).apartmentDao()
        quarterDao = HTVDatabase.invoke(this).quarterDao()
        houseDao = HTVDatabase.invoke(this).houseDao()
        quarterNetworkDataSource = QuarterNetworkDataSourceImpl(apiService)
        houseNetworkDataSource = HouseNetworkDataSourceImpl(apiService)
        apartmentNetworkDataSource = ApartmentNetworkDataSourceImpl(apiService)
        authNetworkDataSource = AuthNetworkDataSourceImpl(apiService)

        loadData()

        authNetworkDataSource.downloadedAuth.observe(this, Observer {
            if (it[0].codeInfo == "0") {
                Toast.makeText(this, it[0].message, Toast.LENGTH_SHORT).show()
            } else {
                saveData()
                codeClient.postValue(it[0])
            }
        })
        if (quarterDao.getAllQuarters().isEmpty()) {
            GlobalScope.launch(Dispatchers.Main) {
                quarterNetworkDataSource.fetchStreets()
                houseNetworkDataSource.fetchHouses()
                apartmentNetworkDataSource.fetchApartments()
            }
            quarterNetworkDataSource.downloadedStreets.observe(this, Observer { list ->
                val sortedList = list.sortedWith(compareBy { it.name })
                quarterList.postValue(sortedList)
                DataHolder.quarterList = sortedList
                Log.d("quarters", "done")
            })
            houseNetworkDataSource.downloadedHouses.observe(this, Observer { list ->
                val sortedList = list.sortedWith(compareBy { it.name })
                houseList.postValue(sortedList)
                DataHolder.houseList = sortedList
                Log.d("houses", "done")
            })
            apartmentNetworkDataSource.downloadedApartment.observe(this, Observer { list ->
                val sortedList = list.sortedWith(compareBy { it.name })
                apartmentList.postValue(sortedList)
                DataHolder.apartmentList = sortedList
                Log.d("apartments", "done")
                loading.visibility = View.GONE
            })
        } else {
            quarterList.postValue(quarterDao.getAllQuarters())
            DataHolder.quarterList = quarterDao.getAllQuarters()

            houseList.postValue(houseDao.getAllHouses())
            DataHolder.houseList = houseDao.getAllHouses()

            apartmentList.postValue(apartmentDao.getAllApartments())
            DataHolder.apartmentList = apartmentDao.getAllApartments()
        }

        codeClient.observe(this, Observer {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("code", it.code)
            intent.putExtra("photo", it.photo)
            intent.putExtra("name", it.name)
            startActivity(intent)
        })
        userQuarter.onClick {
            if (!quarterList.value.isNullOrEmpty()) {
                quarterDialog = QuarterListDialog(this, this, quarterList.value!!)
                quarterDialog.show()
            }
        }

        userHouse.onClick {
            if (::selectedQuarter.isInitialized) {
                filteredHouseList.value = houseList.value?.filter {
                    it.codeQuarter == selectedQuarter.code
                }
                houseDialog = HouseListDialog(this, this, filteredHouseList.value!!)
                houseDialog.show()
            } else {
                toastLN("Вы еще не выбрали квартал")
            }
        }

        userApartment.onClick {
            if (!apartmentList.value.isNullOrEmpty() && ::selectedHouse.isInitialized) {
                filteredApartmentList.value =
                    apartmentList.value?.filter { it.codeHouse == selectedHouse.code }
                apartmentDialog = ApartmentListDialog(this, this, filteredApartmentList.value!!)
                apartmentDialog.show()
            } else {
                toastLN("Вы еще не выбрали дом")
            }
        }

        tvRegister.onClick {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
        }

        llForgotPassword.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:712767753")
            val s: () -> String = { android.Manifest.permission.CALL_PHONE }
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) startActivity(intent)
            else ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                requestCall
            )
        }

        tvLogin.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                authNetworkDataSource.fetchAuth(
                    selectedHouse.name,
                    selectedQuarter.name,
                    selectedApartment.name,
                    etChooseUserId.text.toString()
                )
            }
        }
    }

    private fun saveData() {
        Preferences.setUserQuarter(selectedQuarter.name)
        Preferences.setUserHouse(selectedHouse.name)
        Preferences.setUserApartment(selectedApartment.name)
        Preferences.setCodeQuarter(selectedQuarter.code)
        Preferences.setCodeHouse(selectedHouse.code)
        Preferences.setCodeApartment(selectedApartment.code)
        Preferences.setUserId(etChooseUserId.text.toString())
        Preferences.setSaveData(rememberLogin.isChecked)
    }

    private fun loadData() {
        if (Preferences.isSaveData()) {
            selectedQuarter = quarterDao.getQuarterByCode(Preferences.getCodeQuarter())
            selectedHouse = houseDao.getHouseByCode(Preferences.getCodeHouse())
            selectedApartment = apartmentDao.getApartmentByCode(Preferences.getCodeApartment())
            tvChooseQuarter.text = getString(R.string.quarter) + "${selectedQuarter.name}"
            tvChooseHouse.text = getString(R.string.house) + "${selectedHouse.name}"
            tvChooseApartment.text = getString(R.string.apartment) + "${selectedApartment.name}"
            etChooseUserId.setText(Preferences.getUserId())
            rememberLogin.isChecked = true
        }
    }

    override fun onPositiveButtonClick(quarter: Quarter) {
        selectedQuarter = quarter

        tvChooseQuarter.text = getString(R.string.quarter) + "${selectedQuarter.name}"
        quarterList.value?.forEach { it.isSelected = false }
        quarterDialog.dismiss()
    }

    override fun onNegativeButtonClick() {
        quarterList.value?.forEach { it.isSelected = false }
        quarterDialog.dismiss()
    }

    override fun onHousePositiveButtonClick(house: House) {
        selectedHouse = house
        tvChooseHouse.text = getString(R.string.house) + "${selectedHouse.name}"
        houseList.value?.forEach { it.isSelected = false }
        houseDialog.dismiss()
    }

    override fun onHouseNegativeButtonClick() {
        houseList.value?.forEach { it.isSelected = false }
        houseDialog.dismiss()
    }

    override fun onApartmentPositiveButtonClick(apartment: Apartment) {
        selectedApartment = apartment
        tvChooseApartment.text = getString(R.string.apartment) + "${selectedApartment.name}"
        apartmentList.value?.forEach { it.isSelected = false }
        apartmentDialog.dismiss()
    }

    override fun onApartmentNegativeButtonClick() {
        apartmentList.value?.forEach { it.isSelected = false }
        apartmentDialog.dismiss()
    }
}