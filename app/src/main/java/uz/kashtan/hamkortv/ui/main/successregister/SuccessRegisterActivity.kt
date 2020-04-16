package uz.kashtan.hamkortv.ui.main.successregister

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.activity_success_register.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class SuccessRegisterActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_success_register

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.registration)
        enableToolbarBackButton()
        val userId = intent.getStringExtra("userId")
        val totalSum = intent.getIntExtra("totalSum", 0)
        successRegisterText.text = "Регистрация прошла успешно\n ваш ID $userId для входа в личный кабинет \n Для активации аккаунта оплатите сумму $totalSum"
        tvPay.onClick {
            try {
                val pm: PackageManager = this.packageManager
                val launchIntent = pm.getLaunchIntentForPackage("uz.dida.payme")
                launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(launchIntent)
            } catch (e: Exception){
                val openUrl = Intent(android.content.Intent.ACTION_VIEW)
                openUrl.data = Uri.parse("https://payme.uz")
                startActivity(openUrl)
            }
        }
    }
}
