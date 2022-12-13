package com.offline.form.builder.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.offline.form.builder.databinding.FragmentGalleryBinding
import com.offline.form.builder.ui.home.HomeViewModel
import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form
import com.pradeep.form.simple_form.presentation.FormSubmitCallback

class AddPeopleFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    private val userdataList = mutableListOf<List<Form>>()

    private val callback = object : FormSubmitCallback {
        override fun onFormSubmitted(forms: List<Form>) {
            userdataList.add(forms)
            binding.simpleForm.setData(getSection1FormData(), this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.simpleForm.setData(getSection1FormData(), callback)
        binding.submitList.setOnClickListener {
            viewModel.submitData(arguments?.getString("formKey", "") ?: "", userdataList)
        }
    }

    private fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Name",
                hint = "Please enter your name",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Enter your gender",
                hint = "Please enter your gender",
                singleLineTextType = SingleLineTextType.EMAIL_ADDRESS,
                errorMessage = "Please provide a valid email address"
            )
        )
        return forms
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}