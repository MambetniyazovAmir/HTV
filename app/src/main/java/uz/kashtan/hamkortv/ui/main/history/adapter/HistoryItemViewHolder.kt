package uz.kashtan.hamkortv.ui.main.history.adapter

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.enabled
import kotlinx.android.synthetic.main.item_history.view.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.LoginModel

class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: LoginModel, itemClickListener: ItemClickListener) {
        itemView.date.text = model.dateOfTime
        itemView.month.text = model.month
        if (model.color == "Orange") {
            itemView.payBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.orange))
        }
        if (model.color == "Red") {
            itemView.payBtn.text = itemView.context.getString(R.string.notPaid)
            itemView.payBtn.setTextColor(Color.RED)
        }
        if (model.color == "Green") {
            itemView.payBtn.text = itemView.context.getString(R.string.paid)
            itemView.isEnabled = false
            itemView.payBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
        }
        itemView.value_text.text = model.sum
        itemView.setOnClickListener {
            itemClickListener.onItemClick(model)
        }
    }
}