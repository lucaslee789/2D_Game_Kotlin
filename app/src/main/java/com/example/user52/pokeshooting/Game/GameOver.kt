package com.example.user52.pokeshooting.Game

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.user52.pokeshooting.R

class GameOver : Activity() {

    lateinit var tvScore: TextView
    lateinit var tvPersonalBest: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)
        val score = intent.extras!!.getInt("score")
        val pref = getSharedPreferences("MyPref", 0)
        var scoreSP = pref.getInt("scoreSP", 0)
        val editor = pref.edit()
        if (score > scoreSP) {
            scoreSP = score
            editor.putInt("scoreSP", scoreSP)
            editor.apply()
        }
        tvScore = findViewById<View>(R.id.tvScore) as TextView
        tvPersonalBest = findViewById<View>(R.id.tvPersonalBest) as TextView
        tvScore.text = "" + score
        tvPersonalBest.text = "" + scoreSP
    }

    fun restart(view: View) {
        val intent = Intent(this@GameOver, GameMenu::class.java)
        startActivity(intent)
        finish()
    }

    fun exit(view: View) {
        finish()
    }
}


