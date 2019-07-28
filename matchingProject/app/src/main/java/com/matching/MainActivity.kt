package com.matching

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answerLeftTextView = findViewById<TextView>(R.id.answer_left)
        answerLeftTextView.text = "food"
        val answerRightTextView = findViewById<TextView>(R.id.answer_right)
        answerRightTextView.text = "health"

    }
}