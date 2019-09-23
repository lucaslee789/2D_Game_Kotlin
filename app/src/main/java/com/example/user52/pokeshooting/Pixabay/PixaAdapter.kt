package com.example.user52.pokeshooting.Pixabay

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.user52.pokeshooting.R
import com.example.user52.pokeshooting.models.Photo

/**
 * Created by czprobity on 13/07/2017.
 */
class PixaAdapter(var photos: List<Photo>,
                  var clickListener: View.OnClickListener) : RecyclerView.Adapter<PixaAdapter.PhotoHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PhotoHolder {
        return PhotoHolder(LayoutInflater.from(p0?.context)
                .inflate(R.layout.photo_item, p0, false))
    }

    override fun onBindViewHolder(p0: PhotoHolder, p1: Int) {
        val photo = photos[p1]
        p0?.tags?.text = photo.tags
        p0?.likes?.text = photo.likes.toString()
        p0?.favorites?.text = photo.favorites.toString()
        if (photo.previewURL.isNotEmpty()) {
            Glide.with(p0?.tags?.context)
                    .load(photo.previewURL)
                    .into(p0?.photoItem)
        }
        setAnimation(p0.itemView)
    }

    fun setAnimation(view: View) {

        if (view.animation == null) {
            var animation: Animation = AnimationUtils.loadAnimation(view.context, R.anim.abc_slide_in_bottom)
            animation.setDuration(1000)
            view.startAnimation(animation)

        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }



    fun getPhoto(adapterPosition: Int) : Photo {
        return photos[adapterPosition]
    }


    inner class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tags : TextView
        var likes : TextView
        var favorites : TextView
        var photoItem : ImageView

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
            tags = itemView.findViewById(R.id.tags)
            likes = itemView.findViewById(R.id.likes)
            favorites = itemView.findViewById(R.id.favorites)
            photoItem = itemView.findViewById(R.id.photo_item)
        }
    }

}