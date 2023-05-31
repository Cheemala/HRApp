package com.cheemala.hrapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheemala.hrapp.application.GlobalSpace
import com.cheemala.hrapp.application.util.AppUtils
import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.databinding.ActivityMainBinding
import com.cheemala.hrapp.presentation.adapter.EmployeeAdapter
import com.cheemala.hrapp.presentation.viewmodel.MainActivityViewModel
import com.cheemala.hrapp.presentation.viewmodel.MainActivityViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var bindingObj: ActivityMainBinding
    private lateinit var empAdapter: EmployeeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingObj = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as GlobalSpace).getAppComponentInstance().inject(this)
        mainActivityViewModel =
            ViewModelProvider(this, mainActivityViewModelFactory)[MainActivityViewModel::class.java]
        bindingObj.fabBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEmployeeActivity::class.java)
            getResult.launch(intent)
        }
        initialiseRecyclerVw()
        displayEmployeeData()
        setUpSearchView()
    }

    private fun setUpSearchView() {
        val onSearchViewListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    val filteredEmpList = mainActivityViewModel.searchList(query)
                    empAdapter.setEmployeeList(filteredEmpList)

                }
                return false
            }

            override fun onQueryTextChange(queryText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    val filteredEmpList = mainActivityViewModel.searchList(queryText)
                    empAdapter.setEmployeeList(filteredEmpList)

                }
                return false
            }

        }

        val onSearchViewClosedListener = SearchView.OnCloseListener {
            val originalEmpList = mainActivityViewModel.allEmployeeDta.value
            if (originalEmpList != null)
                empAdapter.setEmployeeList(originalEmpList)
            false
        }

        bindingObj.empSrchVw.setOnQueryTextListener(onSearchViewListener)
        bindingObj.empSrchVw.setOnCloseListener(onSearchViewClosedListener)
    }

    private fun initialiseRecyclerVw() {
        empAdapter = EmployeeAdapter()
        bindingObj.empRclcrVw.apply {
            adapter = empAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        empAdapter.onItemClickListener = { employee ->
            val intent = Intent(this@MainActivity, AddEmployeeActivity::class.java)
            intent.putExtra(getString(R.string.selected_emp_data_text), employee)
            getResult.launch(intent)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val empItem = empAdapter.getEmployee(position)
                mainActivityViewModel.deleteEmployee(empItem)
                AppUtils.showSnackBar(
                    bindingObj.empMainLyout,
                    getString(R.string.emp_delete_text),
                    Snackbar.LENGTH_LONG
                )
            }

        }).apply {
            attachToRecyclerView(bindingObj.empRclcrVw)
        }
    }

    private fun displayEmployeeData() {
        mainActivityViewModel.allEmployeeDta.observe(this, Observer {
            empAdapter.setEmployeeList(it)
        })
    }

    // Receiver
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {

                if (it.data?.getStringExtra(getString(R.string.action_type_text))
                        .equals(getString(R.string.add_emp_text))
                ) {
                    val newEmployee =
                        it.data?.getSerializableExtra(getString(R.string.emp_details_text)) as Employee
                    mainActivityViewModel.addEmployee(
                        employee = newEmployee
                    )
                    AppUtils.showSnackBar(
                        bindingObj.empMainLyout,
                        getString(R.string.em_eded_text),
                        Snackbar.LENGTH_LONG
                    )
                } else {

                    val editedEmployee =
                        it.data?.getSerializableExtra(getString(R.string.emp_details_text)) as Employee
                    mainActivityViewModel.updateEmployee(
                        employee = editedEmployee
                    )
                    AppUtils.showSnackBar(
                        bindingObj.empMainLyout,
                        getString(R.string.em_update_text),
                        Snackbar.LENGTH_LONG
                    )

                }
            }
        }
}