package com.computertalk.roomdatabaseprojecttry2.Utilities

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*

object SaveImage {

    @Synchronized
    fun saveImage(context: Context, data: Uri): String? {

        try {
            val imageName = UUID.randomUUID().toString() + "." +  uriToExtension(context,data)
            context.openFileOutput(imageName, Context.MODE_PRIVATE).write(uriToBytes(context, data))
            return imageName
        }catch (e: Exception)
        {
            return null
        }
        /*val imageFile = File(path)

        if (!imageFile.exists()) {
            return null
        }

        var split = imageFile.name.split(".")
        val imageName = UUID.randomUUID().toString() + "." + split[(split.size) - 1]
        context.openFileOutput(imageName, Context.MODE_PRIVATE).write(imageFile.readBytes())
        return imageName*/

    }
    fun uriToBytes(context: Context, data: Uri):ByteArray
    {
        val ist = context.contentResolver.openInputStream(data)
        val bOut = ByteArrayOutputStream()

        bOut.write(ist?.readBytes())

        return bOut.toByteArray()
    }

    fun uriToExtension(context: Context, data: Uri):String{
        val cr = context.contentResolver
        val mime = MimeTypeMap.getSingleton()

        val extension = mime.getExtensionFromMimeType(cr.getType(data))
        return (if(extension != null )  extension else  "")
    }
}
