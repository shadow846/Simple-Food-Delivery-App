package com.example.food_app_23

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_23.Adaptor.CartListAdapter
import com.example.food_app_23.Helper.ManagementCart
import com.example.food_app_23.Interface.ChangeNumberItemsListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.round

class CartListActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var recyclerViewList: RecyclerView
    private lateinit var managementCart: ManagementCart
    private lateinit var totalFeeTxt: TextView
    private lateinit var taxTxt: TextView
    private lateinit var deliveryTxt: TextView
    private lateinit var totalTxt: TextView
    private lateinit var emptyTxt: TextView
    private var tax: Double = 0.0
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // initialize managementCart and view references first
        managementCart = ManagementCart(this)
        initView()

        // treat checkoutBtn as a TextView (match your XML type)
        val checkoutBtn = findViewById<TextView>(R.id.checkoutBtn)
        checkoutBtn.setOnClickListener {
            managementCart.clearCart()
            Toast.makeText(this, "Your Food Will Be Delivered Soon", Toast.LENGTH_SHORT).show()
            initList()
            calculateCart()
        }

        initList()
        calculateCart()
        bottomNavigation()

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.systemBars())
    }


    private fun bottomNavigation() {
        val floatingActionButton: FloatingActionButton = findViewById(R.id.cartBtn)
        val homeBtn: LinearLayout = findViewById(R.id.homeBtn)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@CartListActivity, CartListActivity::class.java))
        }

        homeBtn.setOnClickListener {
            startActivity(Intent(this@CartListActivity, MainActivity::class.java))
        }
    }

    private fun initView() {
        recyclerViewList = findViewById(R.id.cartView)
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        taxTxt = findViewById(R.id.taxTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        totalTxt = findViewById(R.id.totalTxt)
        emptyTxt = findViewById(R.id.emptyTxt)
        scrollView = findViewById(R.id.scrollView4)
    }

    private fun initList() {
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewList.layoutManager = layoutManager
        adapter = CartListAdapter(managementCart.getListCart(), this, object : ChangeNumberItemsListener {
            override fun changed() {
                calculateCart()
            }
        })

        recyclerViewList.adapter = adapter
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery: Double

        if (managementCart.getListCart().isEmpty()) {
            delivery = 0.0
            emptyTxt.visibility = View.VISIBLE
            scrollView.visibility = View.GONE
        } else {
            delivery = 10.0
            emptyTxt.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        }

        val itemTotal = managementCart.getTotalFee()
        tax = round((itemTotal * percentTax) * 100.0) / 100
        val total = round((itemTotal + tax + delivery) * 100.0) / 100
        val roundedItemTotal = round(itemTotal * 100.0) / 100

        totalFeeTxt.text = "$$roundedItemTotal"
        taxTxt.text = "$$tax"
        deliveryTxt.text = "$$delivery"
        totalTxt.text = "$$total"
    }
}
