package com.offline.form.builder.ui.home

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.RecyclerView
import com.offline.form.builder.databinding.StringItemBinding
import com.offline.form.builder.utils.OptionType
import com.offline.form.builder.utils.OptionTypeEnum
import com.offline.form.builder.utils.Question

class StringViewHolder(
    private val binding: StringItemBinding,
    private val homeViewModel: HomeViewModel,
    val localTextWatcher: LocalTextWatcher
) : RecyclerView.ViewHolder(binding.root), OfflineViewHolder {

    override fun onBind(item: Question) {
        if (item.optionType != OptionTypeEnum.INPUT)
            return
        with(binding) {
            questionTitle.text = item.question
            val inputOption = (item.options.firstOrNull() as? OptionType.InputField) ?: run {
                textInputEditText.setText("")
                return
            }
            textInputEditText.inputType = inputOption.inputType
            val ansText = homeViewModel.getAnsIfAvailable(item.id)
            if (ansText.isNullOrEmpty()) {
                textInputEditText.setText("")
            } else {
                textInputEditText.setText(ansText)
            }
            textInputEditText.tag = item.id
            textInputEditText.addTextChangedListener(localTextWatcher)
        }
    }

    override fun onAttachedToWindow() {

    }

    override fun onDetachedFromWindow() {

    }
}

class LocalTextWatcher(
    private val homeViewModel: HomeViewModel
): TextWatcher{

    private var item: Question? = null

    fun updateItem(item: Question){
        this.item = item
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (item == null)
            return
        val text = p0?.toString() ?: return
        if (text.isEmpty()){
            return
        }
        if (item?.validate?.isValid(text) == true) {
            homeViewModel.valueEntered(item!!.id, text)
        } else {
            homeViewModel.clearValue(item!!.id)
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }

}