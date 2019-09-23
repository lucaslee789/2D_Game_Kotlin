package com.example.user52.pokeshooting.Game

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.media.AudioManager
import android.media.SoundPool
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.user52.pokeshooting.R

import java.util.ArrayList


class GameView(internal var context: Context) : View(context) {

    internal var background: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.background)
    internal var tank: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.transchar)
    internal var rect: Rect
    internal var planes: ArrayList<Plane>
    internal var planes2: ArrayList<Plane>
    internal var missiles: ArrayList<Missile>
    internal var explosions: ArrayList<Explosion>
    internal var handler: Handler
    internal var runnable: Runnable
    internal val UPDATE_MILLIS: Long = 30
    internal var count = 0
    internal var sp: SoundPool
    internal var fire = 0
    internal var point = 0
    internal var scorePaint: Paint
    internal var healthPaint: Paint
    internal val TEXT_SIZE = 60
    internal var life = 10

    init {
        val display = (getContext() as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        dWidth = size.x
        dHeight = size.y
        rect = Rect(0, 0, dWidth, dHeight)
        planes = ArrayList()
        planes2 = ArrayList()
        missiles = ArrayList()
        explosions = ArrayList()
        for (i in 0..1) {
            val plane = Plane(context)
            planes.add(plane)
            val plane2 = Plane2(context)
            planes2.add(plane2)
        }
        handler = Handler()
        runnable = Runnable { invalidate() }
        tankWidth = tank.width
        tankHeight = tank.height
        sp = SoundPool(3, AudioManager.STREAM_MUSIC, 0)
        fire = sp.load(context, R.raw.fire, 1)
        point = sp.load(context, R.raw.point, 1)
        scorePaint = Paint()
        scorePaint.color = Color.RED
        scorePaint.textSize = TEXT_SIZE.toFloat()
        scorePaint.textAlign = Paint.Align.LEFT
        healthPaint = Paint()
        healthPaint.color = Color.GREEN
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(background, null, rect, null)
        for (i in planes.indices) {
            canvas.drawBitmap(planes[i].bitmap, planes[i].planeX.toFloat(), planes[i].planeY.toFloat(), null)
            planes[i].planeFrame++
            if (planes[i].planeFrame > 14) {
                planes[i].planeFrame = 0
            }
            planes[i].planeX -= planes[i].velocity
            if (planes[i].planeX < -planes[i].width) {
                planes[i].resetPosition()
                life--
                if (life == 0) {
                    val intent = Intent(context, GameOver::class.java)
                    intent.putExtra("score", count * 10)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }
            canvas.drawBitmap(planes2[i].bitmap, planes2[i].planeX.toFloat(), planes2[i].planeY.toFloat(), null)
            planes2[i].planeFrame++
            if (planes2[i].planeFrame > 9) {
                planes2[i].planeFrame = 0
            }
            planes2[i].planeX += planes2[i].velocity
            if (planes2[i].planeX > dWidth + planes2[i].width) {
                planes2[i].resetPosition()
                life--
                if (life == 0) {
                    val intent = Intent(context, GameOver::class.java)
                    intent.putExtra("score", count * 10)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }
        }
        for (i in missiles.indices) {
            if (missiles[i].y >- missiles[i].missileHeight) {
                missiles[i].y -= missiles[i].mVelocity
                canvas.drawBitmap(missiles[i].missile, missiles[i].x.toFloat(), missiles[i].y.toFloat(), null)
                if (missiles[i].x >= planes[0].planeX && missiles[i].x + missiles[i].missileWidth <= planes[0].planeX + planes[0].width && missiles[i].y >= planes[0].planeY &&
                        missiles[i].y <= planes[0].planeY + planes[0].height) {
                    val explosion = Explosion(context)
                    explosion.explosionX = planes[0].planeX + planes[0].width / 2 - explosion.explosionWidth / 2
                    explosion.explosionY = planes[0].planeY + planes[0].height / 2 - explosion.explosionHeight / 2
                    explosions.add(explosion)
                    planes[0].resetPosition()
                    count++
                    missiles.removeAt(i)
                    if (point != 0) {
                        sp.play(point, 1f, 1f, 0, 0, 1f)
                    }

                } else if (missiles[i].x >= planes[1].planeX && missiles[i].x + missiles[i].missileWidth <= planes[1].planeX + planes[1].width && missiles[i].y >= planes[1].planeY &&
                        missiles[i].y <= planes[1].planeY + planes[1].height) {
                    val explosion = Explosion(context)
                    explosion.explosionX = planes[1].planeX + planes[1].width / 2 - explosion.explosionWidth / 2
                    explosion.explosionY = planes[1].planeY + planes[1].height / 2 - explosion.explosionHeight / 2
                    explosions.add(explosion)
                    planes[1].resetPosition()
                    count++
                    missiles.removeAt(i)
                    if (point != 0) {
                        sp.play(point, 1f, 1f, 0, 0, 1f)
                    }

                } else if (missiles[i].x >= planes2[0].planeX && missiles[i].x + missiles[i].missileWidth <= planes2[0].planeX + planes2[0].width && missiles[i].y >= planes2[0].planeY &&
                        missiles[i].y <= planes2[0].planeY + planes2[0].height) {
                    val explosion = Explosion(context)
                    explosion.explosionX = planes2[0].planeX + planes2[0].width / 2 - explosion.explosionWidth / 2
                    explosion.explosionY = planes2[0].planeY + planes2[0].height / 2 - explosion.explosionHeight / 2
                    explosions.add(explosion)
                    planes2[0].resetPosition()
                    count++
                    missiles.removeAt(i)
                    if (point != 0) {
                        sp.play(point, 1f, 1f, 0, 0, 1f)
                    }

                } else if (missiles[i].x >= planes2[1].planeX && missiles[i].x + missiles[i].missileWidth <= planes2[1].planeX + planes2[1].width && missiles[i].y >= planes2[1].planeY &&
                        missiles[i].y <= planes2[1].planeY + planes2[1].height) {
                    val explosion = Explosion(context)
                    explosion.explosionX = planes2[1].planeX + planes2[1].width / 2 - explosion.explosionWidth / 2
                    explosion.explosionY = planes2[1].planeY + planes2[1].height / 2 - explosion.explosionHeight / 2
                    explosions.add(explosion)
                    planes2[1].resetPosition()
                    count++
                    missiles.removeAt(i)
                    if (point != 0) {
                        sp.play(point, 1f, 1f, 0, 0, 1f)
                    }

                }
            } else {
                missiles.removeAt(i)
            }
        }
        for (j in explosions.indices) {
            canvas.drawBitmap(explosions[j].getExplosion(explosions[j].explosionFrame), explosions[j].explosionX.toFloat(),
                    explosions[j].explosionY.toFloat(), null)
            explosions[j].explosionFrame++
            if (explosions[j].explosionFrame > 8) {
                explosions.removeAt(j)
            }
        }
        canvas.drawBitmap(tank, (dWidth / 2 - tankWidth / 2).toFloat(), (dHeight - tankHeight).toFloat(), null)
        canvas.drawText("Pt: " + count * 10, 0f, TEXT_SIZE.toFloat(), scorePaint)
        canvas.drawRect((dWidth - 110).toFloat(), 10f, (dWidth - 110 + 10 * life).toFloat(), TEXT_SIZE.toFloat(), healthPaint)
        handler.postDelayed(runnable, UPDATE_MILLIS)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y
        val action = event.action
        if (action == MotionEvent.ACTION_DOWN) {
            if (touchX >= dWidth / 2 - tankWidth / 2 && touchX <= dWidth / 2 + tankWidth / 2 && touchY >= dHeight - tankHeight) {
                Log.i("Tank", "is tapped")
                if (missiles.size < 1) {
                    val m = Missile(context)
                    missiles.add(m)
                    System.out.println(missiles.indices)
                    if (fire != 0) {
                        sp.play(fire, 1f, 1f, 0, 0, 1f)
                    }
                }
            }
        }
        return true
    }

    companion object {
        internal var dWidth: Int = 0
        internal var dHeight: Int = 0
        internal var tankWidth: Int = 0
        internal var tankHeight: Int = 0
    }
}
