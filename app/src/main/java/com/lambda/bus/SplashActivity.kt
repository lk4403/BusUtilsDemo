package com.lambda.bus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ThreadUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BusUtils.postSticky(TAG, "Bus Even")

        ThreadUtils.runOnUiThreadDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 666)
    }
}