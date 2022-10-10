package com.mgm.a1ctest.ui.home.manufacturer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mgm.a1ctest.R
import com.mgm.a1ctest.databinding.ItemManufacturerBinding
import javax.inject.Inject

class ManufacturerAdapter @Inject constructor() : PagingDataAdapter<Pair<String,String>, ManufacturerAdapter.ViewHolder>(differCallback) {

    private lateinit var binding: ItemManufacturerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerAdapter.ViewHolder {
        binding = ItemManufacturerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ManufacturerAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Pair<String, String>){
            binding.apply {
                txtData.text = item.second
                itemImg.load(R.drawable.logo) {
                    crossfade(true)
                    crossfade(500)
                }
                //click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }
    //onClick
    private var onItemClickListener : ((Pair<String, String>) -> Unit)? = null

    fun setOmItemClickListener(listener: (Pair<String, String>) -> Unit){
        onItemClickListener = listener
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Pair<String, String>>(){
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
    }

}