 package com.example.money_manager_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.money_manager_app.databinding.ActivityMainBinding
import com.example.money_manager_app.databinding.ActivityMainBinding.inflate
import com.example.money_manager_app.databinding.AddItemBinding
import com.example.money_manager_app.databinding.FragmentShoppingListBinding
import com.example.money_manager_app.databinding.FragmentShoppingListBinding.bind
import com.example.money_manager_app.databinding.FragmentShoppingListBinding.inflate

 class ShoppingListFragment : Fragment() {
    lateinit var binding: FragmentShoppingListBinding
    private val adapter = PurchaseAdapter()
     private var index = 1


     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         binding = FragmentShoppingListBinding.inflate(layoutInflater)
         init()
         binding.buttonAddPurchase.setOnClickListener { addInfo() }
         return binding.root
     }



     fun init() {
         binding.apply {
             rcView.layoutManager = LinearLayoutManager(requireContext())
             rcView.adapter = adapter
//             buttonAddPurchase.setOnClickListener {
//                 val purchase = Purchase(index, "someCategory", "Purchase")
//                 adapter.addPurchase(purchase)
//                 index++
//             }
         }
     }

     fun addInfo() {
         val inflater = LayoutInflater.from(requireContext())
         val v = inflater.inflate(R.layout.add_item, null)
         val purchaseName = v.findViewById<EditText>(R.id.purchaseName)
         val addDialog = AlertDialog.Builder(requireContext())
         addDialog.setView(v)
         val categories = resources.getStringArray(R.array.categories)
         val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)
         val tv = v.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
         tv.setAdapter(arrayAdapter)
         addDialog.setPositiveButton("ОК") {
                 dialog, id ->
             val names = purchaseName.text.toString()
             val purchase = Purchase(index, "someCategory", names)
             adapter.addPurchase(purchase)
             Toast.makeText(requireContext(), "Adding purchase success", Toast.LENGTH_SHORT).show()
             dialog.dismiss()
             index++
         }
         addDialog.setNegativeButton("Cancel"){
             dialog, id->
             dialog.dismiss()
             Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
         }
         addDialog.create()
         addDialog.show()
     }
}