package com.example.user52.pokeshooting.Navi

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.user52.pokeshooting.R

class SeekbarVolume: AppCompatActivity () {
    private lateinit var mp: MediaPlayer

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate (savedInstanceState)
        setContentView (R.layout.seekbar_volume)
// coding180.com
        mp = MediaPlayer.create (this, R.raw.battle)
        var position = 0

        val button1 = findViewById (R.id.button1) as Button
        val button2 = findViewById (R.id.button2) as Button
        val button3 = findViewById (R.id.button3) as Button
        val button4 = findViewById (R.id.button4) as Button
       // val button5 = findViewById (R.id.button5) as Button

        button1.setOnClickListener {
            mp.start ()
            /*
            if (button5.text == "Do not play in a circular way")
                mp.isLooping = false
            else
                mp.isLooping = true
                */
        }

        button2.setOnClickListener {
            if (mp.isPlaying ()) {
                position = mp.getCurrentPosition ()
                mp.pause ()
            }
        }

        button3.setOnClickListener {
            if (mp.isPlaying () == false) {
                mp.seekTo (position)
                mp.start ()
            }
        }

        button4.setOnClickListener {
            mp.pause ()
            position = 0
            mp.seekTo (0)
        }

    }

    override fun onDestroy () {
        super.onDestroy ()
        mp.release ()
    }
}


