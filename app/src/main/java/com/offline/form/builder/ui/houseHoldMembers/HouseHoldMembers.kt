package com.offline.form.builder.ui.houseHoldMembers

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.offline.form.builder.ui.NewBaseTableFragment
import com.offline.form.builder.utils.TableData
import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class HouseHoldMembers : NewBaseTableFragment() {

    override fun getSheetName() = "1.3"

    override fun getColumnNames(): List<String> {
        return listOf(
            "व्यस्क पुरुष",
            "वयस्क महिलाएं",
            "बच्चे (पु०)",
            "बच्चे (स्त्री०)",
            "कुल",
        )
    }

    override fun getSection1FormData(): List<Form> {
        return getColumnNames().map {
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = it,
                hint = "कृपया सदस्यों की संख्या बताये",
                errorMessage = "कृपया सदस्यों की संख्या बताये"
            )
        }
    }
}
