package com.offline.form.builder.ui.home

import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.offline.form.builder.databinding.CheckboxItemBinding
import com.offline.form.builder.databinding.CheckboxMainItemBinding
import com.offline.form.builder.utils.OptionType
import com.offline.form.builder.utils.OptionTypeEnum
import com.offline.form.builder.utils.Question

class CheckboxViewHolder(
    private val binding: CheckboxItemBinding,
    private val homeViewModel: HomeViewModel
) :
    RecyclerView.ViewHolder(binding.root), OfflineViewHolder {

    override fun onBind(item: Question) {
        if (item.optionType != OptionTypeEnum.CHECK_BOX)
            return
        with(binding) {
            questionTitle.text = item.question
            val checkBoxOption = (item.options.firstOrNull() as? OptionType.CheckBox) ?: return
            checkBoxOption.checkboxItems.forEach {
                val checkBoxItem = CheckboxMainItemBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    null,
                    false
                )
                checkBoxItem.radioBtn.text = it.optionTitle
                checkBoxItem.radioBtn.isChecked = it.id == homeViewModel.getAnsIfAvailable(item.id)
                checkBoxItem.radioBtn.id = View.generateViewId()
                checkBoxItems.addView(checkBoxItem.root)
            }
            checkBoxItems.setOnCheckedChangeListener { _, i ->
                checkBoxOption.checkboxItems.first {
                    it.optionTitle == checkBoxItems.findViewById<RadioButton>(i).text
                }.let {
                    if (item.validate.isValid()) {
                        homeViewModel.valueEntered(item.id, it.id)
                    }
                }
            }
            otherCheckBox.root.isVisible = checkBoxOption.isOtherOptionAllowed
        }
    }
}