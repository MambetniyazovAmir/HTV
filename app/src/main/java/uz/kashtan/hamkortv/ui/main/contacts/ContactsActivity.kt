package uz.kashtan.hamkortv.ui.main.contacts

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contacts.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.ui.main.contacts.adapter.ContractAdapter
import uz.kashtan.hamkortv.ui.main.contacts.adapter.ContractModel

class ContactsActivity : BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_contacts


    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.contacts)
        enableToolbarBackButton()


//    companion object {
//        private const val METHOD_NAME = "GetClients"
//
//        private const val URL = "http://kit.gloriya.uz:5443/EVYAP_TEST/EVYAP_TEST.1cws"
//
//        private const val NAMESPACE = "http://www.sample-package.org"
//
//        private const val SOAP_ACTION = "${NAMESPACE}#MobileAgents:$METHOD_NAME"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        AsyncTaskEx().execute()
//    }
//
//    @SuppressLint("StaticFieldLeak")
//    inner class AsyncTaskEx : AsyncTask<List<ContractModel>, List<ContractModel>, List<ContractModel>>() {
//        override fun doInBackground(vararg params: List<ContractModel>): List<ContractModel> {
//            val request = SoapObject(NAMESPACE, METHOD_NAME)
//            request.addProperty("UserCode", "000000153")
//            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)
//            envelope.dotNet = true
//            envelope.setOutputSoapObject(request)
//            val httpTransportSE = HttpTransportSE(URL)
//            try {
//                httpTransportSE.call(SOAP_ACTION, envelope)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return extractDataFromXmlResponse(envelope)
//        }
//
//        override fun onPostExecute(result: List<ContractModel>) {
//            super.onPostExecute(result)
//            val adapter = ContractAdapter()
//            recycler_view.adapter = adapter
//            adapter.setData(result)
//        }
//    }
//
//    @Throws(Exception::class)
//    private fun extractDataFromXmlResponse(envelope: SoapSerializationEnvelope): List<ContractModel> {
//        val contractModelList = mutableListOf<ContractModel>()
//        val soapObject = envelope.response as SoapObject
//        val k = soapObject.propertyCount
//        for (item in 0 until k){
//            val refusal = soapObject.getProperty(item) as SoapObject
//            val model = ContractModel(refusal.getProperty("Name").toString(), 123)
//            contractModelList.add(model)
//        }
//        return contractModelList
//        try {
//            //instancing the SAXParserFactory class
//            val parserFactory: SAXParserFactory = SAXParserFactory.newInstance()
//            //instancing the SAXParser class
//            val saxParser: SAXParser = parserFactory.newSAXParser()
//            val defaultHandler = object : DefaultHandler() {
//                var currentValue = ""
//                var currentElement = false
//                //overriding the startElement() method of DefaultHandler
//                override fun startElement(
//                    uri: String,
//                    localName: String,
//                    qName: String,
//                    attributes: org.xml.sax.Attributes
//                ) {
//                    currentElement = true
//                    currentValue = ""
//                    if (localName == "Rows") {
//                        empData = HashMap()
//                    }
//                }
//
//                //overriding the endElement() method of DefaultHandler
//                override fun endElement(uri: String, localName: String, qName: String) {
//                    currentElement = false
//                    if (localName.equals("name", ignoreCase = true))
//                        empData["name"] = currentValue
//                    else if (localName.equals("shouldEnterPassport", ignoreCase = true))
//                        empData["shouldEnterPassport"] = currentValue
//                    else if (localName.equals("employee", ignoreCase = true))
//                        empList.add(empData)
//                }
//
//                //overriding the characters() method of DefaultHandler
//                override fun characters(ch: CharArray, start: Int, length: Int) {
//                    if (currentElement) currentValue += String(ch, start, length)
//                }
//            }
//            val soapObject = envelope.response as SoapObject
//            saxParser.parse(InputSource(StringReader(soapObject.toString())), defaultHandler)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } catch (e: ParserConfigurationException) {
//            e.printStackTrace()
//        } catch (e: SAXException) {
//            e.printStackTrace()
//        }
//        empList.forEach {
//            citiesList.add(it["name"].toString())
//        }
        // Initializes/instantiates a DocumentBuilder to parse the response from the SOAP envelope
        // in order to build an XML object, or a Document in this case.
//        val docBuildFactory = DocumentBuilderFactory.newInstance()
//        val docBuilder = docBuildFactory.newDocumentBuilder()
//        Log.d("response is", envelope.response.toString())
//        val doc = docBuilder.parse(InputSource(StringReader(envelope.response.toString())))
////         Retrieves a list of Table nodes from the Document in order to iterate through.
//        val nodeList = doc.getElementsByTagName("Rows")
//        for (i in 0 until nodeList.length) {
//            // Retrieves each Table node.
//            val node = nodeList.item(i)
//
//            // Runs the following functionality should the node be of an element type.
//            if (node.nodeType == Node.ELEMENT_NODE) {
//
//                // Initially casts the node as an element.
//                val element = node as Element
//
//                // Adds each city to the list.
//                citiesList.add(element.getElementsByTagName("Name").item(0).textContent)
//            }
//        }

    }
}