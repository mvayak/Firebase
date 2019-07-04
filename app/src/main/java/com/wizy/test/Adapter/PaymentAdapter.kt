package com.shopifydemo.Adapter.Product

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.crawlapss.myapplication.R
import com.hw4c.thetam.Interface.ItemClickInterface


class PaymentAdapter(private val context: Context, private val paymentOptionList: Array<String>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {
    private var itemClickInterface: ItemClickInterface? = null
    private var pos = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_payment, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewPaymentLBL.text = paymentOptionList[position]
        if(position==pos){
            holder.textViewPaymentLBL.setBackgroundResource(R.drawable.option_activate_bg)
            holder.textViewPaymentLBL.setTextColor(ContextCompat.getColor(context,android.R.color.white))

        }else{
            holder.textViewPaymentLBL.setBackgroundResource(R.drawable.rect_corner_bg)
            holder.textViewPaymentLBL.setTextColor(ContextCompat.getColor(context,R.color.colorText))
        }
    }

    fun itemClickListener(itemClickInterface: ItemClickInterface) {
        this.itemClickInterface = itemClickInterface
    }


    override fun getItemCount(): Int {
        return paymentOptionList.size
    }

    fun updateData(pos: Int) {
        this.pos=pos
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewPaymentLBL = itemView.findViewById<TextView>(R.id.textViewPaymentLBL)!!

        init {
            textViewPaymentLBL.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.textViewPaymentLBL -> if (itemClickInterface != null)
                    itemClickInterface!!.onClick(adapterPosition, v)
            }
        }
    }
}
