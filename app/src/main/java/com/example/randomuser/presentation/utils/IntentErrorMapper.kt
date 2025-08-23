package com.example.randomuser.presentation.utils

import com.example.randomuser.R
import javax.inject.Inject

class IntentErrorMapper @Inject constructor() {
    fun mapToErrorResources(intentType : IntentType) : Pair<Int , Int> {
        return when (intentType) {
            IntentType.EMAIL -> R.string.no_email_app_title to R.string.no_email_app_message
            IntentType.PHONE -> R.string.no_phone_app_title to R.string.no_phone_app_message
            IntentType.MAPS -> R.string.no_maps_app_title to R.string.no_maps_app_message
        }
    }
}

enum class IntentType {
    EMAIL , PHONE , MAPS
}