package com.example.onlineshopapp.Activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshopapp.Adapter.PopularAdapter
import com.example.onlineshopapp.ViewModel.MainViewModel
import com.example.onlineshopapp.databinding.ActivityListItemsBinding

class ListItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListItemsBinding
    private val viewModel= MainViewModel()
    private var id: String=""
    private var title: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding= ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        getBundle()
        initList()
    }

    private fun initList() {
         binding.apply {
             backBtn.setOnClickListener { finish() }
                progressBarList.visibility= View.VISIBLE
             viewModel.popular.observe(this@ListItemsActivity, Observer{
                 viewList.layoutManager= GridLayoutManager(this@ListItemsActivity,2)
                 viewList.adapter= PopularAdapter(it)
                 progressBarList.visibility= View.GONE
             })
             viewModel.loadFiltered(id)
         }
    }

    private fun getBundle(){
        id=intent.getStringExtra("id").toString()
        title=intent.getStringExtra("title").toString()

        binding.categoryTxt.text=title

    }
}