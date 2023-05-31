package com.cheemala.hrapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheemala.hrapp.data.model.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDB: RoomDatabase() {
    abstract fun empDao():EmpDao
}