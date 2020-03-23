package uz.kashtan.hamkortv.ui.main.register

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterListAdapter
import uz.kashtan.hamkortv.ui.dialog.quarter.QuarterListDialog

class RegisterActivity : BaseActivity() {

    private lateinit var quarterDialog: QuarterListDialog

    override val layoutResource: Int
        get() = R.layout.activity_register

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quarterDialog = QuarterListDialog(this)
        userQuarter.setOnClickListener {
            quarterDialog.show()
        }
    }
}
