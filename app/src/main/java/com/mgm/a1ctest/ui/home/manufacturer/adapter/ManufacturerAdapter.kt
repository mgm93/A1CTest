package com.mgm.a1ctest.ui.home.manufacturer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.a1ctest.databinding.ItemManufacturerBinding
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class ManufacturerAdapter @Inject constructor(): RecyclerView.Adapter<ManufacturerAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding : ItemManufacturerBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ManufacturerAdapter.ViewHolder {
        binding = ItemManufacturerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ManufacturerAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position].first, differ.currentList[position].second)
    }

    override fun getItemCount()= differ.currentList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun bind(key:String, value:String){
            binding.apply {
                txtData.text = value
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Pair<String, String>>(){
        override fun areItemsTheSame(
            oldItem: Pair<String, String>,
            newItem: Pair<String, String>
        ): Boolean {
            return oldItem.first== newItem.first
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, String>,
            newItem: Pair<String, String>
        ): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallBack)
}