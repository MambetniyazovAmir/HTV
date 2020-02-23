package uz.kashtan.hamkortv.ui.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_address.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.ui.main.login.LoginActivity
import uz.kashtan.hamkortv.utils.LiveEvent
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AddressDialog(context: Context, private var codeClient: MutableLiveData<String>) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_address)
        okBtn.setOnClickListener {
            AsyncTaskEx().execute()
        }
        cancelBtn.setOnClickListener {
            dismiss()
        }
    }

    var code: String = ""

    companion object {
        private const val METHOD_NAME = "Auth"

        private const val URL = "http://31.135.214.47/FileWebTest/ws/ws1.1cws"

        private const val NAMESPACE = "http://www.sample-package.org"

        private const val SOAP_ACTION = "${NAMESPACE}#Mobile:$METHOD_NAME"
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskEx : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            val request = SoapObject(NAMESPACE, METHOD_NAME)
            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)
            request.addProperty("Kvartal", streetAddress.text.toString())
            request.addProperty("Dom", houseNumber.text.toString())
            request.addProperty("Kvartira", homeAddress.text.toString())
            envelope.dotNet = true
            envelope.setOutputSoapObject(request)
            val httpTransportSE = HttpTransportSE(URL)
            try {
                httpTransportSE.call(SOAP_ACTION, envelope)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return extractDataFromXmlResponse(envelope)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != "") {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            } else {
                codeClient.postValue(code)
                dismiss()
            }
        }
    }

    @Throws(Exception::class)
    private fun extractDataFromXmlResponse(envelope: SoapSerializationEnvelope): String {
        var msg = ""
        var codeInfo = ""
        try {
            val response = envelope.response as SoapObject
            code = response.getProperty("Code").toString()
            codeInfo = response.getProperty("CodeInfo").toString()
            if(codeInfo == "0") msg = response.getProperty("Message").toString()
        }
        catch (ex: java.lang.Exception){
            ex.printStackTrace()
        }
        return msg
    }
}