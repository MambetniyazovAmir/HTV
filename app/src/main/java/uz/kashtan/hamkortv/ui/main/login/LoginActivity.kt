package uz.kashtan.hamkortv.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.app.basemodule.extensions.onClick
import com.app.basemodule.extensions.toastLN
import kotlinx.android.synthetic.main.activity_login.*
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.ui.main.complaint.ComplaintActivity
import uz.kashtan.hamkortv.ui.main.history.HistoryActivity
import uz.kashtan.hamkortv.ui.main.offer.OfferActivity
import uz.kashtan.hamkortv.utils.AnimationTemplateUtils

class LoginActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_login

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.user_page)
        AnimationTemplateUtils.animateStepByStepVisible(
            arrayOf(
                llPayments,
                llRequest,
                llComplaints
            )
        )
        enableToolbarBackButton()
        val code = intent.getStringExtra("code")
        val name = intent.getStringExtra("name")
        val photo = intent.getStringExtra("photo")
        llPayments.onClick {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("code", code)
            startActivity(intent)
        }
        llComplaints.onClick {
            val intent = Intent(this, ComplaintActivity::class.java)
            intent.putExtra("code", code)
            startActivity(intent)
        }
        llRequest.onClick {
            val intent = Intent(this, OfferActivity::class.java)
            intent.putExtra("code", code)
            intent.putExtra("name", name)
            intent.putExtra("photo", photo)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.notification -> {
                changeFragment(NotificationFragment(), NotificationFragment.TAG)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}