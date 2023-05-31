package com.cheemala.hrapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.cheemala.hrapp.data.db.EmpDao
import com.cheemala.hrapp.data.db.EmployeeDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Context):EmployeeDB{
        return Room.databaseBuilder(context,EmployeeDB::class.java,"employeedb").build()
    }

    @Singleton
    @Provides
    fun providesEmpDao(employeeDB: EmployeeDB):EmpDao{
        return employeeDB.empDao()
    }

}