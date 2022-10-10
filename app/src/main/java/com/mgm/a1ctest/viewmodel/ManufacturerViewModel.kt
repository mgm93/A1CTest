package com.mgm.a1ctest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mgm.a1ctest.paging.ManufacturerPagingSource
import com.mgm.a1ctest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class ManufacturerViewModel @Inject constructor(private val repository:Repository): ViewModel() {

    val list = Pager(PagingConfig(1)){
        ManufacturerPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}