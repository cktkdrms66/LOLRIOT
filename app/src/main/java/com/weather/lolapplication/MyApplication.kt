package com.weather.lolapplication

import android.app.Application
import com.weather.lolapplication.model.LolModel
import com.weather.lolapplication.model.RegisterModel
import com.weather.lolapplication.service.LOL_BASE_URL
import com.weather.lolapplication.service.LolService
import com.weather.lolapplication.service.RegisterService
import com.weather.lolapplication.service.USER_BASE_URL
import com.weather.lolapplication.viewmodel.HomeResultViewModel
import com.weather.lolapplication.viewmodel.LoginViewModel
import com.weather.lolapplication.viewmodel.MainViewModel
import com.weather.lolapplication.viewmodel.RegisterViewModel
import org.koin.android.ext.koin.androidApplication

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.context.startKoin
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(){


    val myModules = module {
        single<RegisterService> { Retrofit.Builder()
            .baseUrl(USER_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterService::class.java)}
        single<LolService>{Retrofit.Builder()
            .baseUrl(LOL_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LolService::class.java)}
        factory { RegisterModel(get()) }
        factory { LolModel(get()) }

    }

    val loginViewModel = module {
        viewModel<LoginViewModel> {
            LoginViewModel(get(), androidApplication())
        }

    }

    val registerViewModel = module {
        viewModel<RegisterViewModel>{
            RegisterViewModel(get(), androidApplication())
        }
    }

    val mainViewModel = module {
        viewModel {
            MainViewModel(get(), androidApplication())
        }
    }

    val homeResultViewModel = module{
        viewModel {
            HomeResultViewModel(get(), androidApplication())
        }
    }






    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(myModules, loginViewModel, registerViewModel, mainViewModel, homeResultViewModel)

        }
    }


}