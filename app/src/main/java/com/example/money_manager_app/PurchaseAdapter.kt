package com.example.money_manager_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.money_manager_app.databinding.PurchaseItemBinding

class PurchaseAdapter(val onClickDelete: (Int) -> Unit): RecyclerView.Adapter<PurchaseAdapter.PurchaseHolder>() {
    val purchaselist = ArrayList<Purchase>()

    inner class PurchaseHolder(val item: View): RecyclerView.ViewHolder(item) {
        val binding = PurchaseItemBinding.bind(item)
        fun bind(purchase: Purchase, index: Int) {
            with(binding){
                checkbox.text = "${purchase.name}"
                categoryItem.text = "${purchase.category}"
            }
            val delete = item.findViewById<ImageButton>(R.id.delete)
            delete.setOnClickListener {onClickDelete(index)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.purchase_item, parent, false)
        return PurchaseHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseHolder, position: Int) {
        holder.bind(purchaselist[position], position)
    }

    override fun getItemCount(): Int {
        return purchaselist.size
    }

    fun addPurchase(purchase: Purchase){
        purchaselist.add(purchase)
        notifyDataSetChanged()
    }
}