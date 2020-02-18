package uz.kashtan.hamkortv.ui.intro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.activity_intro.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.data.pref.Preferences
import uz.kashtan.hamkortv.ui.main.CategoryPickActivity
import uz.kashtan.hamkortv.utils.AnimationTemplateUtils
import uz.kashtan.hamkortv.utils.LocaleManager

class IntroActivity: BaseActivity(){
    override val layoutResource: Int
        get() = R.layout.activity_intro

    override fun init(savedInstanceState: Bundle?) {
        AnimationTemplateUtils.animateStepByStepVisible(
           arrayOf(
               tvApplicationName,
               ivLogo,
               tvAppDiscription,
               tvPickLanguage,
               tvRus,
               tvUzbek,
               tvNext
           )
        )

        if(LocaleManager.getLanguage(this) == LocaleManager.LANGUAGE_RUSSIAN){
            tvRus.setTextColor(Color.parseColor("#212121"))
            tvUzbek.setTextColor(Color.parseColor("#99212121"))
            tvUzbek.onClick {
                LocaleManager.setNewLocale(this, LocaleManager.LANGUAGE_UZBEK)
                refreshActivity()
            }
        }else{
            tvRus.setTextColor(Color.parseColor("#99212121"))
            tvUzbek.setTextColor(Color.parseColor("#212121"))
            tvRus.onClick {
                LocaleManager.setNewLocale(this, LocaleManager.LANGUAGE_RUSSIAN)
                refreshActivity()
            }
        }

        tvNext.onClick {
            Preferences.firstOpened = false
            startActivity(Intent(this, CategoryPickActivity::class.java))
            finish()
        }
    }
    private fun refreshActivity(){
        val intent = intent
        finish()
        startActivity(intent)
    }
}