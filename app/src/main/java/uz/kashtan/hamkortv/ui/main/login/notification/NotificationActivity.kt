package uz.kashtan.hamkortv.ui.main.login.notification

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.room.HTVDatabase
import uz.kashtan.hamkortv.room.dao.RequestsDao
import uz.kashtan.hamkortv.ui.main.login.adapter.NotificationAdapter
import uz.kashtan.hamkortv.utils.MarginItemDecoration

class NotificationActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_notification

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.notifications)
        enableToolbarBackButton()
    }

    private val adapter = NotificationAdapter()
    private lateinit var requestsDao: RequestsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        list.adapter = adapter
        list.addItemDecoration(MarginItemDecoration(8))
        requestsDao = HTVDatabase.invoke(this).requestsDao()
        val list = requestsDao.getRequests()
        adapter.setData(list)
    }
}
