package uz.kashtan.hamkortv.ui.main.feedback

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import uz.kashtan.hamkortv.R
import uz.kashtan.hamkortv.base.BaseActivity

class FeedbackActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_feedback

    override fun init(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.feedback)
        enableToolbarBackButton()
    }

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            val locationHTV = LatLng(41.281376, 69.210081)
            googleMap.addMarker(MarkerOptions().position(locationHTV).title("Location of HTV"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationHTV, 15f))
        })
    }
}
