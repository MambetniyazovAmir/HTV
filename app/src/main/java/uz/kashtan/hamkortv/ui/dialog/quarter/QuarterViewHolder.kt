package uz.kashtan.hamkortv.ui.dialog.quarter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quarter_list.view.*
import uz.kashtan.hamkortv.room.models.Quarter

class QuarterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: Quarter, position: Int, selectListener: QuarterSelectedListener) {
        itemView.quarter.text = model.name
        if (model.isSelected) {
            itemView.outline.visibility = View.VISIBLE
        } else {
            itemView.outline.visibility = View.GONE
        }
        itemView.setOnClickListener {
            selectListener.quarterSelected(position)
        }
    }
}