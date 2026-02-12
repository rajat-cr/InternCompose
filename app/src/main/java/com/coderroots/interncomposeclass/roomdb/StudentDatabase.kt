package com.coderroots.interncomposeclass.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentEntity::class], version = 1,exportSchema = false)
abstract class StudentDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao
    companion object {
        private var INSTANCE: StudentDatabase? = null
        fun getInstance(context: Context): StudentDatabase? {
            synchronized(this) {
                println("Check GetInstance of Room")
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        StudentDatabase::class.java,
                        "student_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}

