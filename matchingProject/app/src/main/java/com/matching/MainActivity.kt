package com.matching

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            loadImagefromGallery()
        }

    }

    private fun loadImagefromGallery() {
        //Intent 생성
        val intent = Intent(Intent.ACTION_PICK)
            .apply {
                type = "image/*" //이미지만 보이게
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && null != data ) {
                //data에서 절대경로로 이미지를 가져옴
                var imageDatas = emptyList<Uri>()

                if (data.clipData == null) {
                    imageDatas = listOf(data.data ?: Uri.EMPTY)
                } else {
                    val clipData = data.clipData ?: return
                    if (clipData.itemCount > 32) {
                        Toast.makeText(this, "사진은 32장까지 선택 가능합니다.", Toast.LENGTH_LONG).show()
                    } else if (clipData.itemCount >= 1) {
                        val list = imageDatas.toMutableList()
                        for (i in 0..(clipData.itemCount - 1)) {
                            list.add(clipData.getItemAt(i).uri)
                        }
                        imageDatas = list.toList()
                    } else {
                        Toast.makeText(this, "사진이 0장 입니다.", Toast.LENGTH_LONG).show()
                    }

                }
                Log.d("GET IMAGE TEST", "$imageDatas")

                ChoosePhotosActivity.start(this, imageDatas!!)
            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}