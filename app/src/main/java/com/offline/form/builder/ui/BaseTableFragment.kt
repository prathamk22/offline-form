package com.offline.form.builder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.offline.form.builder.databinding.FragmentHouseHoldAssetsBinding
import com.offline.form.builder.ui.home.HomeViewModel
import com.pradeep.form.simple_form.model.Form
import com.pradeep.form.simple_form.presentation.FormSubmitCallback

abstract class BaseTableFragment : Fragment() {

    private var _binding: FragmentHouseHoldAssetsBinding? = null
    protected val binding get() = _binding!!

    protected val viewModel: HomeViewModel by activityViewModels()

    protected val userdataList = mutableListOf<List<Form>>()

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
        _binding = FragmentHouseHoldAssetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.simpleForm.setData(getSection1FormData(), callback)
        binding.submitList.setOnClickListener {
            onSubmitListCalled()
        }
    }

    abstract fun onSubmitListCalled()

    abstract fun getSection1FormData(): List<Form>


}