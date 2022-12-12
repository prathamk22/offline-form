package com.offline.form.builder.ui.home

import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.offline.form.builder.databinding.CheckboxMainItemBinding
import com.offline.form.builder.databinding.StringItemBinding
import com.offline.form.builder.utils.OptionType
import com.offline.form.builder.utils.OptionTypeEnum
import com.offline.form.builder.utils.Question

class StringViewHolder(
    private val binding: StringItemBinding,
    private val homeViewModel: HomeViewModel
) : RecyclerView.ViewHolder(binding.root), OfflineViewHolder {

    override fun onBind(item: Question) {
        if (item.optionType != OptionTypeEnum.INPUT)
            return
        with(binding) {
//            questionTitle.text = item.question
            val inputOption = (item.options.firstOrNull() as? OptionType.InputField) ?: return
            val et = EditText(binding.root.context)
            et.addTextChangedListener {
                val text = it?.toString() ?: return@addTextChangedListener
                if (item.validate.isValid(text)) {
                    homeViewModel.valueEntered(item.id, text)
                } else {
                    homeViewModel.clearValue(item.id)
                }
            }
        }
    }
}