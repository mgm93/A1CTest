package com.mgm.a1ctest.ui.home.builtdate.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.a1ctest.databinding.ItemBuiltDateHistoryBinding
import com.mgm.a1ctest.databinding.ItemManufacturerBinding
import com.mgm.a1ctest.db.HistModel
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class BuiltDateHistoryAdapter @Inject constructor(): RecyclerView.Adapter<BuiltDateHistoryAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding : ItemBuiltDateHistoryBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuiltDateHistoryAdapter.ViewHolder {
        binding = ItemBuiltDateHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: BuiltDateHistoryAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount()= differ.currentList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item : HistModel){
            binding.apply {
                txtHistory.text = "${item.mnfName} < ${item.carType}"
                //click
                txtHistory.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
                imgDelete.setOnClickListener {
                    onItemDeleteClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    //onClick
    private var onItemClickListener : ((HistModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (HistModel) -> Unit){
        onItemClickListener = listener
    }
    //onClick delete
    private var onItemDeleteClickListener : ((HistModel) -> Unit)? = null

    fun setOnItemDeleteClickListener(listener: (HistModel) -> Unit){
        onItemDeleteClickListener = listener
    }

    private val differCallBack = object : DiffUtil.ItemCallback<HistModel>(){
        override fun areItemsTheSame(oldItem: HistModel, newItem: HistModel): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: HistModel, newItem: HistModel): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallBack)
}