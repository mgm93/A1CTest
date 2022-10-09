package com.mgm.a1ctest.ui.home.cartype

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.a1ctest.R
import com.mgm.a1ctest.databinding.FragmentCarTypeBinding
import com.mgm.a1ctest.ui.home.cartype.adapter.CarTypeAdapter
import com.mgm.a1ctest.ui.home.manufacturer.ManufacturerFragmentDirections
import com.mgm.a1ctest.utils.initRecycler
import com.mgm.a1ctest.viewmodel.CarTypeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarTypeFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentCarTypeBinding

    @Inject
    lateinit var carTypeAdapter: CarTypeAdapter

    //other
    private var mnfKey=""
    private var mnfName = ""
    private val args : CarTypeFragmentArgs by navArgs()
    private val viewModel : CarTypeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarTypeBinding.inflate(layoutInflater)//second way
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mnfKey = args.mnfKey
        mnfName = args.mnfName
        if (mnfKey.isNotEmpty()){
            //call carType
            viewModel.getCarTypes(mnfKey.toInt())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            viewModel.list.observe(viewLifecycleOwner) { it ->
                //setData
                carTypeAdapter.differ.submitList(it)
                //init recycler Manufacturers
                recyclerCarTypes.initRecycler(
                    LinearLayoutManager(context), carTypeAdapter
                )
                //click
                carTypeAdapter.setOmItemClickListener { pair ->
                    val direction =
                        CarTypeFragmentDirections.actionCarTypeFragmentToBuiltDateFragment(
                            mnfKey,
                            mnfName,
                            pair.first
                        )
                    findNavController().navigate(direction)
                }
            }
        }
    }

}