package com.mgm.a1ctest.ui.home.manufacturer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.a1ctest.databinding.FragmentManufacturerBinding
import com.mgm.a1ctest.ui.home.manufacturer.adapter.ManufacturerAdapter
import com.mgm.a1ctest.utils.initRecycler
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Call Api
        viewModel.getManufacturer()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            viewModel.list.observe(viewLifecycleOwner) { it ->
                //setData
                manufacturerAdapter.differ.submitList(it)
                //init recycler Manufacturers
                recyclerManufacturers.initRecycler(
                    LinearLayoutManager(context), manufacturerAdapter
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
}