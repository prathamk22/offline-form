package com.offline.form.builder.ui.allData

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.offline.form.builder.data.db.AnswerEntity
import com.offline.form.builder.databinding.AllDataItemBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AllDataAdapter :
    ListAdapter<AnswerEntity, AllDataViewHolder>(object : DiffUtil.ItemCallback<AnswerEntity>() {
        override fun areItemsTheSame(oldItem: AnswerEntity, newItem: AnswerEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AnswerEntity, newItem: AnswerEntity): Boolean {
            return oldItem.id.equals(newItem)
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDataViewHolder {
        return AllDataViewHolder(
            AllDataItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllDataViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

class AllDataViewHolder(private val binding: AllDataItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: AnswerEntity) {
        binding.respondentName.text = item.nameOfRespondent
        try {
            val format = SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.ENGLISH)
            binding.date.text = format.format(Date(item.createdAt))
        } catch (e: ParseException) {
            e.printStackTrace();
        }
    }

}
