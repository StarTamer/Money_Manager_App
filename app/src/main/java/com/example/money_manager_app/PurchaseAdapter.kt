package com.example.money_manager_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.money_manager_app.databinding.PurchaseItemBinding

class PurchaseAdapter: RecyclerView.Adapter<PurchaseAdapter.PurchaseHolder>() {
    val purchaselist = ArrayList<Purchase>()

    class PurchaseHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = PurchaseItemBinding.bind(item)
        fun bind(purchase: Purchase) {
            with(binding){
                checkbox.text = "${purchase.purchaseID}. ${purchase.name}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.purchase_item, parent, false)
        return PurchaseHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseHolder, position: Int) {
        holder.bind(purchaselist[position])
    }

    override fun getItemCount(): Int {
        return purchaselist.size
    }

    fun addPurchase(purchase: Purchase){
        purchaselist.add(purchase)
        notifyDataSetChanged()
    }
}