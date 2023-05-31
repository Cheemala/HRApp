package com.cheemala.hrapp.data.model

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employee_id")
    var empId: Int?,
    @ColumnInfo(name = "employee_name")
    var empName: String?,
    @ColumnInfo(name = "employee_age")
    var empAge: String?,
    @ColumnInfo(name = "employee_gender")
    var empGender: String?,
    @ColumnInfo(name = "employee_designation")
    var empDesignation: String?
): Serializable, BaseObservable()
