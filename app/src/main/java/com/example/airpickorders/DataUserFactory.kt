package com.example.airpickorders

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

data class User(val username: String, var password: String)

class DataUserFactory(context: Context?) {

    private val dbManager = DBManager(context)

    fun login(user: User): Boolean{
        val db = User("giorgos","1908")
        return db.username ==user.username  && db.password == user.password
    }

    fun register(user: User){
        dbManager.clearPrefs()
        dbManager.setUser(user.username, USERNAME)
        dbManager.setUser(user.password, PASSWORD)
    }
    companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }
}


open class DBManager(context: Context?) {
    private val DB_NAME_FILE = "userDB"
    private var preferences: SharedPreferences =  context.getSharedPreferences( DB_NAME_FILE, Context.MODE_PRIVATE);


    fun setUser(value: String, prediction: String) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(prediction, value)
        editor.apply()
        //editor.commit()
    }

    fun getUser(prediction: String): String? {
        return preferences.getString(prediction, "")
    }

    fun editUser(newValue: String, prediction: String){
        preferences.edit().putString(prediction, newValue).apply()
    }

    fun clearPrefs() {
        preferences.edit().clear().apply()
    }
}