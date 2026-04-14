package com.example.onlineshopapp.Activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopapp.Adapter.CartAdapter
import com.example.onlineshopapp.Helper.ChangeNumberItemsListener
import com.example.onlineshopapp.Helper.ManagmentCart
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double  = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        managmentCart= ManagmentCart(this)

        setVarible()
        initCartList()
        calculatorCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.viewCart.adapter= CartAdapter(managmentCart.getListCart(),this,object :
            ChangeNumberItemsListener{
            override fun onChanged() {
                calculatorCart()
            }
        })
    }

    private fun setVarible() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculatorCart(){
        val percentTax=0.02
        val delivery=10.0
        tax= Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total= Math.round((managmentCart.getTotalFee()*tax+delivery)*100)/100
        val itemTotal= Math.round(managmentCart.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text="$$total"
        }
    }
}