package com.offline.form.builder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.offline.form.builder.databinding.FragmentHouseHoldAssetsBinding
import com.offline.form.builder.ui.home.HomeViewModel
import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form
import com.pradeep.form.simple_form.presentation.FormSubmitCallback

class HouseHoldAssetsFragment : Fragment() {

    private var _binding: FragmentHouseHoldAssetsBinding? = null
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
        _binding = FragmentHouseHoldAssetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.simpleForm.setData(getSection1FormData(), callback)
        binding.submitList.setOnClickListener {
            viewModel.submitC6Data(arguments?.getString("formKey", "") ?: "", userdataList)
        }
    }

    private fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Production Asset",
                choices = listOf(
                    "Ox Plough",
                    "Ox cart",
                    "Ox harrow",
                    "Ox ridger",
                    "Ox cultivator",
                    "Hoe / pick",
                    "Spade/shovel",
                    "Axe",
                    "Panga",
                    "Motorized blowers",
                    "Motorized chain saws",
                    "Knapsack sprayer",
                    "Wheel barrow",
                    "Carpentry tools",
                    "Pit saw",
                    "Fishing nets",
                    "Fishing boat",
                    "Canoe",
                    "Bark bee hives",
                    "Improved bee hives",
                    "Chigayo",
                    "Sheller",
                    "Oil Expeller",
                    "Bicycle",
                    "Motor cycle",
                    "Car",
                    "Truck",
                    "Motor vehicle",
                    "Radio",
                    "Television",
                    "Video player",
                    "Refrigerator",
                    "Pressing Iron",
                    "Cellular phone",
                    "Solar system",
                    "Sewing machine",
                    "Brazier (Mbaula)",
                    "Hunting gun",
                    "Table",
                    "Sofa",
                    "Bed",
                    "Mattress",
                    "Houses / huts",
                    "Traditional hut (Poles, Mud, Grass, thatched)",
                    "Improved traditional house (Unbrunt bricks, grass, thatched)",
                    "Moder house (Burnt bricks/cement blocks with corrugated roofing)",
                ),
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Who Owns it",
                choices = listOf("M", "F"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Total number",
                hint = "Enter total numbers",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Total number acquired in last 12 months",
                hint = "Enter total numbers",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Purchase old price item",
                hint = "Enter old price",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Purchase old price item",
                hint = "Enter old price",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Purchase new price item",
                hint = "Enter new price",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        return forms
    }

}