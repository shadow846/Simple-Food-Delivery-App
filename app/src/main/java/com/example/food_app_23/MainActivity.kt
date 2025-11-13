package com.example.food_app_23

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_23.Adaptor.CategoryAdaptor
import com.example.food_app_23.Adaptor.PopularAdaptor
import com.example.food_app_23.Domain.CategoryDomain
import com.example.food_app_23.Domain.FoodDomain
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var adapter: RecyclerView.Adapter<*>? = null
    private var adapter2: RecyclerView.Adapter<*>? = null
    private lateinit var recyclerViewCategoryList: RecyclerView
    private lateinit var recyclerViewPopularList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val root: View = findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation()

        val orderNowBtn = findViewById<TextView>(R.id.textView9)
        val mostLovedTxt = findViewById<TextView>(R.id.textView23)
        val scrollView = findViewById<ScrollView>(R.id.scrollView4)

        orderNowBtn.setOnClickListener {
            scrollView.smoothScrollTo(0, mostLovedTxt.top)
        }

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun bottomNavigation() {
        val floatingActionButton: FloatingActionButton = findViewById(R.id.cartBtn)
        val homeBtn: LinearLayout = findViewById(R.id.homeBtn)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartListActivity::class.java))
        }

        homeBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        }
    }

    private fun recyclerViewCategory() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.recyclerView)
        recyclerViewCategoryList.layoutManager = layoutManager

        val category = ArrayList<CategoryDomain>()
        category.add(CategoryDomain("Pizza", "cat_1"))
        category.add(CategoryDomain("Burger", "cat_2"))
        category.add(CategoryDomain("Hotdog", "cat_3"))
        category.add(CategoryDomain("Drink", "cat_4"))
        category.add(CategoryDomain("Donut", "cat_5"))

        adapter = CategoryAdaptor(category)
        recyclerViewCategoryList.adapter = adapter
    }

    private fun recyclerViewPopular() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularList = findViewById(R.id.recyclerView2)
        recyclerViewPopularList.layoutManager = layoutManager

        val foodList = ArrayList<FoodDomain>()
        foodList.add(FoodDomain("Pepperoni pizza", "pizza1", "slices pepperoni, mozzarella cheese, fresh oregano , ground black pepper, pizza sauce", 9.76))
        foodList.add(FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special Sauce, Lettuce, tomato", 8.79))
        foodList.add(FoodDomain("Vegetable Pizza", "pizza2", "olive oil, Vegetable oil, pitted Kalamata, red paprika, fresh oregano , basil", 8.5))

        adapter2 = PopularAdaptor(foodList)
        recyclerViewPopularList.adapter = adapter2
    }
}
