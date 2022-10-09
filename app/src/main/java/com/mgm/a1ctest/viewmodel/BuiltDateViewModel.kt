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
class BuiltDateViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val list = MutableLiveData<List<Pair<String, String>>>()


    fun getBuiltDates(mnfKey: Int, carType: String) = viewModelScope.launch {
        val res = repository.getBuiltDates(mnfKey, carType)
        if (res.isSuccessful) {
            list.postValue(res.body()!!.wkda.toList())
        }
    }
}