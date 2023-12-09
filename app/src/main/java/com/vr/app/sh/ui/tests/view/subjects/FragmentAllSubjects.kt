package com.vr.app.sh.ui.tests.view.subjects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.AllSubjectsViewModelFactory
import com.vr.app.sh.ui.other.UseAlert
import com.vr.app.sh.ui.tests.adapter.BtnSubjectAdapter
import com.vr.app.sh.ui.tests.view.listTests.WindowTestsNames
import com.vr.app.sh.ui.tests.viewmodel.AllSubjectsViewModel

class FragmentAllSubjects : Fragment() {

    lateinit var viewModel: AllSubjectsViewModel

    @javax.inject.Inject
    lateinit var factory: AllSubjectsViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.injectFragmentAllSubjects(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_all_subjects, container, false) as RecyclerView
        view.layoutManager = GridLayoutManager(activity,2)

        viewModel = ViewModelProvider(this, factory)
            .get(AllSubjectsViewModel::class.java)

        view.adapter = viewModel.adapter
        view.addItemDecoration(viewModel.decoration)

        viewModel.errorMessage.observe(viewLifecycleOwner){
            UseAlert.errorMessage(it,requireContext())
        }

        viewModel.statusTestsInBD.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(activity, WindowTestsNames::class.java)
                intent.putExtra("sub", viewModel.sub.value.toString())
                startActivity(intent)
                viewModel.statusTestsInBD.value = false
            }
        }

        viewModel.adapter.setListener(object : BtnSubjectAdapter.Listener{
            override fun click(name_subject: String) {
                Log.d("FFF",name_subject)
                viewModel.getAllTests(name_subject)
            }
        })
        return view
    }
}
