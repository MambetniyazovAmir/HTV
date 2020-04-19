package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notification_list_item.view.*
import uz.kashtan.hamkortv.room.models.NotificationModel

class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: NotificationModel){
        itemView.titleText.text = model.title
        itemView.descriptionText.text = model.description
        itemView.dateTxt.text = model.date
    }
}