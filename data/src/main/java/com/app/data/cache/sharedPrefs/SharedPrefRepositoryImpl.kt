package com.app.data.cache.sharedPrefs

import android.content.SharedPreferences

class SharedPrefRepositoryImpl(private val sharePrefs: SharedPreferences) : SharedPrefRepository {
    override fun saveString(data: String, key: String):Boolean {
        val editor: SharedPreferences.Editor = sharePrefs.edit()
        return editor.putString(key, data).commit()
    }

    override fun getString(query: String): String {
        return sharePrefs.getString(query,"").toString()

    }

    override fun deleteString(query: String) {
        sharePrefs.edit().remove(query)
    }


}