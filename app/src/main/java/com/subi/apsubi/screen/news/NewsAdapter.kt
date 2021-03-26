package com.subi.apsubi.screen.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subi.apsubi.BR
import com.subi.apsubi.data.model.News
import com.subi.apsubi.databinding.OneNewBinding

class NewsAdapter(var items: List<News>, var onItemClickListener: OnItemClickListener?) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        OneNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    var itemSelected = -1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position], onItemClickListener)
        holder.binding.oneNewCv.setOnClickListener {
            onItemClickListener?.onClickScan(items[position])
            itemSelected = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(var binding: OneNewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun binData(new: News, onItemClickListener: OnItemClickListener?) {
            binding.apply {
                setVariable(BR.itemNewViewModel, new)
                executePendingBindings()
            }
            binding.oneNewCv.setOnClickListener {
                onItemClickListener?.onClickScan(new)
            }

        }

    }

    fun setNewData(newItems: List<News>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClickScan(value: News)
    }
}