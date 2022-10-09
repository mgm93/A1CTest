package com.mgm.a1ctest.ui.home.builtdate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.a1ctest.databinding.FragmentBuiltDateBinding
import com.mgm.a1ctest.ui.home.builtdate.adapter.BuiltDateAdapter
import com.mgm.a1ctest.utils.initRecycler
import com.mgm.a1ctest.viewmodel.BuiltDateViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class BuiltDateFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentBuiltDateBinding

    @Inject
    lateinit var builtDateAdapter: BuiltDateAdapter

    //Other
    private var mnfKey = ""
    private var mnfName = ""
    private var carType = ""
    private val args : BuiltDateFragmentArgs by navArgs()
    private val viewModel : BuiltDateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuiltDateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mnfKey = args.mnfKey
        mnfName = args.mnfName
        carType = args.carType
        if (carType.isNotEmpty()){
            //call builtType
            viewModel.getBuiltDates(mnfKey.toInt(), carType)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            viewModel.list.observe(viewLifecycleOwner){
                //set date to adapter
                builtDateAdapter.differ.submitList(it)
                //init recycler
                recyclerBuiltDates.initRecycler(LinearLayoutManager(context), builtDateAdapter)
            }
        }
    }

}