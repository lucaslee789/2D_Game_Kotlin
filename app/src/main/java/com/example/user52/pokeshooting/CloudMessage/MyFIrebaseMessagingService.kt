package com.example.user52.pokeshooting.CloudMessage

import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService


/**
 * Created by js on 2018-02-15.
 */


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if (remoteMessage!!.notification != null) {
            // do with Notification payload...
            // remoteMessage.notification.body
            Log.d(TAG, "From: " + remoteMessage!!.from)
            Log.d(TAG, "${remoteMessage.notification?.body}")

            sendNotification(remoteMessage.notification?.body.toString())
        }

        if (remoteMessage.data.isNotEmpty()) {
            // do with Data payload...
            // remoteMessage.data
            Log.d(TAG, "${remoteMessage.data} : 이것은 data입니다.")

        }
    }

    fun sendNotification(messageBody : String){
        TODO()
    }
}