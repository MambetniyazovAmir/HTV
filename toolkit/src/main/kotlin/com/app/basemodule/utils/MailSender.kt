package com.app.basemodule.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import com.app.basemodule.R
import com.app.basemodule.extensions.message
import com.app.basemodule.extensions.okButton
import com.app.basemodule.extensions.showAlert
import com.app.basemodule.extensions.title

object MailSender {
    fun sendMail(activity: Activity, params: Params) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = android.net.Uri.parse("mailto:${params.mailtoAddress}")

            params.extraSubject?.let {
                putExtra(Intent.EXTRA_SUBJECT, params.extraSubject)
            }

            params.body?.let {
                putExtra(Intent.EXTRA_TEXT, createEmailBodyText())
            }
        }

        try {
            activity.startActivity(Intent.createChooser(intent, ""))
        } catch (e: ActivityNotFoundException) {
            activity.showAlert {
                title(activity.getString(R.string.error_header))
                message(activity.getString(R.string.mailto_not_found))
                okButton { }
            }
        }
    }

    private fun createEmailBodyText(): String {
//    val buffer = StringBuffer()
//    with(buffer) {
//      val osVersion = android.os.Build.VERSION.RELEASE
//      val phoneModel = utils.getDeviceName()
//      val appVersion = BuildConfig.VERSION_NAME
//
//      for(i in 0..5) { append("\n") }
//      append(context.getString(R.string.mail_os_version, osVersion))
//      append("\n")
//      append(context.getString(R.string.mail_model, phoneModel))
//      append("\n")
//      append(context.getString(R.string.mail_app_version, appVersion))
//    }
        return TODO() /*buffer.toString()*/
    }

    class Params(val mailtoAddress: String, val extraSubject: String? = null, val body: String? = null)
}