package com.vr.app.sh.ui.tests.view.listTests

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.TestsOneClassViewModelFactory
import com.vr.app.sh.ui.other.UseAlert.Companion.infoMessage
import com.vr.app.sh.ui.tests.adapter.BtnTestAdapter
import com.vr.app.sh.ui.tests.adapter.TestItemDecoration
import com.vr.app.sh.ui.tests.view.test.WindowTest
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel

class FragmentListTests(var numClass:Int) : Fragment() {

    @javax.inject.Inject
    lateinit var factory: TestsOneClassViewModelFactory

    lateinit var viewModel: TestsOneClassViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.injectFragmentListTests(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_list_tests, container, false)
        val recyclerViewTests = view.findViewById<RecyclerView>(R.id.list_tests_name)

        factory.setClass(numClass)

        viewModel = ViewModelProvider(this, factory)
            .get(TestsOneClassViewModel::class.java)

        recyclerViewTests.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(TestItemDecoration(requireContext()))
            adapter = viewModel.adapter
        }

        val layoutNotTests = view.findViewById<LinearLayout>(R.id.layoutNotTests)
        val animationFadeIn = AnimationUtils.loadAnimation(requireActivity(), R.anim.anim_fade_in)
        val anim = view.findViewById<LottieAnimationView>(R.id.animationView)
        anim.apply {
            startAnimation(animationFadeIn)
            repeatCount = LottieDrawable.INFINITE
            repeatMode = LottieDrawable.REVERSE
            setAnimation("not_tests.json")
        }.playAnimation()

        viewModel.listTests.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                layoutNotTests.visibility = View.VISIBLE
            }else{
                layoutNotTests.visibility = View.GONE
                viewModel.adapter.setTests(it)
            }
        }

        viewModel.openTest.observe(viewLifecycleOwner){
            startActivity(Intent(activity, WindowTest::class.java).apply {
                putExtra("name_test",viewModel.name_test)
            })
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            infoMessage(requireActivity().supportFragmentManager,it)
        }
        viewModel.adapter.setListener(object : BtnTestAdapter.Listener{
            override fun Clicked(name_test: String?, id_test: Int, index: Int) {
                viewModel.saveQuestionsInDB(id_test, name_test!!)
            }
        })
        return view
    }
}