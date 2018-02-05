package com.example.kotlin.scrollingtable

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.kotlin.scrollingtable.type1.Type1Activity
import com.example.kotlin.scrollingtable.type2.Type2Activity
import com.example.kotlin.scrollingtable.type3.Type3Activity
import com.example.kotlin.scrollingtable.type4.Type4Activity
import com.example.kotlin.scrollingtable.type5.Type5Activity
import com.example.kotlin.scrollingtable.type6.Type6Activity

/**
 * 主入口
 * Created by xiaoyulaoshi on 2018/1/31.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun jumpType1(view: View) {
        val intent = Intent(this, Type1Activity::class.java)
        startActivity(intent)
    }

    fun jumpType2(view: View) {
        val intent = Intent(this, Type2Activity::class.java)
        startActivity(intent)
    }

    fun jumpType3(view: View) {
        val intent = Intent(this, Type3Activity::class.java)
        startActivity(intent)
    }

    fun jumpType4(view: View) {
        val intent = Intent(this, Type4Activity::class.java)
        startActivity(intent)
    }

    fun jumpType5(view: View) {
        val intent = Intent(this, Type5Activity::class.java)
        startActivity(intent)
    }

    fun jumpType6(view: View) {
        val intent = Intent(this, Type6Activity::class.java)
        startActivity(intent)
    }
}
