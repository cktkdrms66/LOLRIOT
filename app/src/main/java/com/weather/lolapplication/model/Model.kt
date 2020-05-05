package com.weather.lolapplication.model

import com.weather.lolapplication.service.*
import io.reactivex.Single

class RegisterModel(val service : RegisterService){
    fun tryValidate(userID : String,userName : String) : Single<ValidateResponse>{
        return service.validate(userID,userName)
    }

    fun tryRegister(userID:String, userPassword:String, userName:String) : Single<RegisterResponse>{
        return service.register(userID,userPassword,userName)
    }

    fun tryLogin(userID: String, userPassword: String): Single<LoginResponse>{
        return service.login(userID, userPassword)
    }

}

class LolModel(val service: LolService){
    fun getID(name : String): Single<IdResponse>{
        return service.getID("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36",
            "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
            "application/x-www-form-urlencoded; charset=UTF-8",
            "https://developer.riotgames.com",
            LOL_API_KEY,
            name)
    }

    fun getInformation(id:String):Single<List<SummonerResponse>>{
        return service.getSummoner("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36",
            "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
            "application/x-www-form-urlencoded; charset=UTF-8",
            "https://developer.riotgames.com",
            LOL_API_KEY,
            id)
    }

    fun getMatches(id:String):Single<MatchesFromSummonerResponse>{
        return service.getMatches("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36",
            "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
            "application/x-www-form-urlencoded; charset=UTF-8",
            "https://developer.riotgames.com",
            LOL_API_KEY,
            id)
    }

    fun getMatchDetail(id:String):Single<MatchDetailResponse>{
        return service.getMatchDetail("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36",
            "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
            "application/x-www-form-urlencoded; charset=UTF-8",
            "https://developer.riotgames.com",
            LOL_API_KEY,
            id)
    }
}
