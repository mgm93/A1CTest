package com.mgm.a1ctest.ui.home.cartype.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.a1ctest.R
import com.mgm.a1ctest.databinding.ItemCarTypeBinding
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class CarTypeAdapter @Inject constructor(): RecyclerView.Adapter<CarTypeAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding : ItemCarTypeBinding
    private lateinit var context : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarTypeAdapter.ViewHolder {
        binding = ItemCarTypeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: CarTypeAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount()= differ.currentList.size
    //added to fix duplicate data
    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item : Pair<String, String>){
            binding.apply {
                txtData.text = "${context.getString(R.string.title_carType)} : ${item.second}"
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