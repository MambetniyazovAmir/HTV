package uz.kashtan.hamkortv.ui.dialog.apartment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.dialog_apartment_list.*
import kotlinx.android.synthetic.main.dialog_quarter_list.*
import kotlinx.android.synthetic.main.dialog_quarter_list.negativeButton
import kotlinx.android.synthetic.main.dialog_quarter_list.positiveButton
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.room.models.Apartment

class ApartmentListDialog(
    private val mContext: Context,
    private val apartmentButtonClickListener: ApartmentDialogButtonClickListener,
    private val list: List<Apartment>
) :
    Dialog(mContext), ApartmentDialogItemClickListener {

    val adapter: ApartmentListAdapter = ApartmentListAdapter(this)
    private lateinit var selectedItem: Apartment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_apartment_list)
        val gridLayoutManager = GridLayoutManager(mContext, 5)
        rvApartment.layoutManager = gridLayoutManager
        rvApartment.adapter = adapter
        setData()

        positiveButton.onClick {
            if (::selectedItem.isInitialized) {
                apartmentButtonClickListener.onApartmentPositiveButtonClick(selectedItem)
            }
        }

        negativeButton.onClick {
            apartmentButtonClickListener.onApartmentNegativeButtonClick()
        }
    }

    private fun setData() {
        adapter.setData(list)
    }

    override fun onItemClick(item: Apartment, itemPosition: Int) {
        selectedItem = item
    }
}