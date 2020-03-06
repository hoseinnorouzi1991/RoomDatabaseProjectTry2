package com.computertalk.roomdatabaseprojecttry2.RoomLayer.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.Doa.UserDao
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.Entities.UserEntity

@Database(entities = arrayOf(UserEntity::class) , version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun users() : UserDao

    companion object {

        private var instance:AppDatabase? = null

        fun getInstance(constext:Context) : AppDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(constext , AppDatabase::class.java , "Room_db2")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }

    }

}
