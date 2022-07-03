package com.vr.app.sh.ui.tests.view.listTests

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.QuestionsRepoImpl
import com.vr.app.sh.data.repository.TestsRepoImpl
import com.vr.app.sh.domain.repository.QuestionsRepo
import com.vr.app.sh.domain.repository.TestsRepo
import com.vr.app.sh.ui.base.AuthorizationViewModelFactory
import com.vr.app.sh.ui.base.TestsOneClassViewModelFactory
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel
import com.vr.app.sh.ui.tests.adapter.BtnTestAdapter
import com.vr.app.sh.ui.tests.view.test.WindowTest
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentListTests(var num_class:Int) : Fragment() {

    lateinit var viewModel: TestsOneClassViewModel

    override fun onStart() {
        super.onStart()
        viewModel.updateAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_tests, container, false) as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        view.layoutManager = layoutManager

        val retrofitService = NetworkService.getInstance()
        val mainRepository = InternetRepoImpl(retrofitService)
        val testsRepoImpl = TestsRepoImpl(requireContext())
        val questionsRepoImpl = QuestionsRepoImpl(requireContext())
        viewModel = ViewModelProvider(this, TestsOneClassViewModelFactory(mainRepository,testsRepoImpl,questionsRepoImpl,requireContext(),num_class))
            .get(TestsOneClassViewModel::class.java)

        viewModel.openTest.observe(viewLifecycleOwner){
            val intent = Intent(activity, WindowTest::class.java)
            intent.putExtra("name_test",viewModel.name_test)
            startActivity(intent)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            viewModel.errorMessage(it,requireContext())
        }
        view.adapter = viewModel.adapter
        viewModel.adapter.setListener(object : BtnTestAdapter.Listener{
            override fun Clicked(name_test: String?, id_test: Int, index: Int) {
                viewModel.saveQuestionsInDB(id_test, name_test!!)
            }
        })
        return view
    }
}