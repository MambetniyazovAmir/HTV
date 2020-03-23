package uz.kashtan.hamkortv.ui.dialog.quarter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.Quarter
import uz.xsoft.lesson23pdp13.utils.inflate

class QuarterListAdapter : RecyclerView.Adapter<QuarterViewHolder>(), QuarterSelectedListener {

    var selectedItemPosition: Int = -1
    var models: List<Quarter> = arrayListOf()

    fun setData(models: List<Quarter>) {
        this.models = models
        println(models)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuarterViewHolder {
        val view = parent.inflate(R.layout.item_quarter_list)
        return QuarterViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: QuarterViewHolder, position: Int) {
        holder.populateModel(models[position], position, this)
    }

    override fun quarterSelected(itemPosition: Int) {
        if (selectedItemPosition != -1) {
            notifyItemChanged(selectedItemPosition)
            models[selectedItemPosition].isSelected = false
        }
        selectedItemPosition = itemPosition
        models[selectedItemPosition].isSelected = true
        notifyItemChanged(selectedItemPosition)
    }
}
