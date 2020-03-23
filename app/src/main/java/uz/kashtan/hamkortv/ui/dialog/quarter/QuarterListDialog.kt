package uz.kashtan.hamkortv.ui.dialog.quarter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.dialog_quarter_list.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.Quarter

class QuarterListDialog(private val mContext: Context) : Dialog(mContext) {

    val adapter: QuarterListAdapter = QuarterListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_quarter_list)
        val gridLayoutManager = GridLayoutManager(mContext, 5)
        rvQuarter.layoutManager = gridLayoutManager
        rvQuarter.adapter = adapter
        setData()
    }

    private fun setData() {
        val list: MutableList<Quarter> = arrayListOf()
        for(i in 1..11) {
            val quarter = Quarter(i, "$i${'A' + (i-1)%28}", "code")
            list.add(quarter)
        }
        adapter.setData(list)
    }

}