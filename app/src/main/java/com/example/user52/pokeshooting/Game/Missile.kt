package com.example.user52.pokeshooting.Game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.user52.pokeshooting.R

class Missile(context: Context) {
    internal var x: Int = 0
    internal var y: Int = 0
    internal var mVelocity: Int = 0
    internal var missile: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.missile)

    val missileWidth: Int
        get() = missile.width
    val missileHeight: Int
        get() = missile.height

    init {
        x = GameView.dWidth / 2 - missileWidth / 2
        y = GameView.dHeight - GameView.tankHeight - missileHeight / 2
        mVelocity = 50
    }
}


