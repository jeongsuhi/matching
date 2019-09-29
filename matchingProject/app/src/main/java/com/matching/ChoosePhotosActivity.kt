package com.matching

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView

class ChoosePhotosActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_choose_photos)

        val images: List<Uri>
        if (savedInstanceState == null) {
            images = intent.extras.getParcelableArray(INTENT_KEY_IMAGES).toList() as List<Uri>

        } else {
            images = savedInstanceState.getParcelableArray(INTENT_KEY_IMAGES).toList() as List<Uri>
        }

        val imgView = findViewById<ImageView>(R.id.image_view)
        images.forEach {
            imgView.setImageURI(it)
        }
    }

    companion object {
        const val INTENT_KEY_IMAGES = "INTENT_KEY_IMAGES"

        fun start(activity: Activity, images: List<Uri>) {
            activity.startActivity(
                Intent(activity, ChoosePhotosActivity::class.java).putExtra(INTENT_KEY_IMAGES, images.toTypedArray())
            )

        }
    }
}