package com.mgm.a1ctest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.a1ctest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class CarTypeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val list = MutableLiveData<List<Pair<String, String>>>()
    val emptyList = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    lateinit var mainList : List<Pair<String, String>>


    fun getCarTypes(mnfKey: Int) = viewModelScope.launch {
        isLoading.postValue(true)
        val res = repository.getCarTypes(mnfKey)
        if (res.isSuccessful) {
            if (res.body()!!.wkda.toList().isNotEmpty()) {
                list.postValue(res.body()!!.wkda.toList())
                emptyList.postValue(false)
                mainList = res.body()!!.wkda.toList()
            } else
                emptyList.postValue(true)
        } else {
            emptyList.postValue(true)
        }
        isLoading.postValue(false)
    }

    fun search(str: String) = viewModelScope.launch {
        val schList = mainList.let {
            it.filter { item -> item.second.contains(str) }
        }
        if (schList.isNotEmpty()) {
            emptyList.postValue(false)
            if (schList.size == mainList.size)
                list.postValue(mainList)
            else list.postValue(schList)
        }else{
            emptyList.postValue(true)
        }

    }
}