package uz.kashtan.hamkortv.ui.main.ourquality

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class OurQualityActivity: BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_our_quality

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.quality)
        enableToolbarBackButton()
    }
}