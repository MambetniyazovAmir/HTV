package uz.kashtan.hamkortv.ui.main.channels.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.channels_item.view.*
import uz.kashtan.hamkortv.room.models.ChannelModel

class ChannelItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: ChannelModel) {
        itemView.channelName.text = model.channelName
        itemView.channelNumber.text = model.id.toString()
    }
}