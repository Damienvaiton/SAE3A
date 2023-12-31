package fr.but.sae2024.edukid.utils

import android.content.Context

import android.content.SharedPreferences

class MySharedPreferences {

    private val NAME = "MyPref"
    private var editor: SharedPreferences.Editor? = null
    private var sharedPreferences: SharedPreferences? = null
    private var instance: MySharedPreferences? = null

    fun getInstance(): MySharedPreferences? {
        if (instance == null) {
            instance = MySharedPreferences()
        }
        return instance
    }

    fun sharedPreferences(context: Context) {
        if (sharedPreferences == null) sharedPreferences = context.getSharedPreferences(NAME, 0)
        if (editor == null) editor = sharedPreferences!!.edit()
    }

    fun getGameName(context: Context): String? {
        return getSharedPreferencesString(context, "gameName")
    }

    fun getGameId(context: Context): Int {
        return getSharedPreferencesInt(context, "gameId")
    }

    fun getSubGameId(context: Context): Int {
        return getSharedPreferencesInt(context, "subGameId")
    }

    fun getSubGameName(context: Context): String? {
        return getSharedPreferencesString(context, "subGameName")
    }

    fun getThemeName(context: Context): String? {
        return getSharedPreferencesString(context, "themeName")
    }

    fun getUserId(context: Context): Int {
        return getSharedPreferencesInt(context, "userId")
    }

    fun getUserImage(context: Context): String? {
        return getSharedPreferencesString(context, "userImage")
    }

    fun getUserName(context: Context): String? {
        return getSharedPreferencesString(context, "userName")
    }

    fun getSharedPreferencesString(context: Context, name: String?): String? {
        sharedPreferences(context)
        return sharedPreferences!!.getString(name, "")
    }

    fun getSharedPreferencesInt(context: Context, name: String?): Int {
        sharedPreferences(context)
        return sharedPreferences!!.getInt(name, -1)
    }

    fun getSharedPreferencesBoolean(context: Context, name: String?, defaut: Boolean): Boolean {
        sharedPreferences(context)
        return sharedPreferences!!.getBoolean(name, defaut)
    }

    fun setSharedPreferencesString(context: Context, name: String?, value: String?) {
        sharedPreferences(context)
        editor!!.putString(name, value)
    }

    fun setSharedPreferencesInt(context: Context, name: String?, value: Int) {
        sharedPreferences(context)
        editor!!.putInt(name, value)
    }

    fun setSharedPreferencesBoolean(context: Context, name: String?, value: Boolean) {
        sharedPreferences(context)
        editor!!.putBoolean(name, value)
    }

    fun commit() {
        if (editor != null) editor!!.commit()
    }
}