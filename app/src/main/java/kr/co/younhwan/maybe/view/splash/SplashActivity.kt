package kr.co.younhwan.maybe.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kr.co.younhwan.maybe.databinding.ActivitySplashBinding
import kr.co.younhwan.maybe.view.main.MainActivity
import kr.co.younhwan.maybe.view.splash.presenter.SplashContract
import kr.co.younhwan.maybe.view.splash.presenter.SplashPresenter

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    AppCompatActivity(),
    SplashContract.View {
    // binding
    private lateinit var binding: ActivitySplashBinding

    // presenter
    private val presenter by lazy {
        SplashPresenter(
            view = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set loading view
        binding.splashImage.playAnimation()

        // Temp delay
        Handler().postDelayed(Runnable {
            startMainAct()
        }, 2000)
    }

    override fun startMainAct() {
        binding.splashImage.pauseAnimation()

        startActivity(Intent(this, MainActivity::class.java))

        finish()
    }
}