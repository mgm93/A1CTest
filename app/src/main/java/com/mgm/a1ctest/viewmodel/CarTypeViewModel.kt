package com.mgm.a1ctest.viewmodel

import androidx.lifecycle.*
import com.mgm.a1ctest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class CarTypeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val list = MutableLiveData<List<Pair<String, String>>?>()
    val emptyList = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    private var mainList: List<Pair<String, String>>? = emptyList()

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
            it?.filter { item ->
                (item.second).uppercase(Locale.ROOT).contains(
                    str.uppercase(
                        Locale.ROOT
                    )
                )
            }
        }
        if (!schList.isNullOrEmpty()) {
            if (schList.size == mainList?.size) {
                list.postValue(mainList)
            }
            else { list.postValue(schList)}
        } else {
            list.postValue(null)
            emptyList.postValue(true)
        }

    }
}