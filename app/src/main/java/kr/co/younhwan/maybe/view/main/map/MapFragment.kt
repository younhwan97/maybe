package kr.co.younhwan.maybe.view.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kr.co.younhwan.maybe.R
import kr.co.younhwan.maybe.databinding.FragmentMapBinding
import kr.co.younhwan.maybe.view.main.map.presenter.MapContract
import kr.co.younhwan.maybe.view.main.map.presenter.MapPresenter

class MapFragment : Fragment(), MapContract.View, OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding

    private val presenter: MapPresenter by lazy {
        MapPresenter(
            view = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}