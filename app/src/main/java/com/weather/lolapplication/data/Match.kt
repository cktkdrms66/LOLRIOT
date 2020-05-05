package com.weather.lolapplication.data

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.weather.lolapplication.service.ParticipantResponse

class Match(val player:ParticipantResponse) {
    val average : String
    val win: String
    init {
        if(player.stats.deaths == 0){
            average = "perfect"
        }else{
            average = ((player.stats.kills + player.stats.assists + 0.0) / player.stats.deaths).toString()
        }
        if(player.stats.win) win = "승리"
        else win = "패배"
    }

    fun onClick(view:View){
        //Snackbar.make(view, "저장이 완료되었습니다", Snackbar.LENGTH_SHORT).show()
    }
}