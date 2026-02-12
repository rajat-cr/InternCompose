package com.coderroots.interncomposeclass.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert
    suspend fun addStudent(studentEntity: StudentEntity)

    @Query("select * from StudentEntity")
    fun getStudents() : Flow<List<StudentEntity>>



}