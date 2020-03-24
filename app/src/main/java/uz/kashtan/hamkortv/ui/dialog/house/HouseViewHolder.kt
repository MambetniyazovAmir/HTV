package uz.kashtan.hamkortv.ui.dialog.house

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quarter_list.view.*
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.Quarter
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterDialogItemClickListener

class HouseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: House, position: Int, selectListener: HouseDialogItemClickListener) {
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