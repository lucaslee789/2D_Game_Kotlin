package com.example.user52.pokeshooting.Pixabay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.bumptech.glide.Glide
import com.example.user52.pokeshooting.R
import com.example.user52.pokeshooting.models.Photo

import kotlinx.android.synthetic.main.detail_pixa.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pixa)
        //setSupportActionBar(toolbar)

        val photo = intent.getSerializableExtra(PHOTO) as Photo?
        photo?.webformatURL.let {
            Glide.with(this).load(photo?.webformatURL)
                    .into(imageView)
        }

        imageView.setOnClickListener {
            finish()
        }
    }

    companion object {
        val PHOTO = "PHOTO"
    }
}