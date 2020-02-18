package uz.kashtan.hamkortv.ui.main.area

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class AreaActivity: BaseActivity(){
    override val layoutResource: Int
        get() = R.layout.activity_area

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.coverage_area)
        enableToolbarBackButton()
    }
}