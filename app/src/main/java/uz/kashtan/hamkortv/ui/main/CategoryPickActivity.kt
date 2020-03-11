package uz.kashtan.hamkortv.ui.main

import android.content.Intent
import android.os.Bundle
import com.app.basemodule.extensions.onClick
import kotlinx.android.synthetic.main.activity_main.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.data.pref.Preferences
import uz.kashtan.hamkortv.ui.intro.IntroActivity
import uz.kashtan.hamkortv.ui.main.agrum.AgrumentActivity
import uz.kashtan.hamkortv.ui.main.area.AreaActivity
import uz.kashtan.hamkortv.ui.main.channels.ChannelsListActivity
import uz.kashtan.hamkortv.ui.main.complaint.ComplaintActivity
import uz.kashtan.hamkortv.ui.main.contacts.ContactsActivity
import uz.kashtan.hamkortv.ui.main.licenses.LicensesActivity
import uz.kashtan.hamkortv.ui.main.ourquality.OurQualityActivity
import uz.kashtan.hamkortv.ui.main.user.UserRegistrationActivity
import uz.kashtan.hamkortv.utils.AnimationTemplateUtils

class CategoryPickActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun init(savedInstanceState: Bundle?) {
        if(Preferences.firstOpened){
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
            return
        }
        AnimationTemplateUtils.animateStepByStepVisible(arrayOf(
            llPersonal,
            llContants,
            llLicenses,
            llRate,
            llUserAgr,
            llChannels,
            llOurQuality,
            llArea,
            llWifiFree,
            llLanguage
        ))

        llPersonal.onClick {
            startActivity(Intent(this, UserRegistrationActivity::class.java))
        }

        llContants.onClick {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        llLicenses.onClick {
            startActivity(Intent(this, LicensesActivity::class.java))
        }

        llUserAgr.onClick {
            startActivity(Intent(this, AgrumentActivity::class.java))
        }

        llChannels.onClick {
            startActivity(Intent(this, ChannelsListActivity::class.java))
        }

        llOurQuality.onClick {
            startActivity(Intent(this, OurQualityActivity::class.java))
        }

        llLanguage.onClick {
            startActivity(Intent(this, IntroActivity::class.java))
        }

        llArea.onClick {
            startActivity(Intent(this, AreaActivity::class.java))
        }

//        llComplaints.onClick {
//            startActivity(Intent(this, ComplaintActivity::class.java))
//        }
    }


}
