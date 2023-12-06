package com.offline.form.builder.ui.home

import android.util.Log
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
        if (item.optionType != OptionTypeEnum.CHECK_BOX){
            Log.e("TAG", "onBind: returning from here")
            return
        }
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
                    checkBoxItems.addView(checkBoxItem.root)
                } else {
                    val checkBoxItem = CheckboxMainItemBinding.inflate(
                        LayoutInflater.from(binding.root.context),
                        null,
                        false
                    )
                    checkBoxItem.radioBtn.text = it.optionTitle
                    checkBoxItem.radioBtn.tag = it.id
                    checkBoxItem.radioBtn.isChecked = it.id == homeViewModel.getAnsIfAvailable(item.id)
                    checkBoxItems.addView(checkBoxItem.root)
                }
            }
            checkBoxItems.setOnCheckedChangeListener { _, i ->
                val checkboxItem = checkBoxItems.findViewById<RadioButton?>(i)
                    ?: return@setOnCheckedChangeListener
                val checkboxText = checkboxItem.text
                val checkboxTag = checkboxItem.tag
                checkBoxOption.checkboxItems.first {
                    it.id == checkboxTag
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