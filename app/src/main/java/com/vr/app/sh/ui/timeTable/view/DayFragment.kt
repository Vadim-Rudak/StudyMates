package com.vr.app.sh.ui.timeTable.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.DayViewModelFactory
import com.vr.app.sh.ui.timeTable.adapter.LessonItemDecoration
import com.vr.app.sh.ui.timeTable.viewModel.DayViewModel

class DayFragment(private val num_day:Int) : Fragment() {

    @javax.inject.Inject
    lateinit var factory: DayViewModelFactory

    lateinit var viewModel: DayViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.injectFragmentDay(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false) as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        view.layoutManager = layoutManager

        factory.setDay(num_day)

        viewModel = ViewModelProvider(this,factory)
            .get(DayViewModel::class.java)

        view.adapter = viewModel.adapter
        view.addItemDecoration(LessonItemDecoration(requireContext()))

        viewModel.listLessons.observe(viewLifecycleOwner){
            viewModel.adapter.setLessons(it)
        }


        return view
    }

}