package uz.kashtan.hamkortv.ui.main.user

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity



class UserRegistrationActivity: BaseActivity(){
    override val layoutResource: Int
        get() = R.layout.activity_user_registration

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
    }


}