package com.weather.lolapplication.service

data class RegisterResponse(val success: Boolean)
data class ValidateResponse(val success : Boolean, val userID: String, val userName : String)
data class LoginResponse(val success: Boolean)


data class IdResponse(val id : String, val accountId: String)
data class SummonerResponse(val tier :String, val rank :String, val leaguePoints:Int,
                            val wins :Int, val losses : Int, val summonerName:String)

data class MatchesFromSummonerResponse(val matches: List<MatchResponse>)
data class MatchResponse(val gameId:String)
data class MatchDetailResponse(val participants: List<ParticipantResponse>, val participantIdentities:List<PlayerResponse>)
data class PlayerResponse(val player:PlayerDetail)
data class PlayerDetail(val accountId:String)
data class ParticipantResponse(val participantId:Int, val teamId:Int, val championId:Int, val stats:StatsResponse)
data class StatsResponse(val win : Boolean, val kills:Int, val deaths:Int, val assists:Int, val spell1Id:Int, val spell2Id:Int,
                         val item0:Int, val item1:Int, val item2:Int, val item3:Int, val item4:Int, val item5:Int)