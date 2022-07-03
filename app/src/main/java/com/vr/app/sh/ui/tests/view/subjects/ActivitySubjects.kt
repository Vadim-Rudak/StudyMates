package com.vr.app.sh.ui.tests.view.subjects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.vr.app.sh.R

class ActivitySubjects : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        var fragmentAllSubjects:FragmentAllSubjects = FragmentAllSubjects()
        transaction.add(R.id.subject_fragments_f,fragmentAllSubjects)
        transaction.commit()
    }
}