package com.weather.lolapplication.navigator

import com.weather.lolapplication.base.BaseNavigator

interface LoginNavigator : BaseNavigator{
    fun callMainActivity()
    fun callRegisterActivity()
}

interface RegisterNavigator : BaseNavigator{
    fun callLoginActivity()
}

interface MainNavigator : BaseNavigator{
    fun callHomeResultActivity(id:String, accountId:String)
}
