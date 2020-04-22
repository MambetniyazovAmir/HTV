package uz.kashtan.hamkortv.ui.main.login.adapter

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.notification_list_item.view.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.Requests

class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateModel(model: Requests, itemClickListener: OnItemClickListener, position: Int) {
        itemView.titleText.text = model.status
        itemView.descriptionText.text = model.theme+model.theme+model.theme+model.theme
        itemView.dateTxt.text = model.date
        if (!model.clicked) {
            TransitionManager.beginDelayedTransition(itemView.requestContainer, AutoTransition())
            itemView.descriptionText.maxLines = 2
            itemView.descriptionText.ellipsize = TextUtils.TruncateAt.END
            itemView.arrowBtn.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
        } else {
            TransitionManager.beginDelayedTransition(itemView.requestContainer, AutoTransition())
            itemView.descriptionText.maxLines = Int.MAX_VALUE
            itemView.descriptionText.ellipsize = null
            itemView.arrowBtn.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
        }
        itemView.onClick { itemClickListener.onItemClick(position) }
    }
}