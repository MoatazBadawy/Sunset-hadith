package com.moataz.afternoonhadeeth.ui.view.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.moataz.afternoonhadeeth.data.api.APIYoutubeKey
import com.moataz.afternoonhadeeth.databinding.ActivityYoutubePlayerBinding

class YoutubePlayerActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityYoutubePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubePlayerBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        playYoutubeVideo()
        initializeViews()
    }

    private fun initializeViews() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun playYoutubeVideo() {
        binding.youtubePlayerViewHome.initialize(
            APIYoutubeKey.API_YOUTUBE_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    val videoId = intent.extras!!.getString("url")
                    youTubePlayer.loadVideo(videoId, 0)
                    youTubePlayer.setFullscreen(true)
                    youTubePlayer.play()
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Toast.makeText(
                        applicationContext,
                        "خطأ في التشغيل، يرجى إعادة المحاولة",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}