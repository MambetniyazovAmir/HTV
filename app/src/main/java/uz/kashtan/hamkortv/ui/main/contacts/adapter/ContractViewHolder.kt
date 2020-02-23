package uz.kashtan.hamkortv.ui.main.contacts.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class ContractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateModel(model: ContractModel?) {
        if (model == null) {
            itemView.date.text = "123"
            itemView.description_text.text = "123"
        }
    }
}