package com.matching

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.matching.photo.ConfirmPhotosActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == Activity.RESULT_OK
                && data != null
            ) {
                loadImages(data)
            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    // view 생성
    private fun setupView() {
        // start button
        findViewById<Button>(R.id.start_button).apply {
            setOnClickListener { navigateToGallery() }
        }
    }

    // 갤러리로 이동
    private fun navigateToGallery() {
        val intent = Intent(Intent.ACTION_PICK)
            .apply {
                type = "image/*" //이미지만 보이게
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            }

        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            PICK_IMAGE_REQUEST
        )
    }

    // data에서 절대경로로 이미지를 가져옴
    private fun loadImages(data: Intent) {
        val imageDatas: List<String> = data.clipData?.let {
            // 복수의 데이터
            when {
                (it.itemCount > 32) -> {
                    Toast.makeText(this@MainActivity, "사진은 32장까지 선택 가능합니다.", Toast.LENGTH_LONG).show()
                    return
                }
                (it.itemCount < 1) -> {
                    Toast.makeText(this@MainActivity, "사진이 0장 입니다.", Toast.LENGTH_LONG).show()
                    return
                }
                else -> {
                    mutableListOf<String>().apply {
                        for (i in 0 until it.itemCount) {
                            add(it.getItemAt(i).uri.toString())
                        }
                    }
                }
            }

        } ?: listOf(data.data.toString()) // 단수의 데이터

        ConfirmPhotosActivity.start(this, imageDatas)
    }


    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}