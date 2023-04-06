package com.app.data.cache.sharedPrefs

interface SharedPrefRepository {
    fun saveString(data: String, key: String):Boolean
    fun getString(query: String): String
    fun deleteString(query: String)
}