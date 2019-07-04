package com.shopifydemo.Adapter.Product

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.crawlapss.myapplication.R
import com.hw4c.thetam.Interface.ItemClickInterface
import com.wizy.test.BaseApplication


class ClassesAdapter(private val context: Context, private val classesList: Array<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<ClassesAdapter.ViewHolder>() {
    private var itemClickInterface: ItemClickInterface? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_hobbies, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewClassName.text = classesList[position]
        holder.checkBoxClasses.isChecked = BaseApplication.instance.classSelectList.contains(classesList[position])
    }

    fun itemClickListener(itemClickInterface: ItemClickInterface) {
        this.itemClickInterface = itemClickInterface
    }


    fun updateData(pos: Int) {


        if (BaseApplication.instance.classSelectList.size > 0) {


            if ((BaseApplication.instance.classSelectList.contains("class 11") || BaseApplication.instance.classSelectList.contains(
                    "class 12"
                ))
            ) {
                if (classesList[pos] != "class 11" && classesList[pos] != "class 12") {
                    Toast.makeText(context, "You can not select this class", Toast.LENGTH_SHORT).show()
                } else {
                    if (BaseApplication.instance.classSelectList.contains(classesList[pos])) {
                        BaseApplication.instance.classSelectList.remove(classesList[pos])
                    } else {
                        BaseApplication.instance.classSelectList.add(classesList[pos])
                    }
                }
            } else if ((!BaseApplication.instance.classSelectList.contains("class 11") || !BaseApplication.instance.classSelectList.contains(
                    "class 12"
                ))
            ) {
                if (classesList[pos] == "class 11" || classesList[pos] == "class 12") {
                    Toast.makeText(context, "You can not select this class", Toast.LENGTH_SHORT).show()
                } else {
                    if (BaseApplication.instance.classSelectList.contains(classesList[pos])) {
                        BaseApplication.instance.classSelectList.remove(classesList[pos])
                    } else {
                        BaseApplication.instance.classSelectList.add(classesList[pos])
                    }
                }
            }


        } else {
            BaseApplication.instance.classSelectList.add(classesList[pos])
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return classesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewClassName = itemView.findViewById<TextView>(R.id.textViewTitle)!!
        val checkBoxClasses = itemView.findViewById<CheckBox>(R.id.checkBoxAdapter)!!

        init {
            checkBoxClasses.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.checkBoxAdapter -> if (itemClickInterface != null)
                    itemClickInterface!!.onClick(adapterPosition, v)
            }
        }
    }
}
