package com.example.yeni.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
lateinit var intentToGalary:Intent
class izinler (val context: Context,val activity: Activity,val permissionLauncher: ActivityResultLauncher<String>,
               val activityResultLauncher2 :ActivityResultLauncher<Intent>,val view: View,val izinTürü:Int){
    fun izin(){

        if (izinTürü==0){
            intentToGalary = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        }
        else if (izinTürü==1){
            intentToGalary = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        }

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU) {
            if (izinTürü==0){
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_MEDIA_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.READ_MEDIA_AUDIO)) {
                    Snackbar.make(view, "izin lazım", Snackbar.LENGTH_LONG).setAction("ver") {
                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_AUDIO)

                    }.show()
                } else {
                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_AUDIO)

                }

            } else {

                activityResultLauncher2.launch(intentToGalary)
            }}
            else if (izinTürü==1){
                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.READ_MEDIA_IMAGES)) {
                        Snackbar.make(view, "izin lazım", Snackbar.LENGTH_LONG).setAction("ver") {
                            permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                        }.show()
                    } else {
                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                    }

                } else {

                    activityResultLauncher2.launch(intentToGalary)
                }
            }
        }else {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ) {
                    Snackbar.make(view, "izin lazım", Snackbar.LENGTH_LONG).setAction("ver") {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                    }.show()
                } else {
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                }

            } else {

                activityResultLauncher2.launch(intentToGalary)
            }
        }
    }
}