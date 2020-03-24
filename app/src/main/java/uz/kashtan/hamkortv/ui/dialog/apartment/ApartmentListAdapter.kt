package uz.kashtan.hamkortv.ui.dialog.apartment

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.Apartment
import uz.kashtan.hamkortv.room.models.House
import uz.xsoft.lesson23pdp13.utils.inflate

class ApartmentListAdapter(private val itemSelectedListener: ApartmentDialogItemClickListener) : RecyclerView.Adapter<ApartmentViewHolder>(), ApartmentDialogItemClickListener {

    private var selectedItemPosition: Int = -1
    var models: List<Apartment> = arrayListOf()

    fun setData(models: List<Apartment>) {
        this.models = models
        println(models)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val view = parent.inflate(R.layout.item_quarter_list)
        return ApartmentViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        holder.populateModel(models[position], position, this)
    }

    override fun onItemClick(item: Apartment, itemPosition: Int) {
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
