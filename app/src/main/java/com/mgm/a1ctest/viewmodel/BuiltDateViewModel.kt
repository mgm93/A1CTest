package com.mgm.a1ctest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.a1ctest.db.HistModel
import com.mgm.a1ctest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class BuiltDateViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val list = MutableLiveData<List<Pair<String, String>>>()
    val emptyList = MutableLiveData<Boolean>()

    fun getBuiltDates(mnfKey: Int, carType: String) = viewModelScope.launch {
        val res = repository.getBuiltDates(mnfKey, carType)
        if (res.isSuccessful) {
            list.postValue(res.body()!!.wkda.toList())
            emptyList.postValue(false)
        }else{
            emptyList.postValue(true)
        }
    }

    val listHist = MutableLiveData<List<HistModel>>()
    val emptyHist = MutableLiveData<Boolean>()
    fun getAllHistory() = viewModelScope.launch {

        val res = repository.getAllHist()
        if (res.isNotEmpty()) {
            listHist.postValue(res)
            emptyHist.postValue(false)
        } else
            emptyHist.postValue(true)

    }

    fun insertHist(histModel: HistModel) = viewModelScope.launch {
        val exist = repository.existInHist(histModel.mnfName, histModel.carType)
        if (!exist) {
            repository.insertHist(histModel)
            emptyHist.postValue(false)
        }else{
            emptyHist.postValue(true)
        }
    }
    fun deleteHist(histModel: HistModel) = viewModelScope.launch {
        val exist = repository.deleteHist(histModel)
    }
}