package com.example.user52.pokeshooting.Ranking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.user52.pokeshooting.R
import kotlinx.android.synthetic.main.recylcer_ranking.*

class RecyclerRanking : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recylcer_ranking)

        val rankList = arrayListOf<Rank>(
                Rank("Diamond", "200", "https://www.lol-smurfs.com/blog/wp-content/uploads/2017/01/diamondi.png"),
                Rank("Gold", "150", "https://img.rankedboost.com/wp-content/uploads/2015/07/Gold-Tier.png"),
                Rank("Silver", "100", "https://www.lol-smurfs.com/blog/wp-content/uploads/2017/01/1_3.png"),
                Rank("Bronze", "50", "https://mobalytics.gg/wp-content/uploads/2016/04/bronze.png")
                )

        RrecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        RrecyclerView.setHasFixedSize(true)

        //Adapter settings
        RrecyclerView.adapter = RecyclerViewAdapter(rankList)
    }
}

data class Rank(val rank : String, val score : String, val photo : String)
