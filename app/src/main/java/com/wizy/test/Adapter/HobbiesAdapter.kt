package com.shopifydemo.Adapter.Product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crawlapss.myapplication.R
import com.hw4c.thetam.Interface.ItemClickInterface
import com.wizy.test.BaseApplication


class HobbiesAdapter( private val hobbiesList: Array<String>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<HobbiesAdapter.ViewHolder>() {
    private var itemClickInterface: ItemClickInterface? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_hobbies, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewHobbies.text = hobbiesList[position]
        holder.checkBoxHobbies.isChecked = BaseApplication.instance.hobbiesSelectList.contains(hobbiesList[position])
    }

    fun itemClickListener(itemClickInterface: ItemClickInterface) {
        this.itemClickInterface = itemClickInterface
    }


    override fun getItemCount(): Int {
        return hobbiesList.size
    }

    fun updateData(pos: Int) {
        if (BaseApplication.instance.hobbiesSelectList.contains(hobbiesList[pos])) {
            BaseApplication.instance.hobbiesSelectList.remove(hobbiesList[pos])
        } else {
            BaseApplication.instance.hobbiesSelectList.add(hobbiesList[pos])
        }

        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewHobbies = itemView.findViewById<TextView>(R.id.textViewTitle)!!
        val checkBoxHobbies = itemView.findViewById<CheckBox>(R.id.checkBoxAdapter)!!

        init {
            checkBoxHobbies.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.checkBoxAdapter -> if (itemClickInterface != null)
                    itemClickInterface!!.onClick(adapterPosition, v)
            }
        }
    }
}
