package uz.kashtan.hamkortv.ui.dialog.house

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.dialog_house_list.*
import kotlinx.android.synthetic.main.dialog_quarter_list.*
import kotlinx.android.synthetic.main.dialog_quarter_list.negativeButton
import kotlinx.android.synthetic.main.dialog_quarter_list.positiveButton
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.House

class HouseListDialog(
    private val mContext: Context,
    private val houseButtonClickListener: HouseDialogButtonClickListener,
    private val list: List<House>
) :
    Dialog(mContext), HouseDialogItemClickListener {

    val adapter: HouseListAdapter = HouseListAdapter(this)
    private lateinit var selectedItem: House
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_house_list)
        val gridLayoutManager = GridLayoutManager(mContext, 5)
        rvHouse.layoutManager = gridLayoutManager
        rvHouse.adapter = adapter
        setData()

        positiveButton.onClick {
            if (::selectedItem.isInitialized) {
                houseButtonClickListener.onHousePositiveButtonClick(selectedItem)
            }
        }

        negativeButton.onClick {
            houseButtonClickListener.onHouseNegativeButtonClick()
        }
    }

    private fun setData() {
        adapter.setData(list)
    }

    override fun onItemClick(item: House, itemPosition: Int) {
        selectedItem = item
    }
}