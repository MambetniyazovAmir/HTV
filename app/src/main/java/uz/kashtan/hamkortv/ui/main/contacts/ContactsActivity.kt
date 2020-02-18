package uz.kashtan.hamkortv.ui.main.contacts

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class ContactsActivity: BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_contacts

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.contacts)
        enableToolbarBackButton()
    }
}