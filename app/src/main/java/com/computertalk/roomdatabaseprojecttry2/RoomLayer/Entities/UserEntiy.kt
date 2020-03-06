package com.computertalk.roomdatabaseprojecttry2.RoomLayer.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(@PrimaryKey(autoGenerate = true) var id:Int,
                      @ColumnInfo(name = "display_name") var displayName:String,
                      @ColumnInfo(name = "email") var email:String?,
                      @ColumnInfo(name = "phone") var phone:String,
                      @ColumnInfo(name = "user_image") var image:String? = null){
    constructor(displayName: String,email: String?,phone: String,image: String?):this(0,displayName,email,phone,image)
}