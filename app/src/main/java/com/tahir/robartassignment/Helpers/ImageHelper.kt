package com.tahir.robartassignment.Helpers

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.util.*

object ImageHelper {

    fun BITMAP_RESIZER(bitmap: Bitmap?, newWidth: Int, newHeight: Int): Bitmap {
        val scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)
        val ratioX = newWidth / bitmap!!.width.toFloat()
        val ratioY = newHeight / bitmap!!.height.toFloat()
        val middleX = newWidth / 2.0f
        val middleY = newHeight / 2.0f
        val scaleMatrix = Matrix()
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
        val canvas = Canvas(scaledBitmap)
        canvas.setMatrix(scaleMatrix)
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(
            bitmap,
            middleX - bitmap.width / 2,
            middleY - bitmap.height / 2,
            Paint(Paint.FILTER_BITMAP_FLAG)
        )
        return scaledBitmap
    }

    fun getImageUri(inContext: Context, inImage: Bitmap?, title: String): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage!!.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.getContentResolver(),
            inImage,
            title,
            null
        )
        return Uri.parse(path)
    }

    // Method to save an image to external storage
    fun saveImageToExternalStorage(bitmap: Bitmap, context: Context): Uri {


        val path = Environment.getExternalStorageDirectory().toString()
        // Create a file to save the image
        val dir: File = File(path + "/formaula_images/")
        if (!dir.exists()) {
            dir.mkdir()
        }
        val file = File(dir, "${UUID.randomUUID()}.jpg")
        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)
            // Compress the bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            // Flush the output stream
            stream.flush()
            // Close the output stream
            stream.close()
            UIHelper.showLongToastInCenter(context, "Image saved successful.")
            //toast(“Image saved successful.“)
        } catch (e: IOException) { // Catch the exception
            e.printStackTrace()
            UIHelper.showLongToastInCenter(context, "Error occured.")
        }
        // Return the saved image path to uri
        return Uri.parse(file.absolutePath)
    }

    fun shareImage(
        appCompatActivity: AppCompatActivity,
        textBody: String?,
        fileUri: Uri?
    ) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, if (!TextUtils.isEmpty(textBody)) textBody else "")
        if (fileUri != null) {
            intent.putExtra(Intent.EXTRA_STREAM, fileUri)
            intent.type = "image/*"
        }
        try {
            appCompatActivity.startActivity(
                Intent.createChooser(
                    intent,
                    "Share formula image via "
                )
            )
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()

        }
    }
}