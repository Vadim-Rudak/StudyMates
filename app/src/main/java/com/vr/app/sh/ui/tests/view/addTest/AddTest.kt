package com.vr.app.sh.ui.tests.view.addTest

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.vr.app.sh.R

class AddTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_test)
        val test_name = findViewById<EditText>(R.id.TextTest)
        val btn_add = findViewById<Button>(R.id.AddNameTest)
        btn_add.setOnClickListener {
            if (TextUtils.isEmpty(test_name.text.toString().trim())){
                test_name.setText("")
                test_name.hint = "Введите название теста"
            }else{
                Log.d("FFF", intent.extras?.getString("sub").toString())
                val intent1 = Intent(this,AddQuestion::class.java)
                intent1.putExtra("subject",intent.extras?.getString("sub").toString())
                intent1.putExtra("num_class",intent.extras?.getInt("num_class"))
                intent1.putExtra("name_test",test_name.text.toString())
                startActivity(intent1)
                finish()
            }
        }
    }
}