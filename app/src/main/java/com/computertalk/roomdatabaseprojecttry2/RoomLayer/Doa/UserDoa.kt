package com.computertalk.roomdatabaseprojecttry2.RoomLayer.Doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.Entities.UserEntity

@Dao
interface UserDao {

    @Query("select * from users")
    fun getAll(): List<UserEntity>

    @Insert
    fun insert(user:UserEntity)
}