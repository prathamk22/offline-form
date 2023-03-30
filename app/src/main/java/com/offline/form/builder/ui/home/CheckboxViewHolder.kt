package com.offline.form.builder.ui.home

import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.offline.form.builder.databinding.CheckboxItemBinding
import com.offline.form.builder.databinding.CheckboxMainItemBinding
import com.offline.form.builder.databinding.CheckboxTextItemBinding
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
            questionTitle.text = item.id + " " + item.question.plus(if (item.isOptional) " ( Optional )" else "")
            val checkBoxOption = (item.options.firstOrNull() as? OptionType.CheckBox) ?: return
            checkBoxItems.removeAllViews()
            checkBoxOption.checkboxItems.forEach {
                if (it.isTextOnly){
                    val checkBoxItem = CheckboxTextItemBinding.inflate(
                        LayoutInflater.from(binding.root.context),
                        null,
                        false
                    )
                    checkBoxItem.radioTxt.text = it.optionTitle
                    checkBoxItem.radioTxt.id = View.generateViewId()
                    checkBoxItems.addView(checkBoxItem.root)
                } else {
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
            }
            checkBoxItems.setOnCheckedChangeListener { _, i ->
                val checkboxText = checkBoxItems.findViewById<RadioButton?>(i)?.text
                    ?: return@setOnCheckedChangeListener
                checkBoxOption.checkboxItems.first {
                    it.optionTitle == checkboxText
                }.let {
                    if (item.validate.isValid(checkboxText)) {
                        homeViewModel.valueEntered(item.id, it.id)
                    } else {
                        homeViewModel.clearValue(item.id)
                    }
                }
            }
            otherCheckBox.root.isVisible = checkBoxOption.isOtherOptionAllowed
        }
    }

    override fun onAttachedToWindow() {

    }

    override fun onDetachedFromWindow() {

    }
}