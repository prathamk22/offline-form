package com.offline.form.builder.ui.home

import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
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
            questionTitle.text = item.question
            val inputOption = (item.options.firstOrNull() as? OptionType.InputField) ?: return
//            if (inputOption.hint.isEmpty()) {
//                textInputLayout.hint = ""
//            } else {
//                textInputLayout.hint = inputOption.hint
//            }
            textInputEditText.inputType = inputOption.inputType
            textInputEditText.setText(homeViewModel.getAnsIfAvailable(item.id))
            textInputEditText.addTextChangedListener {
                val text = it?.toString() ?: return@addTextChangedListener
                if (item.validate.isValid(text)) {
                    homeViewModel.valueEntered(item.id, text)
                } else {
                    textInputEditText.error = item.validate.getError()
                    homeViewModel.clearValue(item.id)
                }
            }
        }
    }
}