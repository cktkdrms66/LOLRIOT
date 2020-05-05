package com.weather.lolapplication.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weather.lolapplication.base.BaseViewModel
import com.weather.lolapplication.model.LolModel
import com.weather.lolapplication.navigator.MainNavigator
import com.weather.lolapplication.room.entity.User
import com.weather.lolapplication.room.repository.UserRepository
import com.weather.lolapplication.utils.Message
import com.weather.lolapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(val model : LolModel, application: Application) : BaseViewModel(application){
    lateinit var navigator : MainNavigator

    private val repository = UserRepository(application)
    private val users = repository.getAll()

    private val _snackBar = SingleLiveEvent<Message>()
    val snackbar : LiveData<Message>
        get() = _snackBar

    fun search(string :String){
        if(string.isEmpty()){
            _snackBar.value = Message.EMPTY
            return
        }
        addDisposable(model.getID(string).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("${it.id} $string")
                searchFromID(it.id, it.accountId)
            },{it.printStackTrace()}))

    }

    fun searchFromID(id: String, accountId : String){
        navigator.callHomeResultActivity(id, accountId)
    }

    fun getAll():LiveData<List<User>>{
        return this.users
    }

    fun insert(user:User){
        repository.insert(user)
    }

    fun delete(user:User){
        repository.delete(user)
    }
}