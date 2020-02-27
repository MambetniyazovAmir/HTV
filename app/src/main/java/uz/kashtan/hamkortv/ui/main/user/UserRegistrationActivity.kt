package uz.kashtan.hamkortv.ui.main.user

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_user_registration.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.ui.dialog.AddressDialog
import uz.kashtan.hamkortv.ui.main.login.LoginActivity

class UserRegistrationActivity: BaseActivity(){
    override val layoutResource: Int
        get() = R.layout.activity_user_registration

    private var codeClient: MutableLiveData<AuthModel> = MutableLiveData()

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialog = AddressDialog(this, codeClient, this)
        userAddress.setOnClickListener {
            dialog.show()
        }
        dialog.setOnDismissListener {
            codeClient.observe(this, Observer {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("code", it.code)
                startActivity(intent)
            })
        }
        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("code", "100027099")
            startActivity(intent)
        }
    }
}