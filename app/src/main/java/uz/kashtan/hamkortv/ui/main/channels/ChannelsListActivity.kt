package uz.kashtan.hamkortv.ui.main.channels

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_channels.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.room.models.ChannelModel
import uz.kashtan.hamkortv.ui.main.channels.adapter.ChannelAdapter

class ChannelsListActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_channels

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.accessable_chanel)
        enableToolbarBackButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AsyncTaskEx().execute()
    }

    companion object {
        private const val METHOD_NAME = "Channels"

        private const val URL = "http://31.135.214.47/FileWebTest/ws/ws1.1cws"

        private const val NAMESPACE = "http://www.sample-package.org"

        private const val SOAP_ACTION = "${NAMESPACE}#Mobile:$METHOD_NAME"
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskEx :
        AsyncTask<List<ChannelModel>, List<ChannelModel>, List<ChannelModel>>() {
        override fun doInBackground(vararg params: List<ChannelModel>): List<ChannelModel> {
            val request = SoapObject(NAMESPACE, METHOD_NAME)
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

        override fun onPostExecute(result: List<ChannelModel>) {
            super.onPostExecute(result)
            val adapter = ChannelAdapter()
            adapter.setData(result)
            channelsList.adapter = adapter
        }
    }

    @Throws(Exception::class)
    private fun extractDataFromXmlResponse(envelope: SoapSerializationEnvelope): List<ChannelModel> {
        val channelsModelList = mutableListOf<ChannelModel>()
        try {
            val soapObject = envelope.response as SoapObject
            val k = soapObject.propertyCount
            for (item in 0 until k) {
                val refusal = soapObject.getProperty(item) as SoapObject
                val model = ChannelModel(
                    item + 1,
                    refusal.getProperty("Code").toString(),
                    refusal.getProperty("Name").toString()
                )
                channelsModelList.add(model)
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return channelsModelList
    }
}