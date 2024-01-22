package com.vr.app.sh.ui.other.cityPicker

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.vr.app.sh.R

class BottomSheetPickCity(val context: Context, private val viewBottomSheet: LinearLayout, private val windowHeight:Int) {

    private val listAllCity = context.resources.getStringArray(R.array.cityArray)
    val selectCity = MutableLiveData<String>()
    private val heightTopLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 96.toFloat(), context.resources.displayMetrics).toInt()
    private val defaultHeight = windowHeight/2
    val adapter = CityAdapter()

    fun see(){
        val listSortedCity = ArrayList<String>()
        val pickCityBottomSheet = BottomSheetBehavior.from(viewBottomSheet)
        pickCityBottomSheet.apply {
            peekHeight = defaultHeight
            maxHeight = windowHeight
        }

        val btnClose = viewBottomSheet.findViewById<ImageButton>(R.id.city_btn_close)
        btnClose.setOnClickListener {
            pickCityBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }

        val btnClearSearchInput = viewBottomSheet.findViewById<ImageButton>(R.id.city_clear_search)
        val searchCity = viewBottomSheet.findViewById<EditText>(R.id.city_input_search)
        searchCity.text.clear()
        searchCity.doAfterTextChanged {
            if (it!!.isEmpty()){
                btnClearSearchInput.visibility = View.GONE
            }else{
                btnClearSearchInput.visibility = View.VISIBLE
            }
            listSortedCity.clear()
            for (i in listAllCity){
                if (i.lowercase().contains(it.toString().lowercase())){
                    listSortedCity.add(i)
                }
            }
            adapter.setItems(listSortedCity.toTypedArray())
        }
        btnClearSearchInput.setOnClickListener {
            searchCity.text.clear()
        }

        val recyclerView = viewBottomSheet.findViewById<RecyclerView>(R.id.recycler_bottom_sheet_city)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.setItems(listAllCity)
        recyclerView.adapter = adapter

        val params = recyclerView.layoutParams
        params.height = windowHeight/2 - heightTopLine
        recyclerView.layoutParams = params

        adapter.setListener(object:CityAdapter.Listener{
            override fun Click(citySelected: String) {
                selectCity.postValue(citySelected)
                pickCityBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        })

        pickCityBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED

        pickCityBottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset>0){
                    val  dynamicParams = recyclerView.layoutParams
                    dynamicParams.height = (defaultHeight + defaultHeight*slideOffset).toInt() - heightTopLine
                    recyclerView.layoutParams = dynamicParams
                }
            }
        })
    }

}