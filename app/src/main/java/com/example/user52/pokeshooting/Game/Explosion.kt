package com.example.user52.pokeshooting.Game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.user52.pokeshooting.R

class Explosion(context: Context) {

    internal var explosion = arrayOfNulls<Bitmap>(9)
    internal var explosionFrame = 0
    internal var explosionX: Int = 0
    internal var explosionY: Int = 0
    val explosionWidth: Int
        get() = explosion[0]!!.width
    val explosionHeight: Int
        get() = explosion[0]!!.height

    init {
        explosion[0] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion0)
        explosion[1] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion1)
        explosion[2] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion2)
        explosion[3] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion3)
        explosion[4] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion4)
        explosion[5] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion5)
        explosion[6] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion6)
        explosion[7] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion7)
        explosion[8] = BitmapFactory.decodeResource(context.resources, R.drawable.explosion8)
    }

    fun getExplosion(explosionFrame: Int): Bitmap? {
        return explosion[explosionFrame]
    }
}

