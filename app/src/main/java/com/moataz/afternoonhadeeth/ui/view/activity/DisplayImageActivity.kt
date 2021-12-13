package com.moataz.afternoonhadeeth.ui.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ablanco.zoomy.Zoomy
import com.bumptech.glide.Glide
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.databinding.ActivityDisplayImagesBinding
import com.moataz.afternoonhadeeth.utils.helper.Views.hideStatusBar
import com.moataz.afternoonhadeeth.utils.helper.Views.intiViews

class DisplayImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayImagesBinding
    val PERMISSION_WRITE = 0


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()
        back()
        loadImage()
        shareImage()
        zoomIn()
    }

    private fun initializeView() {
        intiViews(window)
        hideStatusBar(window)
    }

    private fun back() {
        binding.buttonBackOtherImages.setOnClickListener { finish() }
    }

    private fun loadImage() {
        val intent = intent
        if (intent.hasExtra("imageUrl")) {
            val url = intent.getStringExtra("imageUrl")
            Glide
                .with(this)
                .load(url)
                .placeholder(R.drawable.folder_loading_image)
                .into(binding.imageDisplayOtherImages)
        }
    }

    private fun zoomIn() {
        val builder = Zoomy.Builder(this).target(binding.imageDisplayOtherImages)
        builder.register()
    }

    private fun shareImage() {
        binding.buttonShareOtherImages.setOnClickListener {
            if (checkPermission()) {
                val bitmapDrawable = binding.imageDisplayOtherImages.drawable as BitmapDrawable
                val bitmap = bitmapDrawable.bitmap
                val bitmapPath = MediaStore.Images.Media.insertImage(
                    contentResolver,
                    bitmap,
                    "حمل تطبيق حديث الغروب للمزيد من الأحاديث الشريفه",
                    null
                )
                val bitmapUri = Uri.parse(bitmapPath)
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "image/jpg/png"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "حمل تطبيق حديث الغروب للمزيد من الأحاديث الشريفه"
                )
                shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
                startActivity(Intent.createChooser(shareIntent, ""))
            }
        }
    }

    private fun checkPermission(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_WRITE
            )
            false
        }
    }
}