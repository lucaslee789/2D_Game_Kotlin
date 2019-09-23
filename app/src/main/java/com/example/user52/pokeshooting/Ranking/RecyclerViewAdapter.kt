package com.example.user52.pokeshooting.Ranking

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.user52.pokeshooting.R
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.ranking_viewholder.view.*


class RecyclerViewAdapter(val rankList:ArrayList<Rank>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(rankList[position])
        setAnimation(holder.itemView)
    }
    override fun getItemCount(): Int {
        return rankList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ranking_viewholder, parent, false)
        return ViewHolder(v)
    }

    fun setAnimation(view: View) {

        if (view.animation == null) {
            var animation: Animation = AnimationUtils.loadAnimation(view.context, R.anim.abc_slide_in_bottom)
            animation.setDuration(1000)
            view.startAnimation(animation)

        }
    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : Rank){
            //Show Image
            Glide.with(itemView.context).load(data.photo)
                    .into(itemView.imageView_rank)
            itemView.textView_rank.text = data.rank
            itemView.textView_Score.text = data.score
            //itemView.imageView_photo.setImageBitmap(data.photo)

            //When Clicked
            itemView.setOnClickListener({
                //Toast
                Toast.makeText(itemView.context, "This is the rank '${data.rank}'. Compare Your Score", Toast.LENGTH_LONG).show()
            })
        }

    }

}