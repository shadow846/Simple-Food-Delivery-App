package com.example.food_app_23

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.food_app_23.Domain.FoodDomain
import com.example.food_app_23.Helper.ManagementCart

class ShowDetailActivity : AppCompatActivity() {

    private lateinit var addToCartBtn: TextView
    private lateinit var titleTxt: TextView
    private lateinit var priceTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var numberOrderTxt: TextView
    private lateinit var plusBtn: ImageView
    private lateinit var minusBtn: ImageView
    private lateinit var picFood: ImageView
    private lateinit var foodDomain: FoodDomain
    private var numberOrder = 1

    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        managementCart = ManagementCart(this)
        initView()
        getBundle()
    }

    private fun getBundle() {
        val obj = intent.getSerializableExtra("object") as? FoodDomain ?: return
        foodDomain = obj

        val drawableResourceId =
            resources.getIdentifier(foodDomain.pic, "drawable", this.packageName)
        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood)

        titleTxt.text = foodDomain.title
        priceTxt.text = "$" + foodDomain.fee
        descriptionTxt.text = foodDomain.description
        numberOrderTxt.text = numberOrder.toString()

        plusBtn.setOnClickListener {
            numberOrder += 1
            numberOrderTxt.text = numberOrder.toString()
        }

        minusBtn.setOnClickListener {
            if (numberOrder > 1) numberOrder -= 1
            numberOrderTxt.text = numberOrder.toString()
        }

        addToCartBtn.setOnClickListener {
            foodDomain.numberInCart = numberOrder
            managementCart.insertFood(foodDomain)
        }
    }

    private fun initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn)
        titleTxt = findViewById(R.id.titleTxt)
        priceTxt = findViewById(R.id.priceTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        numberOrderTxt = findViewById(R.id.numberOrderTxt)
        plusBtn = findViewById(R.id.plusBtn)
        minusBtn = findViewById(R.id.minusBtn)
        picFood = findViewById(R.id.picFood)
    }
}
