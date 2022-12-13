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

                question = "Name of the household member",
                hint = "please enter your name",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Gender",
                choices = listOf("1. Male", "2. Female"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,

                question = "Age",
                hint = "in yrs",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",

            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Education Level",
                hint = "1=No Formal Education; 2 = Less than Grade 5; 3 = Less than grade 10; 4 = Less than Grade 12; 5= College Student; 6 = University Undergraduate Student; 7= Tertiary Certificate; Diploma; 8= Bachelors Degree; 9 = Masters Degree and Above.",
                choices = listOf("1","2","3","4","5","6","7","8","9"),
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Actively involved in Farming",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Earning member of HH",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Suffered from COVID-19 at any time",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Suffered from HIV AIDS",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Suffered from any other Health issue",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        return forms
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}