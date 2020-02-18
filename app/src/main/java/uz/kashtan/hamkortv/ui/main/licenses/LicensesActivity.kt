package uz.kashtan.hamkortv.ui.main.licenses

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class LicensesActivity: BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_licenses

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.license)
        enableToolbarBackButton()
    }
}