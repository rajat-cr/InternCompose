package com.coderroots.interncomposeclass.roomdb

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface StudentDao {

    @Insert
    fun addStudent(studentEntity: StudentEntity)


}