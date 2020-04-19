package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.NotificationModel
import uz.xsoft.lesson23pdp13.utils.inflate

class NotificationAdapter: RecyclerView.Adapter<NotificationViewHolder>() {

    private var models: List<NotificationModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val  view = parent.inflate(R.layout.notification_list_item)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    fun setData(models: List<NotificationModel>){
        this.models = models
    }
}