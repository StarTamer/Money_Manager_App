package com.example.money_manager_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.money_manager_app.databinding.ActivityMainBinding
import com.example.money_manager_app.databinding.FragmentShoppingListBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment:ShoppingListFragment = ShoppingListFragment()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()


    }
}