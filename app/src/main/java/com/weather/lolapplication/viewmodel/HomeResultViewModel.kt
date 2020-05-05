package com.weather.lolapplication.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.lolapplication.R
import com.weather.lolapplication.base.BaseViewModel
import com.weather.lolapplication.data.Match
import com.weather.lolapplication.model.LolModel
import com.weather.lolapplication.room.entity.User
import com.weather.lolapplication.room.repository.UserRepository
import com.weather.lolapplication.utils.Message
import com.weather.lolapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeResultViewModel(val model: LolModel, application: Application) :BaseViewModel(application){


    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    private val _tier = MutableLiveData<String>()
    val tier : LiveData<String>
        get() = _tier

    private val _rank = MutableLiveData<String>()
    val rank : LiveData<String>
        get() = _rank

    private val _leaguePoints = MutableLiveData<String>()
    val leaguePoints : LiveData<String>
        get() = _leaguePoints

    private val _wins = MutableLiveData<String>()
    val wins : LiveData<String>
        get() = _wins

    private val _loses = MutableLiveData<String>()
    val loses : LiveData<String>
        get() = _loses

    private val _average = MutableLiveData<String>()
    val average : LiveData<String>
        get() = _average

    private val _image = MutableLiveData<Int>()
    val image : LiveData<Int>
        get() = _image

    private val _winsAndLosses = MutableLiveData<String>()
    val winsAndLosses :LiveData<String>
        get() = _winsAndLosses

    private val _tierAndRank = MutableLiveData<String>()
    val tierAndRank : LiveData<String>
        get() = _tierAndRank

    private val _matches = MutableLiveData<ArrayList<Match>>()
    val matches : LiveData<ArrayList<Match>>
        get() = _matches

    private val _snackbarLiveData = SingleLiveEvent<Message>()
    val snackbarLiveData : LiveData<Message>
        get() = _snackbarLiveData

    private val repository = UserRepository(application)
    private val users = repository.getAll()


    init {
        _name.value = ""
        _image.value = R.drawable.emblem_challenger
        _tier.value = ""
        _average.value = ""
        _leaguePoints.value = ""
        _rank.value = ""
        _wins.value = ""
        _loses.value = ""
        _matches.value = ArrayList<Match>()
    }

    fun setSummoner(id:String?){
        addDisposable(model.getInformation(id!!).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("summoner")
                val content = it.get(0)
                _name.value = content.summonerName
                _tier.value = content.tier
                _rank.value = content.rank
                _leaguePoints.value = content.leaguePoints.toString()
                _wins.value = content.wins.toString()
                _loses.value = content.losses.toString()
                _average.value = (content.wins * 100/(content.wins + content.losses)).toString()
                when(_tier.value){
                    "IRON"->_image.value = R.drawable.emblem_iron
                    "BRONZE"->_image.value = R.drawable.emblem_bronze
                    "SILVER"->_image.value = R.drawable.emblem_silver
                    "GOLD"->_image.value = R.drawable.emblem_gold
                    "PLATINUM"->_image.value = R.drawable.emblem_platinum
                    "DIAMOND"->_image.value = R.drawable.emblem_diamond
                    "MASTER"->_image.value = R.drawable.emblem_master
                    "GRANDMASTER"->_image.value = R.drawable.emblem_grandmaster
                    "CHALLENGER"->_image.value = R.drawable.emblem_challenger
                    else->_image.value = R.drawable.emblem_challenger
                }
                _tierAndRank.value = "${_tier.value} ${_rank.value}"
                _winsAndLosses.value = "${_wins.value}승 ${_loses.value}패 ${_average.value}%"


            },{it.printStackTrace()}))
    }

    fun setList(id:String?){
        addDisposable(model.getMatches(id!!).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("setList success")
                val matches = it.matches
                println(matches[0].gameId)
                for(i in 0..9){
                   addListItem(matches[i].gameId, id)
                }

            },{it.printStackTrace()}))
    }

    fun addListItem(id:String?, summonerId:String?){
        addDisposable(model.getMatchDetail(id!!).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("addListItem suuccess")
                println(summonerId)
                for(i in 0..9){
                    println(it.participantIdentities[i].player.accountId)
                    if(it.participantIdentities[i].player.accountId.equals(summonerId)){
                        println("add ${it.participants[i].participantId}")
                        val list = _matches.value as ArrayList<Match>
                        list.add(Match(it.participants[i]))
                        _matches.value = list
                        break
                    }
                }

            },{it.printStackTrace()}))
    }

    fun onClick(view:View){
        println("save")
        repository.insert(User(null, _name.value!!, _tier.value!!))
        _snackbarLiveData.value = Message.SAVE

    }


}