package com.vr.app.sh.ui.tests.view.subjects

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.vr.app.sh.R

class ActivitySubjects : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        supportFragmentManager.beginTransaction().apply {
            add(R.id.subject_fragments_f,FragmentAllSubjects())
            commit()
        }
    }
}