package uz.kashtan.hamkortv.ui.main.login.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*
import uz.kashtan.hamkortv.room.models.HistoryOfPaymentsModel

class LoginItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun populateModel(model: HistoryOfPaymentsModel) {
        val date = itemView.date.text.toString()
        val month = itemView.month.text.toString()
        val payment = itemView.description_text.text.toString()
        itemView.month.text = month+" "+model.month
        itemView.date.text = date+" "+model.dateOfPayment
        itemView.description_text.text = payment+" "+model.payment.toString()
    }
}