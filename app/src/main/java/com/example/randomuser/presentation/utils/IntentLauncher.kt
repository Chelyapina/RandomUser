package com.example.randomuser.presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject
import androidx.core.net.toUri

interface IntentLauncher {
    fun launchEmail(email : String)
    fun launchPhone(phone : String)
    fun launchMaps(latitude : String , longitude : String)
    fun canHandleIntent(intentType : IntentType) : Boolean
}

class AndroidIntentLauncher @Inject constructor(
    private val context : Context ,
) : IntentLauncher {

    override fun launchEmail(email : String) {
        val uri = "mailto:$email".toUri()
        val intent = Intent(Intent.ACTION_VIEW , uri)
        launchIntent(intent , IntentType.EMAIL)
    }

    override fun launchPhone(phone : String) {
        val uri = "tel:$phone".toUri()
        val intent = Intent(Intent.ACTION_VIEW , uri)
        launchIntent(intent , IntentType.PHONE)
    }

    override fun launchMaps(latitude : String , longitude : String) {
        val intent = try {
            val lat = latitude.toDouble()
            val long = longitude.toDouble()
            val uri = "geo:$lat,$long".toUri()
            Intent(Intent.ACTION_VIEW , uri)
        } catch (e : NumberFormatException) {
            val uri = "geo:0,0?q=${Uri.encode("$latitude,$longitude")}".toUri()
            Intent(Intent.ACTION_VIEW , uri)
        }
        launchIntent(intent , IntentType.MAPS)
    }

    override fun canHandleIntent(intentType : IntentType) : Boolean {
        return createIntentForType(intentType).resolveActivity(context.packageManager) != null
    }

    private fun createIntentForType(intentType : IntentType) : Intent {
        return when (intentType) {
            IntentType.EMAIL -> Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:".toUri()
            }

            IntentType.PHONE -> Intent(Intent.ACTION_DIAL).apply {
                data = "tel:".toUri()
            }

            IntentType.MAPS -> Intent(Intent.ACTION_VIEW).apply {
                data = "geo:0,0".toUri()
            }
        }
    }

    private fun launchIntent(intent : Intent , intentType : IntentType) {
        val intentWithFlag = intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (intentWithFlag.resolveActivity(context.packageManager) != null) {
            context.startActivity(intentWithFlag)
        } else {
            throw IntentNotAvailableException(intentType)
        }
    }
}

class IntentNotAvailableException(val intentType : IntentType) : Exception()