package com.mgm.a1ctest.ui.home.manufacturer

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.a1ctest.databinding.FragmentManufacturerBinding
import com.mgm.a1ctest.paging.LoadMoreAdapter
import com.mgm.a1ctest.ui.home.manufacturer.adapter.ManufacturerAdapter
import com.mgm.a1ctest.viewmodel.ManufacturerViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ManufacturerFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentManufacturerBinding

    @Inject
    lateinit var manufacturerAdapter: ManufacturerAdapter

    private val viewModel: ManufacturerViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManufacturerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Load data
            lifecycleScope.launchWhenCreated {
                viewModel.list.collect {
                    manufacturerAdapter.submitData(it)
                }
            }
            //Loading
            lifecycleScope.launchWhenCreated {
                manufacturerAdapter.loadStateFlow.collect {
                    val state = it.refresh

                    manufacturerLoading.isVisible = state is LoadState.Loading
                }
            }
            //RecyclerView
            recyclerManufacturers.apply {
                adapter = manufacturerAdapter
            }
            //SwipeRefresh
            movieSwipe.setOnRefreshListener {
                movieSwipe.isRefreshing = false
                manufacturerAdapter.refresh()
            }
            //Load more
            recyclerManufacturers.adapter = manufacturerAdapter.withLoadStateFooter(
                LoadMoreAdapter { manufacturerAdapter.retry() }
            )
            //click
            manufacturerAdapter.setOmItemClickListener { pair ->
                val direction =
                    ManufacturerFragmentDirections.actionManufacturerFragmentToCarTypeFragment(
                        pair.first,
                        pair.second
                    )
                findNavController().navigate(direction)
            }

        }
    }

}