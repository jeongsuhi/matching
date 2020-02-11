package com.matching.photo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.matching.R
import com.matching.photo.recycler.ConfirmPagerAdapter

class ConfirmPhotosActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_photos)

        val images: List<Uri> = intent.extras!!.getStringArray(INTENT_KEY_IMAGES)?.map {
            try {
                Uri.parse(it)
            } catch (e: Throwable) {
                Uri.EMPTY
            }
        } ?: emptyList()

        setupHeader()
        setupView(images)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

    private fun setupHeader() {
        setActionBar(findViewById(R.id.header_toolbar))

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun setupView(images: List<Uri>) {
        val adapter = ConfirmPagerAdapter(
            fragmentManager = supportFragmentManager,
            onViewChanged = { index ->
                // header title
                findViewById<android.widget.Toolbar>(R.id.header_toolbar).title = "$index/${images.count()}"
            }
        )

        // header
        findViewById<android.widget.Toolbar>(R.id.header_toolbar).apply {
            // to do list
        }

        // pager
        findViewById<ViewPager>(R.id.view_pager).apply {
            this.adapter = adapter
        }
        adapter.updatePhotos(images)
    }

    companion object {
        const val INTENT_KEY_IMAGES = "INTENT_KEY_IMAGES"

        fun start(activity: Activity, images: List<String>) {
            activity.startActivity(
                Intent(activity, ConfirmPhotosActivity::class.java).apply {
                    putExtra(INTENT_KEY_IMAGES, images.toTypedArray())
                }
            )
        }
    }
}