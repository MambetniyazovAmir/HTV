package uz.kashtan.hamkortv.ui.main.channels

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class ChannelsListActivity:BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_channels

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.accessable_chanel)
        enableToolbarBackButton()
    }
}