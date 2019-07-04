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


class SubjectAdapter(private val subjectsList: Array<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {
    private var itemClickInterface: ItemClickInterface? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_hobbies, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewSubjectName.text = subjectsList[position]
        holder.checkBoxSubject.isChecked = BaseApplication.instance.subjectSelectList.contains(subjectsList[position])
    }

    fun itemClickListener(itemClickInterface: ItemClickInterface) {
        this.itemClickInterface = itemClickInterface
    }


    fun updateData(pos: Int) {
        if (BaseApplication.instance.subjectSelectList.contains(subjectsList[pos])) {
            BaseApplication.instance.subjectSelectList.remove(subjectsList[pos])
        } else {
            BaseApplication.instance.subjectSelectList.add(subjectsList[pos])
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return subjectsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewSubjectName = itemView.findViewById<TextView>(R.id.textViewTitle)!!
        val checkBoxSubject = itemView.findViewById<CheckBox>(R.id.checkBoxAdapter)!!

        init {
            checkBoxSubject.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.checkBoxAdapter -> if (itemClickInterface != null)
                    itemClickInterface!!.onClick(adapterPosition, v)
            }
        }
    }
}
