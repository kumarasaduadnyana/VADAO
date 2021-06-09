package com.example.ocr

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

@Suppress("DEPRECATION")
class ScanData : AppCompatActivity() {
    
    private val fileName = "gambar.jpg"
    private val requestCode = 42
    private lateinit var photoFile : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_data)

        val takePicture : Button = findViewById(R.id.takePicture)
        takePicture.setOnClickListener {
            val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(fileName)
            val fileProvider = FileProvider.getUriForFile(this, "com.example.ocr", photoFile)
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            if (pictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(pictureIntent, requestCode)
            } else {
                Toast.makeText(this, "Unable to open Camera", Toast.LENGTH_SHORT). show()
            }
        }

        val uploadPicture : Button = findViewById(R.id.uploadPicture)
        uploadPicture.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)

            if (uploadPicture.isClickable) {
                Toast.makeText(this, "Uploading Picture Success", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private  fun getPhotoFile(fileName :  String) : File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == requestCode && resultCode == Activity.RESULT_OK) {
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            val imagePreview : ImageView = findViewById(R.id.imagePreview)
            imagePreview.setImageBitmap(takenImage)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
