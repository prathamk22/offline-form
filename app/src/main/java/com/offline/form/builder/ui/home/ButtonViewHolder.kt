package com.offline.form.builder.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.offline.form.builder.databinding.ButtonItemBinding
import com.offline.form.builder.utils.OptionType
import com.offline.form.builder.utils.OptionTypeEnum
import com.offline.form.builder.utils.Question

class ButtonViewHolder(private val binding: ButtonItemBinding) :
    RecyclerView.ViewHolder(binding.root), OfflineViewHolder {

    override fun onBind(item: Question) {
        if (item.optionType != OptionTypeEnum.Button)
            return

        with(binding) {
            questionTitle.text = item.question
            val buttonData = (item.options.firstOrNull() as? OptionType.Button) ?: return
            mainBtn.text = buttonData.buttonText
            mainBtn.setOnClickListener {
                buttonData.buttonAction.doAction(it, item)
            }
        }

    }

    override fun onAttachedToWindow() {

    }

    override fun onDetachedFromWindow() {

    }

}