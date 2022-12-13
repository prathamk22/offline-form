package com.offline.form.builder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.offline.form.builder.databinding.FragmentHomeBinding
import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.NumberInputType
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class HomeFragment : Fragment() {

    fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Name",
                hint = "please enter your name",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Email",
                hint = "please enter your Email address",
                singleLineTextType = SingleLineTextType.EMAIL_ADDRESS,
                errorMessage = "Please provide a valid email address"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Phone number",
                hint = "please provide your phone number",
                numberInputType = NumberInputType.PHONE_NUMBER,
                errorMessage = "Please provide a valid phone number"
            )
        )
        return forms
    }

    fun getSection2FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "College Name",
                hint = "please enter your name",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Your score in college",
                hint = "example: 7.6",
                numberInputType = NumberInputType.NUMBER,
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }


    /*private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private val questionAdapter: QuestionAdapter by lazy {
        QuestionAdapter(homeViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.vm = homeViewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = questionAdapter
            questionAdapter.submitList(homeViewModel.questions)
        }
        binding.submitData.setOnClickListener {
            if (it.isEnabled) {
                homeViewModel.insertData()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}