package uz.kashtan.hamkortv.ui.main.channels

import android.os.Bundle
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ChannelsNetworkDataSourceImpl
import uz.kashtan.hamkortv.ui.main.channels.adapter.ChannelsAdapter

class ChannelsListActivity:BaseActivity() {
    override val layoutResource: Int
        get() = R.layout.activity_channels
    private val adapter = ChannelsAdapter()
    private lateinit var apiService : ApiService
    private lateinit var channelsNetworkDataSource : ChannelsNetworkDataSourceImpl

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.accessable_chanel)
        enableToolbarBackButton()
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        apiService = ApiService(ConnectivityInterceptorImpl(this.applicationContext))
////        channelsNetworkDataSource = ChannelsNetworkDataSourceImpl(apiService)
////        channelsList.adapter = adapter
////        channelsNetworkDataSource.downloadedChannels.observe(this, Observer {
////            adapter.setData(it)
////        })
////        GlobalScope.launch(Dispatchers.Main) {
////            channelsNetworkDataSource.fetchChannels()
////        }
//    }
}