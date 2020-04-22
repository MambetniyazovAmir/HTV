package uz.kashtan.hamkortv.ui.main.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.notification_list_item.*
import uz.kashtan.hamkortv.HamkorTvApp
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.HTVDatabase
import uz.kashtan.hamkortv.room.dao.RequestsDao
import uz.kashtan.hamkortv.room.models.Requests
import uz.kashtan.hamkortv.ui.main.login.adapter.NotificationAdapter
import uz.kashtan.hamkortv.ui.main.login.adapter.OnItemClickListener
import uz.kashtan.hamkortv.utils.MarginItemDecoration

class NotificationFragment : Fragment(R.layout.fragment_notification){

//    private val adapter = NotificationAdapter(this)
//    private lateinit var requestsDao: RequestsDao

    companion object {
        const val TAG = "NotificationFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        list.adapter = adapter
//        list.addItemDecoration(MarginItemDecoration(8))
//        requestsDao = HTVDatabase.invoke(HamkorTvApp().applicationContext).requestsDao()
//        val list = requestsDao.getRequests()
//        adapter.setData(list)
        super.onViewCreated(view, savedInstanceState)
    }
//
//    override fun onItemClick(model: Requests) {
//        if (!model.clicked) {
//            TransitionManager.beginDelayedTransition(requestContainer, AutoTransition())
//            descriptionText.maxLines = Int.MAX_VALUE
//            descriptionText.ellipsize = null
//            descriptionText.text = model.theme
//            arrowBtn.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
//            model.clicked = true
//            requestsDao.setClicked(model)
//        } else {
//            TransitionManager.beginDelayedTransition(requestContainer, AutoTransition())
//            descriptionText.maxLines = 2
//            descriptionText.ellipsize = TextUtils.TruncateAt.END
//            descriptionText.text = model.theme
//            arrowBtn.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
//            model.clicked = false
//            requestsDao.setClicked(model)
//        }
//    }
}