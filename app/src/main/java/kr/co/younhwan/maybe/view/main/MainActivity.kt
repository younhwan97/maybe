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
import kr.co.younhwan.maybe.data.source.maybe.MaybeRepository
import kr.co.younhwan.maybe.databinding.ActivityMainBinding
import kr.co.younhwan.maybe.view.main.presenter.MainContract
import kr.co.younhwan.maybe.view.main.presenter.MainPresenter

class MainActivity :
    AppCompatActivity(),
    MainContract.View,
    OnCameraMoveStartedListener,
    OnCameraMoveListener,
    OnCameraMoveCanceledListener,
    OnCameraIdleListener,
    OnMapReadyCallback {
    // binding
    private lateinit var binding: ActivityMainBinding

    // presenter
    private val presenter: MainPresenter by lazy {
        MainPresenter(
            view = this,
            maybeData = MaybeRepository,
        )
    }

    // variable
    private val jejuLatLng = LatLng(33.35944, 126.56001)
    private var previousZoomLev = 9.3f
    private val maximumZoomLev = 13f
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
            map.setLatLngBoundsForCameraTarget(
                LatLngBounds(
                    LatLng(33.15, 126.13), // SW
                    LatLng(33.60, 127.0) // NE
                )
            )

            // Zoom Controls
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomGesturesEnabled = true
            uiSettings.isTiltGesturesEnabled = false
            uiSettings.isScrollGesturesEnabled = true
            uiSettings.isRotateGesturesEnabled = true
            uiSettings.isMyLocationButtonEnabled = true

            // Show Jeju
            map.setMinZoomPreference(previousZoomLev)
            map.setMaxZoomPreference(maximumZoomLev)
            moveCamera(CameraUpdateFactory.newLatLngZoom(jejuLatLng, previousZoomLev))

            // Set Marker
            presenter.setIcon(previousZoomLev)

            // Read API
            presenter.readMaybe()
        }
    }

    override fun onCameraMoveStarted(p0: Int) {

    }

    override fun onCameraIdle() {
        val newZoomLev = map.cameraPosition.zoom

        if ((previousZoomLev < 10.8 && newZoomLev >= 10.8) || (previousZoomLev >= 10.8 && newZoomLev < 10.8)) {
            presenter.setIcon(zoomLev = newZoomLev)
        }

        previousZoomLev = newZoomLev
    }

    override fun onCameraMove() {
    }

    override fun onCameraMoveCanceled() {
    }

    override fun addMarker(location: List<LatLng>) {
        map.clear()

        for (latLng in location) {
            if (latLng != LatLng(0.0, 0.0)) {
                map.addMarker(
                    MarkerOptions()
                        .position(latLng)
                )
            }
        }
    }
}