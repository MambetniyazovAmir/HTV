package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.LoginModel

class HistoryAdapter: RecyclerView.Adapter<HistoryItemViewHolder>(){
    private var  models : List<LoginModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    fun setData(models: List<LoginModel>){
        this.models = models
        notifyDataSetChanged()
    }
}