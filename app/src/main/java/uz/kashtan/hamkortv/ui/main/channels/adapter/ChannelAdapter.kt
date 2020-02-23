package uz.kashtan.hamkortv.ui.main.channels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.ChannelModel

class ChannelAdapter : RecyclerView.Adapter<ChannelItemViewHolder>() {

    private var models: List<ChannelModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.channels_item, parent, false)
        return ChannelItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ChannelItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    fun setData(models: List<ChannelModel>) {
        this.models = models
        notifyDataSetChanged()
    }
}