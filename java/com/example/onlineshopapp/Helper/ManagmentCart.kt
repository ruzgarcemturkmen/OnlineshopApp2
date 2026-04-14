package com.example.onlineshopapp.Helper

import android.content.Context
import android.widget.Toast
import com.example.onlineshopapp.Domain.ItemsModel
import com.example.onlineshopapp.Helper.TinyDB

class ManagmentCart(private val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertItem(item: ItemsModel) {
        val cartList = getListCart()

        val index = cartList.indexOfFirst { it.title == item.title }

        if (index != -1) {
            cartList[index].numberInCart += item.numberInCart
        } else {
            cartList.add(item)
        }

        saveCart(cartList)
        Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<ItemsModel> {
        return (tinyDB.getListObject("CartList") ?: arrayListOf()) as ArrayList<ItemsModel>
    }

    fun minusItem(position: Int, listener: ChangeNumberItemsListener) {
        val cartList = getListCart()

        if (position !in cartList.indices) return

        if (cartList[position].numberInCart > 1) {
            cartList[position].numberInCart--
        } else {
            cartList.removeAt(position)
        }

        saveCart(cartList)
        listener.onChanged()
    }

    fun plusItem(position: Int, listener: ChangeNumberItemsListener) {
        val cartList = getListCart()

        if (position !in cartList.indices) return

        cartList[position].numberInCart++

        saveCart(cartList)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        return getListCart().sumOf { it.price * it.numberInCart }
    }

    private fun saveCart(list: ArrayList<ItemsModel>) {
        tinyDB.putListObject("CartList", list)
    }
}
