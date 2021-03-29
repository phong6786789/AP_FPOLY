package com.subi.apsubi.screen.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subi.apsubi.BR
import com.subi.apsubi.data.model.LichHoc
import com.subi.apsubi.databinding.OneScheduleBinding

class ScheduleAdapter(var items: List<LichHoc>, var onItemClickListener: OnItemClickListener?) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        OneScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    var itemSelected = -1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position], onItemClickListener)
        holder.binding.oneSchedule.setOnClickListener {
            onItemClickListener?.onClickScan(items[position])
            itemSelected = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(var binding: OneScheduleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun binData(new: LichHoc, onItemClickListener: OnItemClickListener?) {
            binding.apply {
                setVariable(BR.one_sch_item, new)
                executePendingBindings()
            }
            binding.oneSchedule.setOnClickListener {
                onItemClickListener?.onClickScan(new)
            }

        }

    }

    fun setNewData(newItems: List<LichHoc>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClickScan(value: LichHoc)
    }
}