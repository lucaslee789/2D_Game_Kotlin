package com.example.user52.pokeshooting.Game

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.user52.pokeshooting.R
import com.example.user52.pokeshooting.Ranking.RecyclerRanking
import kotlinx.android.synthetic.main.game_menu.*


class GameMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_menu)

        btn_ranking.setOnClickListener {
            val intent = Intent(this, RecyclerRanking::class.java)
            startActivity(intent)
        }
    }

    fun startGame(v: View) {
        Log.i("ImageButton", "clicked")
        val intent = Intent(this, StartGame::class.java)
        startActivity(intent)
        finish()
    }


}