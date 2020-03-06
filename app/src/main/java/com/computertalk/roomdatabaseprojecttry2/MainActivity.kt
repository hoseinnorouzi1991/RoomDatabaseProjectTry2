package com.computertalk.roomdatabaseprojecttry2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.DB.AppDatabase
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.Entities.UserEntity
import com.computertalk.roomdatabaseprojecttry2.Utilities.SaveImage
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase
    val pickRequest = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        db = AppDatabase.getInstance(this)

        listeners()

    }

    private fun listeners() {
        fab.setOnClickListener { view ->

            /*val user1 = UserEntity("mohammadhoseinNorouzi","mh.norouzi89@gmail.com","09398299779")
            db.users().insert(user1)*/

            openAddOrEdit()
            //advancedAddUser()

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        test2?.setOnClickListener {
            val users = db.users().getAll()
            val builder: StringBuilder = StringBuilder()
            for (user in users) {
                builder.append(user.displayName)
                builder.append("\n")
            }
            test2.text = builder.toString()
        }
    }

    fun advancedAddUser() {
        val picker = Intent(Intent.ACTION_GET_CONTENT)
        picker.setType("image/*")
        startActivityForResult(picker, pickRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        val newImageName =  SaveImage.saveImage(this,data!!.data!!.path!!)
        if (requestCode == pickRequest && resultCode == Activity.RESULT_OK) {

            val uImage = data!!.data!!
            val saveImage = SaveImage.saveImage(this,uImage)

            addUser("mohammad hosein norouzi",null,"09398299779",saveImage)
//            addUser("mohammmad hosein norouzi",null,"09398299779",newImageName)
        }
    }

    fun addUser(name: String, email: String?, phone: String, image: String?) {
        db.users().insert(UserEntity(name, email, phone, image))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun openAddOrEdit(id:Int = 0)
    {
        val open  = Intent(this,AddOrEditActivity::class.java)
        if(id != 0)
            open.putExtra("id",id)
        startActivity(open)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
