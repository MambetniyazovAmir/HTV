package uz.kashtan.hamkortv.ui.main.history.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import uz.kashtan.hamkortv.room.models.LoginModel

class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: LoginModel) {
        itemView.date.text = model.dateOfTime
        itemView.month.text = model.month
        if (model.color == "Black") {
            itemView.month.setTextColor(Color.BLACK)
        }
        if (model.color == "Red") {
            itemView.month.setTextColor(Color.RED)
        }
        if (model.color == "Green") {
            itemView.month.setTextColor(Color.GREEN)
        }
        itemView.value_text.text = model.sum
    }
}