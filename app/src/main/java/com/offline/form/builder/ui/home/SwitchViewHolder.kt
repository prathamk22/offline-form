package com.offline.form.builder.ui.home

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.offline.form.builder.databinding.SwitchItemBinding
import com.offline.form.builder.databinding.SwitchMainItemBinding
import com.offline.form.builder.utils.OptionType
import com.offline.form.builder.utils.OptionTypeEnum
import com.offline.form.builder.utils.Question

class SwitchViewHolder(
    private val binding: SwitchItemBinding,
    private val homeViewModel: HomeViewModel
) : RecyclerView.ViewHolder(binding.root), OfflineViewHolder {

    override fun onBind(item: Question) {
        if (item.optionType != OptionTypeEnum.Switch)
            return
        with(binding) {
            questionTitle.text = item.id + " " + item.question.plus(if (item.isOptional) " ( Optional )" else "")
            val checkBoxOption = (item.options.firstOrNull() as? OptionType.CheckBox) ?: return
            checkBoxesHolder.removeAllViews()
            checkBoxOption.checkboxItems.forEach {
                val checkBoxItem = SwitchMainItemBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    null,
                    false
                )
                checkBoxItem.checkBox.id = View.generateViewId()
                checkBoxItem.checkBox.text = it.optionTitle
                checkBoxItem.checkBox.isChecked = homeViewModel.getAnsForCheckboxIfAvailable(item.id, it.id)
                checkBoxItem.checkBox.setOnClickListener { view->
                    val checkboxText = checkBoxItem.checkBox.text
                    checkBoxOption.checkboxItems.first { checkboxItem ->
                        checkboxItem.optionTitle == checkboxText
                    }.let {
                        if (item.validate.isValid(checkboxText)) {
                            homeViewModel.valueEnteredInCheckbox(item.id, it.id, checkBoxItem.checkBox.isChecked)
                        } else {
                            homeViewModel.clearValue(item.id)
                        }
                    }
                }
                checkBoxesHolder.addView(checkBoxItem.root)
            }
        }
    }

    override fun onAttachedToWindow() {

    }

    override fun onDetachedFromWindow() {

    }
}
