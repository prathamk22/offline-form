package com.offline.form.builder.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.offline.form.builder.databinding.CheckboxItemBinding
import com.offline.form.builder.databinding.StringItemBinding
import com.offline.form.builder.utils.OptionTypeEnum
import com.offline.form.builder.utils.Question

class QuestionAdapter(
    private val homeViewModel: HomeViewModel
) :
    ListAdapter<Question, ViewHolder>(object : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            OptionTypeEnum.CHECK_BOX.id -> CheckboxViewHolder(
                CheckboxItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                homeViewModel
            )
            OptionTypeEnum.INPUT.id -> StringViewHolder(
                StringItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                homeViewModel,
                LocalTextWatcher(homeViewModel)
            )
            else -> throw IllegalStateException("Input type not found")
        }
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        (holder as? OfflineViewHolder)?.onAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder){
        super.onViewDetachedFromWindow(holder)
        (holder as? OfflineViewHolder)?.onDetachedFromWindow()
    }

    override fun getItemViewType(position: Int): Int {
        return OptionTypeEnum.values().indexOf(getItem(position).optionType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as? StringViewHolder)?.localTextWatcher?.updateItem(getItem(position))
        (holder as? OfflineViewHolder)?.onBind(getItem(position))
    }
}
