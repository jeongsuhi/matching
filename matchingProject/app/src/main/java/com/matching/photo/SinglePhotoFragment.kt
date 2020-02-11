package com.matching.photo

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.matching.R

class SinglePhotoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_single_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoUri = arguments!!.getParcelable<Uri>(ARG_KEY_PHOTO)

        // image 적용
        view.findViewById<ImageView>(R.id.image).apply {
            setImageURI(photoUri)
        }
    }

    companion object {
        private const val ARG_KEY_PHOTO = "ARG_KEY_PHOTO"

        fun newInstance(photo: Uri): SinglePhotoFragment = SinglePhotoFragment().apply {
            this.arguments = Bundle().apply {
                putParcelable(ARG_KEY_PHOTO, photo)
            }
        }
    }

}