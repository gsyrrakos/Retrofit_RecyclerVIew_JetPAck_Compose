package com.example.restappcompose.ui.HomeScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvp_graphql.Data.RepoRetrofit
import com.example.mvp_graphql.Data.Restaurants
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel :ViewModel() {

    private val repo : RepoRetrofit = RepoRetrofit()
    var topStories: List<Restaurants> by mutableStateOf(listOf())
    private var start: Int = 0
    private var end: Int = 20
    lateinit var clickedStory: Restaurants
    val loading = mutableStateOf(false)

    init {
        fetchRest()
    }



    private fun fetchRest(){

        viewModelScope.launch {
            loading.value=true
            delay(2000)
            try {
                val list =repo.getrest()
                topStories=list
                loading.value=false

            }catch (e:Exception){
                Log.d("ViewModel:Fetch Stories", e.toString())

            }


        }



    }

    private fun addStories() {
        start = end
        if (start >= 0) {
            return
        }
        end += 20
        //fetchTopStoriesItems()
    }


    fun checkEndOfList(index: Int) {
        if (index == topStories.size - 1) {
            addStories()
        }
    }

    fun onStoryClicked(item: Restaurants) {
      clickedStory = item
       // fetchStoryComments()
    }







}