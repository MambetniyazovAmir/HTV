package uz.kashtan.hamkortv.ui.main.agrum

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class AgrumentActivity: BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_agrument

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.user_arg)
        enableToolbarBackButton()
    }
}