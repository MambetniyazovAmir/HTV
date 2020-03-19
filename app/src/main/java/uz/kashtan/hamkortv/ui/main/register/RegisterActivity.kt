package uz.kashtan.hamkortv.ui.main.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class RegisterActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_register

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
