package com.weather.lolapplication.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weather.lolapplication.base.BaseViewModel
import com.weather.lolapplication.model.RegisterModel
import com.weather.lolapplication.navigator.RegisterNavigator
import com.weather.lolapplication.utils.SingleLiveEvent
import com.weather.lolapplication.utils.Message
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(val model : RegisterModel, application: Application) : BaseViewModel(application){


    lateinit var registerNavigator : RegisterNavigator

    private val _isOkLiveData = MutableLiveData<Boolean>()
    val isOkLiveData: LiveData<Boolean>
        get() = _isOkLiveData

    private val _idLiveData = MutableLiveData<String>()
    val idLiveData : LiveData<String>
        get() = _idLiveData

    private val _passwordLiveData = MutableLiveData<String>()
    val passwordLiveData : LiveData<String>
        get() = _passwordLiveData

    private val _nameLiveData = MutableLiveData<String>()
    val nameLiveData : LiveData<String>
        get() = _nameLiveData

    private val _snackbarLiveData = SingleLiveEvent<Message>()
    val snackbarLiveData : LiveData<Message>
        get() = _snackbarLiveData


    init{
        _isOkLiveData.value = false
        _idLiveData.value = ""
        _passwordLiveData.value = ""
        _nameLiveData.value = ""
    }

    fun idTextChanged(string: String){
        _idLiveData.value = string
        checkAndRegister()
    }

    fun passwordTextChanged(string: String){
        _passwordLiveData.value = string
        checkAndRegister()
    }

    fun nameTextChanged(string: String){
        _nameLiveData.value = string
        checkAndRegister()
    }

    fun checkAndRegister(){
        if(!_idLiveData.value!!.isEmpty() && !_passwordLiveData.value!!.isEmpty()
            && !_nameLiveData.value!!.isEmpty()){
            unlockRegister(true)
        }else{
            unlockRegister(false)
        }
    }

    fun unlockRegister(boolean: Boolean){
        _isOkLiveData.postValue(boolean)
    }


    fun tryRegister(){
        if(_isOkLiveData.value == true){
            println("try register")
            println(_idLiveData.value)
            println(_nameLiveData.value)
            addDisposable(model.tryValidate(_idLiveData.value!!,_nameLiveData.value!!).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("fail")
                    println(it.success)
                    if(it.success){
                        register(_idLiveData.value!!,_passwordLiveData.value!!,_nameLiveData.value!!)
                    }else{
                        if(_idLiveData.value.equals(it.userID)){
                            _snackbarLiveData.value = Message.SAME_ID
                        }else if(_nameLiveData.value.equals(it.userName)){
                            _snackbarLiveData.value = Message.SAME_NAME
                        }
                    }
                },{it.printStackTrace()}))
        }
    }

    fun register(id:String, password:String, value:String){
        println("success register")
        println("$id, $password, $value")
        addDisposable(model.tryRegister(id, password, value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.success){
                    _snackbarLiveData.value = Message.REGISTER_SUCCESS
                    registerNavigator.callLoginActivity()
                }
            },{it.printStackTrace()}))
    }



}