package com.example.airpickorders

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.internal.Contexts.getApplication
import javax.inject.Inject


class UserViewModel: ViewModel() {

    private val context = getApplication<Application>().applicationContext

    private val userDataFactory = DataUserFactory(context)

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user


    fun login(user: User) = userDataFactory.login(
        User(
            username = user.username,
            password = user.password
        )
    )

    fun register(user: User) = userDataFactory.register(
        User(
            username = user.username,
            password = user.password
        )
    )
}