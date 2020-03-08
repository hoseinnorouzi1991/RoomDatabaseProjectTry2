package com.computertalk.roomdatabaseprojecttry2.RoomLayer.Doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.Entities.UserEntity

@Dao
interface UserDao {

    @Query("select * from users")
    fun getAll(): List<UserEntity>

    @Query("select * from users where id = :id")
    fun getSingle(id:Int):UserEntity

    @Insert
    fun insert(user:UserEntity)

    @Update
    fun update(user: UserEntity)

}