package com.vr.app.sh.ui.tests.view.subjects

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.repository.BookRepoImpl
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.TestsRepoImpl
import com.vr.app.sh.domain.repository.TestsRepo
import com.vr.app.sh.ui.base.AllSubjectsViewModelFactory
import com.vr.app.sh.ui.base.BooksViewModelFactory
import com.vr.app.sh.ui.books.viewmodel.SubjectsViewModel
import com.vr.app.sh.ui.tests.view.listTests.WindowTestsNames
import com.vr.app.sh.ui.tests.viewmodel.AllSubjectsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentAllSubjects() : Fragment(), View.OnClickListener {

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var button9: Button
    lateinit var button10: Button
    lateinit var button11: Button
    lateinit var button12: Button
    lateinit var button13: Button
    lateinit var button14: Button
    lateinit var button15: Button
    lateinit var button16: Button
    lateinit var button17: Button
    lateinit var button18: Button
    lateinit var button19: Button
    lateinit var button20: Button

    lateinit var viewModel: AllSubjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_all_subjects, container, false)
        // Inflate the layout for this fragment
        button1 = v.findViewById<Button>(R.id.sub_1)
        button1.setOnClickListener(this)
        button2 = v.findViewById<Button>(R.id.sub_2)
        button2.setOnClickListener(this)
        button3 = v.findViewById<Button>(R.id.sub_3)
        button3.setOnClickListener(this)
        button4 = v.findViewById<Button>(R.id.sub_4)
        button4.setOnClickListener(this)
        button5 = v.findViewById<Button>(R.id.sub_5)
        button5.setOnClickListener(this)
        button6 = v.findViewById<Button>(R.id.sub_6)
        button6.setOnClickListener(this)
        button7 = v.findViewById<Button>(R.id.sub_7)
        button7.setOnClickListener(this)
        button8 = v.findViewById<Button>(R.id.sub_8)
        button8.setOnClickListener(this)
        button9 = v.findViewById<Button>(R.id.sub_9)
        button9.setOnClickListener(this)
        button10 = v.findViewById<Button>(R.id.sub_10)
        button10.setOnClickListener(this)
        button11 = v.findViewById<Button>(R.id.sub_11)
        button11.setOnClickListener(this)
        button12 = v.findViewById<Button>(R.id.sub_12)
        button12.setOnClickListener(this)
        button13 = v.findViewById<Button>(R.id.sub_13)
        button13.setOnClickListener(this)
        button14 = v.findViewById<Button>(R.id.sub_14)
        button14.setOnClickListener(this)
        button15 = v.findViewById<Button>(R.id.sub_15)
        button15.setOnClickListener(this)
        button16 = v.findViewById<Button>(R.id.sub_16)
        button16.setOnClickListener(this)
        button17 = v.findViewById<Button>(R.id.sub_17)
        button17.setOnClickListener(this)
        button18 = v.findViewById<Button>(R.id.sub_18)
        button18.setOnClickListener(this)
        button19 = v.findViewById<Button>(R.id.sub_19)
        button19.setOnClickListener(this)
        button20 = v.findViewById<Button>(R.id.sub_20)
        button20.setOnClickListener(this)

        val retrofitService = NetworkService.getInstance()
        val mainRepository = InternetRepoImpl(retrofitService)
        val testsRepoImpl = TestsRepoImpl(requireContext())
        viewModel = ViewModelProvider(this, AllSubjectsViewModelFactory(mainRepository, testsRepoImpl, requireContext())
        ).get(AllSubjectsViewModel::class.java)

        viewModel.errorMessage.observe(viewLifecycleOwner){
            viewModel.errorMessage(it,requireContext())
        }

        viewModel.statusTestsInBD.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(activity, WindowTestsNames::class.java)
                intent.putExtra("sub", viewModel.sub.value.toString())
                startActivity(intent)
                viewModel.statusTestsInBD.value = false
            }
        }
        return v
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.sub_1 -> {
                viewModel.getAllTests(button1.text.toString())
            }
            R.id.sub_2 -> {
                viewModel.getAllTests(button2.text.toString())
            }
            R.id.sub_3 -> {
                viewModel.getAllTests(button3.text.toString())
            }
            R.id.sub_4 -> {
                viewModel.getAllTests(button4.text.toString())
            }
            R.id.sub_5 -> {
                viewModel.getAllTests(button5.text.toString())
            }
            R.id.sub_6 -> {
                viewModel.getAllTests(button6.text.toString())
            }
            R.id.sub_7 -> {
                viewModel.getAllTests(button7.text.toString())
            }
            R.id.sub_8 -> {
                viewModel.getAllTests(button8.text.toString())
            }
            R.id.sub_9 -> {
                viewModel.getAllTests(button9.text.toString())
            }
            R.id.sub_10 -> {
                viewModel.getAllTests(button10.text.toString())
            }
            R.id.sub_11 -> {
                viewModel.getAllTests(button11.text.toString())
            }
            R.id.sub_12 -> {
                viewModel.getAllTests(button12.text.toString())
            }
            R.id.sub_13 -> {
                viewModel.getAllTests(button13.text.toString())
            }
            R.id.sub_14 -> {
                viewModel.getAllTests(button14.text.toString())
            }
            R.id.sub_15 -> {
                viewModel.getAllTests(button15.text.toString())
            }
            R.id.sub_16 -> {
                viewModel.getAllTests(button16.text.toString())
            }
            R.id.sub_17 -> {
                viewModel.getAllTests(button17.text.toString())
            }
            R.id.sub_18 -> {
                viewModel.getAllTests(button18.text.toString())
            }
            R.id.sub_19 -> {
                viewModel.getAllTests(button19.text.toString())
            }
            R.id.sub_20 -> {
                viewModel.getAllTests(button20.text.toString())
            }
        }
    }
}
