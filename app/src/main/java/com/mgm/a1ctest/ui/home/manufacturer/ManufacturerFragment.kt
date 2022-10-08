package com.mgm.a1ctest.ui.home.manufacturer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgm.a1ctest.R
import com.mgm.a1ctest.databinding.FragmentManufacturerBinding

class ManufacturerFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentManufacturerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManufacturerBinding.inflate(inflater, container, false)
        return binding.root
    }
}