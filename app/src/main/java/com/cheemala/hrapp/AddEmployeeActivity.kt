package com.cheemala.hrapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cheemala.hrapp.application.util.AppUtils
import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.databinding.ActivityAddEmployeeBinding
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

class AddEmployeeActivity : AppCompatActivity() {
    private lateinit var bindingObj: ActivityAddEmployeeBinding
    private var newEmployee: Employee = Employee(null, "", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingObj = DataBindingUtil.setContentView(this, R.layout.activity_add_employee)

        if (intent.hasExtra(getString(R.string.selected_emp_data_text))) {
            bindingObj.titleHdngTxt.text = getString(R.string.edit_emp_text)
            bindingObj.saveBtn.text = getString(R.string.edit_emp_text)
            Log.i("AddEmployeeActivity_", "source_is_to_be_edited")
            val selectedEmployee =
                getSerializable(
                    intent,
                    getString(R.string.selected_emp_data_text),
                    Employee::class.java
                )
            newEmployee = selectedEmployee
            bindingObj.employee = selectedEmployee
        } else {
            bindingObj.titleHdngTxt.text = getString(R.string.add_emp_text)
            bindingObj.saveBtn.text = getString(R.string.add_emp_text)
            Log.i("AddEmployeeActivity_", "source_is_to_be_added")
            bindingObj.employee = newEmployee
        }

        bindingObj.saveBtn.setOnClickListener {

            val isValidEmployee = isValidEmp()
            if (!isValidEmployee) {
                AppUtils.showSnackBar(
                    bindingObj.adEmpContainer,
                    getString(R.string.valid_emp_details_warning_text),
                    Snackbar.LENGTH_LONG
                )
                return@setOnClickListener
            }
            val resultIntent = Intent()
            var actionTxt = if (bindingObj.saveBtn.text.equals(getString(R.string.add_emp_text))) {
                "${getString(R.string.add_emp_text)}"
            } else {
                "${getString(R.string.edit_emp_text)}"
            }
            resultIntent.putExtra(
                getString(R.string.emp_details_text), newEmployee
            )
            resultIntent.putExtra(
                getString(R.string.action_type_text), actionTxt
            )
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun isValidEmp(): Boolean {
        val nameVal = bindingObj.employee?.empName
        val ageVal = bindingObj.employee?.empAge
        val genderVal = bindingObj.employee?.empGender
        val desgnVal = bindingObj.employee?.empDesignation

        if (nameVal?.isEmpty() == true || ageVal?.isEmpty() == true || genderVal?.isEmpty() == true || desgnVal?.isEmpty() == true)
            return false
        return true
    }

    fun <T : Serializable?> getSerializable(intent: Intent, key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(key, m_class)!!
        else
            intent.getSerializableExtra(key) as T
    }

    fun fetchGenderInput(view: View) {
        when (view.id) {
            R.id.radio_male -> {
                bindingObj.employee?.empGender = getString(R.string.male_text)
            }
            R.id.radio_female -> {
                bindingObj.employee?.empGender = getString(R.string.female_text)
            }
        }

    }
}