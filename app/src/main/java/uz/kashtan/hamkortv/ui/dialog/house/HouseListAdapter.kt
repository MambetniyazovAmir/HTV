package uz.kashtan.hamkortv.ui.dialog.house

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.House
import uz.xsoft.lesson23pdp13.utils.inflate

class HouseListAdapter(private val itemSelectedListener: HouseDialogItemClickListener) : RecyclerView.Adapter<HouseViewHolder>(), HouseDialogItemClickListener {

    private var selectedItemPosition: Int = -1
    var models: List<House> = arrayListOf()

    fun setData(models: List<House>) {
        this.models = models
        println(models)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val view = parent.inflate(R.layout.item_quarter_list)
        return HouseViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        holder.populateModel(models[position], position, this)
    }

    override fun onItemClick(item: House, itemPosition: Int) {
        if (selectedItemPosition != -1) {
            notifyItemChanged(selectedItemPosition)
            models[selectedItemPosition].isSelected = false
        }
        selectedItemPosition = itemPosition
        models[selectedItemPosition].isSelected = true
        notifyItemChanged(selectedItemPosition)
        itemSelectedListener.onItemClick(item, itemPosition)
    }
}
