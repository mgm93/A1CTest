package com.mgm.a1ctest.ui.home.builtdate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mgm.a1ctest.databinding.FragmentBuiltDateBinding

class BuiltDateFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentBuiltDateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuiltDateBinding.inflate(layoutInflater)
        return binding.root
    }

}