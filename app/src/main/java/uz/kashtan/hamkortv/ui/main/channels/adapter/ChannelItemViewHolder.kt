package uz.kashtan.hamkortv.ui.main.channels.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_channels.view.*
import uz.kashtan.hamkortv.room.models.ChannelsModel

class ChannelItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    fun populateModel(model: ChannelsModel){
        itemView.channel.text = model.name
    }
}