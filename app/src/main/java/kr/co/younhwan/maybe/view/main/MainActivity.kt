package kr.co.younhwan.maybe.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.OnCameraMoveListener
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener
import com.google.android.gms.maps.GoogleMap.OnCameraMoveCanceledListener
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
import com.google.android.gms.maps.model.*
import kr.co.younhwan.maybe.R
import kr.co.younhwan.maybe.databinding.ActivityMainBinding

class MainActivity :
    AppCompatActivity(),
    OnCameraMoveStartedListener,
    OnCameraMoveListener,
    OnCameraMoveCanceledListener,
    OnCameraIdleListener,
    OnMapReadyCallback {
    // binding
    private lateinit var binding: ActivityMainBinding

    // variable
    private val jejuLatLng = LatLng(33.35944, 126.56001)
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // return early if the map was not initialised properly
        map = googleMap ?: return

        with(googleMap) {
            // Set appearance
            setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    applicationContext,
                    R.raw.map_style_json
                )
            )

            // Set Event Listener
            setOnCameraIdleListener(this@MainActivity)
            setOnCameraMoveStartedListener(this@MainActivity)
            setOnCameraMoveListener(this@MainActivity)
            setOnCameraMoveCanceledListener(this@MainActivity)

            // Constrain the camera target to the Adelaide bounds.
            map.setLatLngBoundsForCameraTarget(LatLngBounds(
                LatLng(33.15, 126.13), // SW
                LatLng(33.60, 127.0) // NE
            ))

            // Zoom Controls
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomGesturesEnabled = true
            uiSettings.isTiltGesturesEnabled = false
            uiSettings.isScrollGesturesEnabled = true
            uiSettings.isRotateGesturesEnabled = true
            uiSettings.isMyLocationButtonEnabled = true

            // Show Jeju
            map.setMinZoomPreference(9.3f)
            map.setMaxZoomPreference(13.0f)
            moveCamera(CameraUpdateFactory.newLatLngZoom(jejuLatLng, 9.3f))
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
    }

    override fun onCameraIdle() {
    }

    override fun onCameraMove() {
    }

    override fun onCameraMoveCanceled() {
    }
}