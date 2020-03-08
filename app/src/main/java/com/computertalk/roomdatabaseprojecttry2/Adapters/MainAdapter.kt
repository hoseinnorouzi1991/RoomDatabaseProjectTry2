package com.computertalk.roomdatabaseprojecttry2.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.computertalk.roomdatabaseprojecttry2.AddOrEditActivity
import com.computertalk.roomdatabaseprojecttry2.R
import com.computertalk.roomdatabaseprojecttry2.RoomLayer.Entities.UserEntity
import com.computertalk.roomdatabaseprojecttry2.Utilities.ImageUtil
import kotlinx.android.synthetic.main.model_main_list.view.*
import java.util.zip.Inflater

class MainAdapter (val context: Context,val list:List<UserEntity>):RecyclerView.Adapter<MainAdapter.MyHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.model_main_list,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val view = holder.itemView
        val user  = list[position]

        val card = view.mo_main_card
        val username = view.mo_main_user_name
        val userImage = view.mo_main_user_img

        card.setBackgroundResource(R.drawable.bg_main_list)
        username.text = user.displayName

        if(user.image != null)
        {
            val imageFile = ImageUtil.loadFilePrivate(context,user.image!!)
            userImage.setImageURI(imageFile.toUri())
        }
        else
        {
            userImage.setImageResource(R.drawable.user)
        }

        card.setOnClickListener{
            val open  = Intent(context, AddOrEditActivity::class.java)
            open.putExtra("id",user.id)
            context.startActivity(open)
        }
    }

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}