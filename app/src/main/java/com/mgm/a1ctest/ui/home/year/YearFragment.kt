package com.mgm.a1ctest.ui.home.year

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgm.a1ctest.R
import com.mgm.a1ctest.databinding.FragmentYearBinding

class YearFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentYearBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYearBinding.inflate(layoutInflater)
        return binding.root
    }

}