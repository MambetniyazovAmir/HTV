package uz.kashtan.hamkortv.ui.main.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_notification.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.NotificationModel
import uz.kashtan.hamkortv.ui.main.login.adapter.NotificationAdapter
import uz.kashtan.hamkortv.utils.MarginItemDecoration

class NotificationFragment: Fragment(R.layout.fragment_notification) {

    private val adapter = NotificationAdapter()

    companion object {
        const val TAG = "NotificationFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.adapter = adapter
        list.addItemDecoration(MarginItemDecoration(8))
        adapter.setData(getData())
        super.onViewCreated(view, savedInstanceState)
    }
    private fun getData(): MutableList<NotificationModel> {
        val list: MutableList<NotificationModel> = arrayListOf()
        for (item in 1..10){
            list.add(NotificationModel("Заявка принята", "Отправьте мастера", "17.04.2020"))
        }
        return list
    }
}