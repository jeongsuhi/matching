package com.matching.photo.recycler

import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.matching.photo.SinglePhotoFragment

class ConfirmPagerAdapter(
    private val fragmentManager: FragmentManager,
    private val onViewChanged: (Int) -> Unit
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var photos = emptyList<Uri>()

    override fun getItem(position: Int): Fragment {
        onViewChanged(position + 1)

        return SinglePhotoFragment.newInstance(photo = photos[position])
    }

    override fun getCount(): Int = photos.count()

    fun updatePhotos(photos: List<Uri>) {
        this.photos = photos
        notifyDataSetChanged()
    }

}