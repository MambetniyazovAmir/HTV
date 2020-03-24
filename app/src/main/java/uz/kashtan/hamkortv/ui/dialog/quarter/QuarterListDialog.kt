package uz.kashtan.hamkortv.ui.dialog.quarter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.dialog_quarter_list.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.Quarter

class QuarterListDialog(private val mContext: Context, private val quarterButtonClickListener: QuarterDialogButtonClickListener, private val list: List<Quarter>) :
    Dialog(mContext), QuarterDialogItemClickListener {

    val adapter: QuarterListAdapter = QuarterListAdapter(this)
    private lateinit var selectedItem: Quarter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_quarter_list)
        val gridLayoutManager = GridLayoutManager(mContext, 5)
        rvQuarter.layoutManager = gridLayoutManager
        rvQuarter.adapter = adapter
        setData()

        positiveButton.onClick {
            if (::selectedItem.isInitialized) {
                quarterButtonClickListener.onPositiveButtonClick(selectedItem)
            }
        }

        negativeButton.onClick {
            quarterButtonClickListener.onNegativeButtonClick()
        }
    }

    private fun setData() {
        adapter.setData(list)
    }

    override fun onItemClick(item: Quarter, itemPosition: Int) {
        selectedItem = item
    }
}