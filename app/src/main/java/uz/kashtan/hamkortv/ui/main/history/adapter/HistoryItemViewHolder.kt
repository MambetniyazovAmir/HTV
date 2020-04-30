package uz.kashtan.hamkortv.ui.main.history.adapter

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.LoginModel

class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: LoginModel) {
        itemView.date.text = model.dateOfTime
        itemView.month.text = model.month
        if (model.color == "Orange") {
            itemView.payBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.orange))
        }
        if (model.color == "Red") {
            itemView.payBtn.setTextColor(Color.RED)
        }
        if (model.color == "Green") {
            itemView.payBtn.text = "Оплачено"
            itemView.payBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
        }
        itemView.value_text.text = model.sum
    }
}