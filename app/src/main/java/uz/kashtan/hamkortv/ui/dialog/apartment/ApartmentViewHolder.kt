package uz.kashtan.hamkortv.ui.dialog.apartment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quarter_list.view.*
import uz.kashtan.hamkortv.room.models.Apartment

class ApartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: Apartment, position: Int, selectListener: ApartmentDialogItemClickListener) {
        itemView.quarter.text = model.name
        if (model.isSelected) {
            itemView.outline.visibility = View.VISIBLE
        } else {
            itemView.outline.visibility = View.GONE
        }
        itemView.setOnClickListener {
            selectListener.onItemClick(model, position)
        }
    }
}