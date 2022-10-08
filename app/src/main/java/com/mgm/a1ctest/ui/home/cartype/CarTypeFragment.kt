package com.mgm.a1ctest.ui.home.cartype

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgm.a1ctest.R
import com.mgm.a1ctest.databinding.FragmentCarTypeBinding

class CarTypeFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentCarTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarTypeBinding.inflate(layoutInflater)//second way
        return binding.root
    }

}