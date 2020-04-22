package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.NotificationModel
import uz.kashtan.hamkortv.room.models.Requests
import uz.xsoft.lesson23pdp13.utils.inflate

class NotificationAdapter(private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<NotificationViewHolder>() {

    private var models: List<Requests> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val  view = parent.inflate(R.layout.notification_list_item)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.populateModel(models[position], itemClickListener)
    }

    fun setData(models: List<Requests>){
          this.models = models
    }
}