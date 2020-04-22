package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.Requests
import uz.xsoft.lesson23pdp13.utils.inflate

class NotificationAdapter :
    RecyclerView.Adapter<NotificationViewHolder>(), OnItemClickListener {

    private var models: List<Requests> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = parent.inflate(R.layout.notification_list_item)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int = models.size


    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.populateModel(models[position], this, position)
    }

    fun setData(models: List<Requests>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        models[position].clicked = !models[position].clicked
        notifyItemChanged(position)
    }
}