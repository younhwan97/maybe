package kr.co.younhwan.maybe.view.main.presenter

import com.google.android.gms.maps.model.LatLng

interface MainContract {
    interface View {
        fun addMarker(location: List<LatLng>)
    }

    interface Model {
        fun setIcon(zoomLev: Float)
    }
}