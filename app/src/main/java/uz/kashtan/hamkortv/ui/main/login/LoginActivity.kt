package uz.kashtan.hamkortv.ui.main.login

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_login.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.room.models.HistoryOfPaymentsModel
import uz.kashtan.hamkortv.ui.main.login.adapter.LoginAdapter

class LoginActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_login

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.login)
        enableToolbarBackButton()
    }

    var code = ""
    var year = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        code = intent.getStringExtra("code")
        year = intent.getStringExtra("year")
        AsyncTaskEx().execute()
    }

    companion object {
        private const val METHOD_NAME = "CreditStories"

        private const val URL = "http://31.135.214.47/FileWebTest/ws/ws1.1cws"

        private const val NAMESPACE = "http://www.sample-package.org"

        private const val SOAP_ACTION = "${NAMESPACE}#Mobile:$METHOD_NAME"
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskEx :
        AsyncTask<List<HistoryOfPaymentsModel>, List<HistoryOfPaymentsModel>, List<HistoryOfPaymentsModel>>() {
        override fun doInBackground(vararg params: List<HistoryOfPaymentsModel>?): List<HistoryOfPaymentsModel> {
            val request = SoapObject(NAMESPACE, METHOD_NAME)
            request.addProperty("CodeClient", code)
            request.addProperty("Year", year)
            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)
            envelope.dotNet = true
            envelope.setOutputSoapObject(request)
            val httpTransportSE = HttpTransportSE(URL)
            try {
                httpTransportSE.call(SOAP_ACTION, envelope)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return extractDataFromXmlResponse(envelope)
        }

        override fun onPostExecute(result: List<HistoryOfPaymentsModel>) {
            super.onPostExecute(result)
            val adapter = LoginAdapter()
            adapter.setData(result)
            historyOfPaymentsList.adapter = adapter
            historyOfPaymentsList.addItemDecoration(
                DividerItemDecoration(
                    applicationContext,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun extractDataFromXmlResponse(envelope: SoapSerializationEnvelope): List<HistoryOfPaymentsModel> {
        val soapObject = envelope.response as? SoapObject
        val historyOfPaymentsList: MutableList<HistoryOfPaymentsModel> = arrayListOf()
        val size = soapObject.propertyCount
        for (item in 0 until size) {
            val refusal = soapObject?.getProperty(item) as SoapObject
            val model = HistoryOfPaymentsModel(
                item + 1,
                refusal.getProperty("Month").toString(),
                refusal.getProperty("DateOfTime").toString(),
                refusal.getProperty("Sum").toString().toInt()
            )
            historyOfPaymentsList.add(model)
        }
        return historyOfPaymentsList
    }

}
