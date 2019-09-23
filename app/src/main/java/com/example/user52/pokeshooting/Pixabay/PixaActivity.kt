package com.example.user52.pokeshooting.Pixabay

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import android.view.View

import com.example.user52.pokeshooting.R
import com.example.user52.pokeshooting.api.PhotoRetriever
import com.example.user52.pokeshooting.models.Photo
import com.example.user52.pokeshooting.models.PhotoList
import kotlinx.android.synthetic.main.activity_pixa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PixaActivity : AppCompatActivity(), View.OnClickListener {

    var photos: List<Photo>? = null
    var mainAdapter: PixaAdapter? = null
//    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pixa)
        //setSupportActionBar(toolbar)

        //recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)



        val retriever = PhotoRetriever()

        val callback = object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Problems calling API", t)
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@PixaActivity.photos = response?.body()?.hits
                    mainAdapter = PixaAdapter(this@PixaActivity.photos!!,
                            this@PixaActivity)
                    recyclerView.adapter = mainAdapter
                }
            }
        }

        retriever.getPhotos(callback)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        val holder = view?.tag as PixaAdapter.PhotoHolder
        intent.putExtra(DetailActivity.PHOTO,
                mainAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)
    }


}