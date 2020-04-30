package uz.kashtan.hamkortv.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.app.basemodule.extensions.onClick
import com.app.basemodule.extensions.toastLN
import com.stepstone.apprating.AppRatingDialog
import com.stepstone.apprating.listener.RatingDialogListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.data.pref.Preferences
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ConnectivityInterceptorImpl
import uz.kashtan.hamkortv.retrofit.network.SetRatingImpl
import uz.kashtan.hamkortv.room.models.RatingModel
import uz.kashtan.hamkortv.ui.main.complaint.ComplaintActivity
import uz.kashtan.hamkortv.ui.main.history.HistoryActivity
import uz.kashtan.hamkortv.ui.main.login.notification.NotificationActivity
import uz.kashtan.hamkortv.ui.main.offer.OfferActivity
import uz.kashtan.hamkortv.utils.AnimationTemplateUtils
import java.util.*


class LoginActivity : BaseActivity(), RatingDialogListener {

    private lateinit var setRating: SetRatingImpl
    private lateinit var apiService: ApiService

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

        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
        setRating = SetRatingImpl(apiService)

        val id = intent.getStringExtra("id")
        val code = intent.getStringExtra("code")
        val name = intent.getStringExtra("name")
        val photo = intent.getStringExtra("photo")
        llPayments.onClick {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("code", id)
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
        setRating.ratingResponse.observe(this, androidx.lifecycle.Observer {
            Preferences.setRequestDone(false)
            toastLN("Спасибо за оценку")
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Preferences.getRequestDone()) {
            showDialog()
        }
    }

    private fun showDialog() {
        AppRatingDialog.Builder()
            .setPositiveButtonText("Оценить")
            .setNegativeButtonText("Отмена")
            .setNeutralButtonText("Позже")
            .setNoteDescriptions(
                Arrays.asList(
                    "Очень плохо",
                    "Плохо",
                    "Хорошо",
                    "Очень хорошо",
                    "Отлично"
                )
            )
            .setDefaultRating(3)
            .setTitle("Оцените работу сотдрудника")
            .setDescription("Пожалуйста, выберите несколько звездочек и оставьте свой отзыв")
            .setCommentInputEnabled(false)
            //.setDefaultComment("На заявку отреагировали очень быстро")
            .setStarColor(R.color.colorAccent)
            .setNoteDescriptionTextColor(R.color.colorAccent)
            .setTitleTextColor(R.color.colorBlack)
            .setDescriptionTextColor(R.color.colorPrimaryDark)
            .setHint("Пожалуйста, напишите свой комментарий здесь ...")
            .setHintTextColor(R.color.hintTextColor)
            //.setCommentTextColor(R.color.blackish)
            //.setCommentBackgroundColor(R.color.commentBackgroundColor)
            .setWindowAnimation(R.style.MyDialogFadeAnimation)
            .setCancelable(false)
            .setCanceledOnTouchOutside(false)
            .create(this)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.notification -> {
                startActivity(Intent(this, NotificationActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNegativeButtonClicked() {
        Preferences.setRequestDone(false)
    }

    override fun onNeutralButtonClicked() {
        Preferences.setRequestDone(false)
    }

    override fun onPositiveButtonClicked(rate: Int, comment: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val requestId = Preferences.getLastRequestId()
            val ratingModel = RatingModel(requestId, rate)
            setRating.fetchRating(ratingModel)
        }
    }
}