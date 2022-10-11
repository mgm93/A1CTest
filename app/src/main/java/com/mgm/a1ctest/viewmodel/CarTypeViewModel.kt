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

    fun getCarTypes(mnfKey: Int) = viewModelScope.launch {
        isLoading.postValue(true)
        val res = repository.getCarTypes(mnfKey)
        if (res.isSuccessful) {
            if (res.body()!!.wkda.toList().isNotEmpty()) {
                list.postValue(res.body()!!.wkda.toList())
                emptyList.postValue(false)
            }else
                emptyList.postValue(true)
        }else{
            emptyList.postValue(true)
        }
        isLoading.postValue(false)
    }
}