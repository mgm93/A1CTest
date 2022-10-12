package com.mgm.a1ctest.ui.home.cartype

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mgm.a1ctest.databinding.FragmentCarTypeBinding
import com.mgm.a1ctest.ui.home.cartype.adapter.CarTypeAdapter
import com.mgm.a1ctest.utils.showInvisible
import com.mgm.a1ctest.viewmodel.CarTypeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarTypeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentCarTypeBinding

    @Inject
    lateinit var carTypeAdapter: CarTypeAdapter

    //other
    private var mnfKey = ""
    private var mnfName = ""
    private val args: CarTypeFragmentArgs by navArgs()
    private val viewModel: CarTypeViewModel by viewModels()

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
        if (mnfKey.isNotEmpty()) {
            //call carType
            viewModel.getCarTypes(mnfKey.toInt())
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Search Box
            edtSearch.addTextChangedListener {
                it.let {
                    viewModel.search(it.toString())
                }
            }
            //service list response
            viewModel.list.observe(viewLifecycleOwner) { it ->
                //setData
                carTypeAdapter.differ.submitList(null)//added because duplicate from search
                carTypeAdapter.differ.submitList(it)
                //init recycler Manufacturers
                recyclerCarTypes.adapter = carTypeAdapter
            }
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
            //Empty History
            viewModel.emptyList.observe(viewLifecycleOwner) {
                if (it) {
                    emptyState.showInvisible(true)
                    recyclerCarTypes.showInvisible(false)
                } else {
                    emptyState.showInvisible(false)
                    recyclerCarTypes.showInvisible(true)
                }
            }
            //loading
            viewModel.isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    loading.showInvisible(true)
                } else {
                    loading.showInvisible(false)
                }
            }
        }
    }

}