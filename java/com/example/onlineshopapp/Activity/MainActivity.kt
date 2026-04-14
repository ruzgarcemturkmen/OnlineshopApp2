package com.example.onlineshopapp.Activity

import CategoryAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshopapp.Adapter.PopularAdapter
import com.example.onlineshopapp.Adapter.SliderAdapter
import com.example.onlineshopapp.Domain.SliderModel
import com.example.onlineshopapp.ViewModel.MainViewModel
import com.example.onlineshopapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initCategory()
        initBanner()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.bottomBtn3.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this) {
            binding.progressBarCategory.visibility = View.GONE
            binding.viewCategory.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter = CategoryAdapter(it)
        }
        viewModel.loadCategory()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this) {
            binding.progressBarPopular.visibility = View.GONE
            binding.viewPopualar.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.viewPopualar.adapter = PopularAdapter(it)
        }
        viewModel.loadPopular()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banner.observe(this) {
            binding.progressBarBanner.visibility = View.GONE
            banners(it)
        }
        viewModel.loadBanners()
    }

    private fun banners(list: List<SliderModel>) {
        binding.viewPager2.adapter = SliderAdapter(list, binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 2
        binding.viewPager2.getChildAt(0).overScrollMode= View.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)

        if (list.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }
    }
}
