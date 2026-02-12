package com.coderroots.interncomposeclass.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long? =0L,
    var name: String?=null,
    var rollNo: String? =null
)