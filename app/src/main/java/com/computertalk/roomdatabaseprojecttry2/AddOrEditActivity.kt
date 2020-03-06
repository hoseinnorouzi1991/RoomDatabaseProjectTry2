package com.computertalk.roomdatabaseprojecttry2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.DB.AppDatabase
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.Entities.UserEntity
import com.computertalk.roomdatabaseprojecttry2.Utilities.SaveImage
import kotlinx.android.synthetic.main.activity_add_or_edit.*

class AddOrEditActivity : AppCompatActivity() {

    private var MODE_ADD = 1
    private var MODE_EDIT = 2
    private var mode = MODE_ADD
    private var id = 0

    lateinit var db : AppDatabase

    private val pickRequest = 1
    private  var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit)

        db = AppDatabase.getInstance(this)

        //MODE = intent.getIntExtra("mode",MODE_ADD)
        if(intent.getIntExtra("id",0)!=0) {
            mode = MODE_ADD
        }
        else
        {
            MODE_EDIT
            id = intent.getIntExtra("id",0)
        }
        init()
        listeners()
    }

    private fun listeners()
    {
        actv_aoe_submit.setOnClickListener{
            if(validatior())
            {
                val name = actv_aoe_name.text.toString()
                val email = actv_aoe_email.text.toString()
                val phone = actv_aoe_phone.text.toString()

                val imageName = saveImage()

                addUser(name,email,phone,imageName)
            }
        }

        actv_aoe_user_img.setOnClickListener {
            openPicker()
        }
    }

    private fun init()
    {
        when (mode){
            MODE_ADD -> {
                actv_aoe_user_img.setImageResource(R.drawable.user)
                actv_aoe_submit.text = "افزودن"

            }
            MODE_EDIT -> {
                loadImage()
                actv_aoe_submit.text = "ویرایش"
                //viewsIntializer()
            }
        }

    }

    private fun viewsIntializer(user: UserEntity){

    }

    fun loadImage() {

    }

    fun validatior(): Boolean
    {
        //TODO()// set write validator code

        return true
    }

    private fun openPicker()
    {
        val pick = Intent(Intent.ACTION_GET_CONTENT)
        pick.setType("image/*")
        startActivityForResult(pick,pickRequest)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == pickRequest && resultCode == Activity.RESULT_OK) {
            imageUri = data!!.data!!
            changeImage(imageUri!!)
        }
    }

    private fun changeImage(data: Uri)
    {
        actv_aoe_user_img.setImageURI(data)

    }

    private fun saveImage():String
    {
        var result:String? = null
        if(imageUri != null) {
            result = SaveImage.saveImage(this, imageUri!!)
        }
        return  result!!
    }

    private fun addUser(username:String , email:String?,phone:String,imageName:String?=null)
    {
        val user = UserEntity(username,email,phone,imageName)
        db.users().insert(user)
        finish()
    }
}

