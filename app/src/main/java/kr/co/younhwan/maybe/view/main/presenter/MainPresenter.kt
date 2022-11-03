package kr.co.younhwan.maybe.view.main.presenter

import android.util.Log
import com.google.android.gms.maps.model.LatLng

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Model {

    private val locationMap = mapOf<String, LatLng>(
        "제주" to LatLng(33.4996213, 126.5311884),
        "애월" to LatLng(33.4110782, 126.3939929),
        "한림" to LatLng(33.3763407, 126.2848377),
        "한경" to LatLng(33.322496, 126.2232542),
        "대정" to LatLng(33.2563457, 126.2471366),
        "안덕" to LatLng(33.292566, 126.3476872),
        "중문" to LatLng(33.2486522, 126.4124374),
        "서귀포" to LatLng(33.2540489, 126.5600967),
        "남원" to LatLng(33.3283051, 126.6661835),
        "표선" to LatLng(33.3747638, 126.7685366),
        "성산" to LatLng(33.4180456, 126.8648601),
        "구좌" to LatLng(33.4981029, 126.7955303),
        "조천" to LatLng(33.472189, 126.6675138),
        "우도" to LatLng(33.505439, 126.9555616),
        "마라도" to LatLng(33.1649658, 126.2706327)
    )

    override fun setIcon(zoomLev: Float) {
        val placeList = mutableListOf<String>("제주", "한림", "서귀포", "성산", "우도")

        // Zoom In
        if (zoomLev >= 10.8)
            placeList.addAll(listOf("애월", "한경", "대정", "안덕", "중문", "남원", "표선", "구좌", "조천", "마라도"))

        val latLngList: MutableList<LatLng> = mutableListOf<LatLng>()
        for (place in placeList) {
            latLngList.add(locationMap[place] ?: LatLng(0.0, 0.0))
        }

        view.addMarker(latLngList)
    }
}