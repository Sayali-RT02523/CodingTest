package com.androidmvvmdatabindingrecyclerviewkotlin.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.codingtest.model.Pets
import com.example.codingtest.repository.MainRepository
import com.google.gson.Gson
import kotlinx.coroutines.*

class MainViewModel(application: Application)  :
    AndroidViewModel(application) {
    private  var mainRepository= MainRepository(application)

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val petList = MutableLiveData<List<Pets.Pet>>()

    var job: Job? = null

    fun getConfig(): MutableLiveData<String> {
        val result = MutableLiveData<String>()
        viewModelScope.launch {
            val response = mainRepository.loadConfig()
            Log.d("ssssresponse",response.toString())
            result.postValue(response!!.settings.workHours)

        }
        return result
    }

    fun getPets() {

        viewModelScope.launch {
            val response = mainRepository.loadJSONFromAsset()
            Log.d("ssssresponse",response.toString())


            val gson = Gson()
            val mMineUserEntity = gson.fromJson(response, Pets::class.java)
            petList.postValue(mMineUserEntity.pets)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}