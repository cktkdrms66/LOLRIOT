package com.weather.lolapplication.service

import io.reactivex.Single
import retrofit2.http.*

const val USER_BASE_URL = "http://cktkdrms66.dothome.co.kr/"
const val LOL_BASE_URL = "https://kr.api.riotgames.com/lol/"
const val LOL_API_KEY = "RGAPI-53f34061-8bcf-466a-abd0-94c85e04c838"
interface RegisterService{
    @FormUrlEncoded
    @POST("Validate.php")
    fun validate(@Field("userID")userID : String,
                 @Field("userName")userName:String): Single<ValidateResponse>

    @FormUrlEncoded
    @POST("Register.php")
    fun register(@Field("userID")userID : String,
                 @Field("userPassword")userPassword:String,
                 @Field("userName")userName:String): Single<RegisterResponse>

    @FormUrlEncoded
    @POST("Login.php")
    fun login(@Field("userID")userID : String,
              @Field("userPassword")userPassword: String): Single<LoginResponse>
}

interface LolService{
    @GET("summoner/v4/summoners/by-name/{name}")
    fun getID(@Header("User-Agent")user_agent:String,
              @Header("Accept-Language")accept_language:String,
              @Header("Accept-Charset")accept_charset:String,
              @Header("Origin")origin:String,
              @Header("X-Riot-Token")api_key:String,
              @Path("name") name:String): Single<IdResponse>

    @GET("league/v4/entries/by-summoner/{id}")
    fun getSummoner(@Header("User-Agent")user_agent:String,
                    @Header("Accept-Language")accept_language:String,
                    @Header("Accept-Charset")accept_charset:String,
                    @Header("Origin")origin:String,
                    @Header("X-Riot-Token")api_key:String,
                    @Path("id")id:String): Single<List<SummonerResponse>>
    @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}")
    fun getMatches(@Header("User-Agent")user_agent:String,
                  @Header("Accept-Language")accept_language:String,
                  @Header("Accept-Charset")accept_charset:String,
                  @Header("Origin")origin:String,
                  @Header("X-Riot-Token")api_key:String,
                  @Path("encryptedAccountId")id:String): Single<MatchesFromSummonerResponse>

    @GET("/lol/match/v4/matches/{matchId}")
    fun getMatchDetail(@Header("User-Agent")user_agent:String,
                       @Header("Accept-Language")accept_language:String,
                       @Header("Accept-Charset")accept_charset:String,
                       @Header("Origin")origin:String,
                       @Header("X-Riot-Token")api_key:String,
                       @Path("matchId")id:String): Single<MatchDetailResponse>
}