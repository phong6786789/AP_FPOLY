package com.subi.apsubi.screen.attend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subi.apsubi.BR
import com.subi.apsubi.data.model.DiemDanh
import com.subi.apsubi.databinding.OneAttendBinding
import com.subi.apsubi.databinding.OneScheduleBinding

class AttendAdapter(var items: List<DiemDanh>, var onItemClickListener: OnItemClickListener?) :
    RecyclerView.Adapter<AttendAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        OneAttendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    var itemSelected = -1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position], onItemClickListener)
        holder.binding.oneAttend.setOnClickListener {
            onItemClickListener?.onClickScan(items[position])
            itemSelected = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(var binding: OneAttendBinding) : RecyclerView.ViewHolder(binding.root) {

        fun binData(new: DiemDanh, onItemClickListener: OnItemClickListener?) {
            binding.apply {
                setVariable(BR.one_sch_item, new)
                executePendingBindings()
            }
            binding.oneAttend.setOnClickListener {
                onItemClickListener?.onClickScan(new)
            }

        }

    }

    fun setNewData(newItems: List<DiemDanh>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClickScan(value: DiemDanh)
    }
}