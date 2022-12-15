package com.offline.form.builder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            val count = arguments?.getInt("count", Int.MAX_VALUE) ?: Int.MAX_VALUE
            if (userdataList.size >= count){
                Toast.makeText(requireContext(), "Only $count entries are allowed", Toast.LENGTH_SHORT).show()
                return
            }
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
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (userdataList.size > 0) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Do you want to add data?")
                    .setCancelable(true)
                    .setMessage("Do you want to add these items into the data?")
                    .setPositiveButton("Add and Exit") { d, i ->
                        onSubmitListCalled()
                        findNavController().navigateUp()
                    }.setNegativeButton("Cancel and Exit") { d, i ->
                        findNavController().navigateUp()
                    }.create().show()
            } else {
                findNavController().navigateUp()
            }
        }
        binding.simpleForm.setData(getSection1FormData(), callback)
    }

    abstract fun onSubmitListCalled()

    abstract fun getSection1FormData(): List<Form>


}