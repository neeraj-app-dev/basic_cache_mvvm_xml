package com.example.cache_mvvm_xml.view

import android.content.Context
import android.content.SyncStatusObserver
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cache_mvvm_xml.data.Details
import com.example.cache_mvvm_xml.databinding.RecyclerviewItemBinding
import okhttp3.internal.notifyAll

class DetailsListAdapter(
    listData : MutableLiveData<List<Details>>,
    observerOwner: LifecycleOwner
) : RecyclerView.Adapter<DetailsListAdapter.DetailsViewHolder>() {
   private var list : List<Details> = arrayListOf()
    init {
        listData.observe(observerOwner){
            list = it
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(view)
    }

    override fun getItemCount() : Int {
        return list.size
    }
    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
            with(holder.binding) {
                with(list[position]) {
                    idNo.text = id.toString().plus(".")
                    titleTxt.text = title
                }
        }
    }

    inner class DetailsViewHolder(val binding: RecyclerviewItemBinding) : ViewHolder(binding.root)

}