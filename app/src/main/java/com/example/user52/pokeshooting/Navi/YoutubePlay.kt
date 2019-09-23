package com.example.user52.pokeshooting.Navi

import android.os.Bundle
import android.widget.Toast
import com.example.user52.pokeshooting.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubePlay : YouTubeBaseActivity() {

    companion object {
        val VIDEO_ID : String = "9jRtpMKLsts"
        val YOUTUBE_API_KEY: String = "AIzaSyAv2bQTZ3w08tR1iNWL_gcSl7QDM8oBNSA"
    }

    lateinit var youtubePlayerInit : YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        initUI()
    }

    private fun initUI() {
        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener{

            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                youtubePlayer?.loadVideo(VIDEO_ID)
            }


            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext, "Something went Wrong!!", Toast.LENGTH_SHORT).show()
            }
        }

        youtubePlayer.setOnClickListener {
            youtubePlayer.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
        }
    }
}