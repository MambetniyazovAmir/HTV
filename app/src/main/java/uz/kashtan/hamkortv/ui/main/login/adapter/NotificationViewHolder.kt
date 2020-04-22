package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.notification_list_item.view.*
import uz.kashtan.hamkortv.room.models.NotificationModel
import uz.kashtan.hamkortv.room.models.Requests

class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: Requests, itemClickListener: OnItemClickListener){
        itemView.titleText.text = model.status
        itemView.descriptionText.text = model.theme
        itemView.dateTxt.text = model.date
        itemView.onClick { itemClickListener.onItemClick(model.requestId) }
    }
}