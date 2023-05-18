package com.ybdev.appnextdaily.repository.shared_preferences

import android.content.SharedPreferences

class MySP(private val preferences: SharedPreferences?) {

    val LAST_NETWORK_CALL = "LAST_NETWORK_CALL"

    fun putLong(key: String?, value: Long) {
        preferences?.edit()?.putLong(key, value)?.apply()
    }

    fun getLong(key: String?, def: Long): Long {
        return preferences?.getLong(key, def) ?: 0L
    }

    fun putBoolean(key: String?, value: Boolean) {
        preferences?.edit()?.putBoolean(key, value)?.apply()
    }

    fun getBoolean(key: String?, def: Boolean): Boolean {
        return preferences?.getBoolean(key, def) ?: false
    }
}