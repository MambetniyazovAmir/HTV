package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.room.models.LoginModel

class HistoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun populateModel(model: LoginModel){
        itemView.date.text = model.dateOfTime
        itemView.month.text = model.month
        itemView.value_text.text = model.sum.toString()
    }
}