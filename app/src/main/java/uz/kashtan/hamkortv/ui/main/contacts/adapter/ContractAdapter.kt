package uz.kashtan.hamkortv.ui.main.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R

class ContractAdapter : RecyclerView.Adapter<ContractViewHolder>() {

    private var models: List<ContractModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ContractViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    fun setData(models: List<ContractModel>) {
        this.models = models
    }
}