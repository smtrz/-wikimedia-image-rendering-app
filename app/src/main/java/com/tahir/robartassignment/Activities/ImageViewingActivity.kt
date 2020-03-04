package com.tahir.robartassignment.Activities

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.tahir.robartassignment.Helpers.ImageHelper
import com.tahir.robartassignment.Helpers.ImageHelper.shareImage
import com.tahir.robartassignment.R
import kotlinx.android.synthetic.main.imageview_activity.*


class ImageViewingActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imageview_activity)
        init()
    }

    fun init() {
        share_formula.setOnClickListener(this)
        val intent = intent
        // recieving the bitmap from previous activity and now setting it up on the imageview after resizing.
        bitmap = (intent.getParcelableExtra<Parcelable>("BitmapImage") as Bitmap?)!!
        bitmap = ImageHelper.BITMAP_RESIZER(bitmap, 350, 150)
        expression_img.setImageBitmap(bitmap)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.share_formula -> {
// requesting permission
                requestStoragePermissions()
            }

        }

    }

    //request permission for Storage - in order to send the image we are saving it first in a folder and then retriving the ImageUri
    private fun requestStoragePermissions() {
// just making sure if the android version is marshmallow or above the we need to request run time permission.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val dialogMultiplePermissionsListener: MultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder

                    .withContext(this@ImageViewingActivity)
                    .withTitle("Storage Permission")
                    .withMessage("Please grant permission to enable image sharing.")
                    .withButtonText(android.R.string.ok)
                    .withIcon(R.mipmap.ic_launcher)

                    .build();

            val checkListener: MultiplePermissionsListener = object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report?.areAllPermissionsGranted()!!) {
                        shareImage(
                            this@ImageViewingActivity,
                            "Formula image",
                            ImageHelper.saveImageToExternalStorage(
                                bitmap,
                                this@ImageViewingActivity
                            )
                        )

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            }
            val composit: MultiplePermissionsListener = CompositeMultiplePermissionsListener(
                dialogMultiplePermissionsListener,
                checkListener
            )
            Dexter.withActivity(this@ImageViewingActivity)


                .withPermissions(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                    , android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )

                .withListener(composit)


                .withErrorListener {
                }

                .check()
        } else {
            // for devices below 6.0 we dont need to request run time permissions.
            ImageHelper.saveImageToExternalStorage(bitmap, this@ImageViewingActivity)


        }
    }


}