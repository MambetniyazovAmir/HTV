package uz.kashtan.hamkortv.ui.main.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.HistoryOfPaymentsModel

class LoginAdapter : RecyclerView.Adapter<LoginItemViewHolder>() {

    var models: List<HistoryOfPaymentsModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return LoginItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: LoginItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    fun setData(models: List<HistoryOfPaymentsModel>) {
        this.models = models
        notifyDataSetChanged()
    }
}