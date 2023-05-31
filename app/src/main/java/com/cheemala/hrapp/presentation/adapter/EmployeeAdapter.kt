package com.cheemala.hrapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cheemala.hrapp.R
import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.databinding.EmpListItemBinding

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>(){

    private var employeeList = ArrayList<Employee>()
    var onItemClickListener : ((Employee) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bindingObj:EmpListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.emp_list_item,parent,false)
        return MyViewHolder(bindingObj)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(employeeList[position])
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun setEmployeeList(employeeLst:List<Employee>){
        employeeList.clear()
        employeeList.addAll(employeeLst)
        notifyDataSetChanged()
    }

    fun getEmployee(position: Int):Employee{
        return employeeList[position]
    }

    inner class MyViewHolder(private val empListItemBinding: EmpListItemBinding):RecyclerView.ViewHolder(empListItemBinding.root){
        fun bindView(employee: Employee){
            empListItemBinding.rvEmpName.text = employee.empName
            empListItemBinding.rvEmpAge.text = employee.empAge
            empListItemBinding.rvEmpGender.text = employee.empGender
            empListItemBinding.rvEmpDesignation.text = employee.empDesignation
            empListItemBinding.cardVw.setOnClickListener{
                onItemClickListener?.invoke(employee)
            }
        }
    }

}

