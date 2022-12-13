package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class HouseHoldAssetsFragment : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitC6Data(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
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
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Who Owns it",
                choices = listOf("M", "F"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "Total number",
                hint = "Enter total numbers",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "Total number acquired in last 12 months",
                hint = "Enter total numbers",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "Purchase old price item",
                hint = "Enter old price",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "Purchase old price item",
                hint = "Enter old price",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",
            )
        )
        forms.add(
            Form(
                isMandatory = true,
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