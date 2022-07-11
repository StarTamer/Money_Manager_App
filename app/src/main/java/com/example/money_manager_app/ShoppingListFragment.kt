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
    private val adapter = PurchaseAdapter(){index -> deleteItem(index)}
     private var index = 1


     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         binding = FragmentShoppingListBinding.inflate(layoutInflater)
         init()
         binding.buttonAddPurchase.setOnClickListener { addInfo() }
         binding.btnAddToExpenses.setOnClickListener { addToExp() }
         return binding.root
     }

     fun deleteItem(index: Int){
         adapter.purchaselist.removeAt(index)
         adapter.notifyDataSetChanged()
         if (adapter.purchaselist.isEmpty()){
             binding.btnAddToExpenses.visibility = View.INVISIBLE
         }
     }

     fun init() {
         binding.apply {
             rcView.layoutManager = LinearLayoutManager(requireContext())
             rcView.adapter = adapter
//             }
         }
     }

     fun addInfo() {
         val inflater = LayoutInflater.from(requireContext())
         val v = inflater.inflate(R.layout.add_item, null)
         val purchaseName = v.findViewById<EditText>(R.id.purchaseName)
         val addDialog = AlertDialog.Builder(requireContext())
         var category: String = ""

         val spinner = v.findViewById<Spinner>(R.id.spinner)
         spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
             override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 category = adapterView?.getItemAtPosition(position).toString()
             }

             override fun onNothingSelected(p0: AdapterView<*>?) {
                 TODO("Not yet implemented")
             }
         }

         addDialog.setView(v)
         addDialog.setPositiveButton("ОК") {
                 dialog, id ->
             binding.btnAddToExpenses.visibility = View.VISIBLE
             val names = purchaseName.text.toString()
             val purchase = Purchase(index, category, names)
             adapter.addPurchase(purchase)
             dialog.dismiss()
             index++
         }
         addDialog.setNegativeButton("Cancel"){
             dialog, id->
             dialog.dismiss()
         }
         addDialog.create()
         addDialog.show()
     }

     fun addToExp() {
         val inflater = LayoutInflater.from(requireContext())
         val v = inflater.inflate(R.layout.clear_shopping_list, null)
         val addDialog = AlertDialog.Builder(requireContext())
         val sum = v.findViewById<EditText>(R.id.sumOfPurchases)
         addDialog.setView(v)
         addDialog.setPositiveButton("OK") {
             dialog, id ->
             if (sum.text.toString().toDoubleOrNull() != null){
                 adapter.purchaselist.clear()
                 adapter.notifyDataSetChanged()
                 binding.btnAddToExpenses.visibility = View.INVISIBLE
                 dialog.dismiss()
             }
             else{
                 Toast.makeText(requireContext(),"Введите сумму корректно", Toast.LENGTH_SHORT).show()
             }
         }
         addDialog.setNegativeButton("CANCEL") {
             dialog, id ->
             dialog.dismiss()
         }
         addDialog.create()
         addDialog.show()
     }
}