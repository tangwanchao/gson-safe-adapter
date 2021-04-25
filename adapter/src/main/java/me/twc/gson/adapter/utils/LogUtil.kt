package me.twc.gson.adapter.utils

import android.util.Log

/**
 * @author 唐万超
 * @date 2021/04/23
 */

private const val LOG_TAG = "GsonSafeAdapter"

fun logD(message: String, throwable: Throwable? = null) {
    if (Log.isLoggable(LOG_TAG, Log.DEBUG)) {
        Log.d(LOG_TAG, message, throwable)
    }
}

