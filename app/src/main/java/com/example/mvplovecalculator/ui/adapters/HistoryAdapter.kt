package com.example.mvplovecalculator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvplovecalculator.data.database.entities.LoveResultEntity
import com.example.mvplovecalculator.databinding.HistoryItemBinding

class HistoryAdapter : ListAdapter<LoveResultEntity, HistoryAdapter.HistoryViewHolder>(
    DiffCallback()
) {

    inner class HistoryViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LoveResultEntity) {
            binding.tvFirstName.text = item.firstName
            binding.tvSecondName.text = item.secondName
            binding.tvPercentage.text = "Compatibility: ${item.percentage}%"
            binding.tvResult.text = item.result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<LoveResultEntity>() {
        override fun areItemsTheSame(oldItem: LoveResultEntity, newItem: LoveResultEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LoveResultEntity,
            newItem: LoveResultEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}