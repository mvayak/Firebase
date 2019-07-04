package com.wizy.test.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.crawlapss.myapplication.R
import com.wizy.test.Util.Utility
import com.hw4c.thetam.Interface.ItemClickInterface
import com.shopifydemo.Adapter.Product.ClassesAdapter
import com.wizy.test.BaseApplication
import kotlinx.android.synthetic.main.common_recyclerview.*
import kotlinx.android.synthetic.main.fragment_classes_academics.*

class ClassesAcademicsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_classes_academics, container, false)
    }
    // Make fragment instance
    companion object {
        fun newInstance(): ClassesAcademicsFragment {
            return ClassesAcademicsFragment()
        }
    }
    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commonRecyclerView.layoutManager = LinearLayoutManager(activity!!)
        val classesArray = resources.getStringArray(R.array.class_array)
        val classesAdapter = ClassesAdapter(activity!!, classesArray)
        commonRecyclerView.adapter = classesAdapter
        classesAdapter.itemClickListener(object : ItemClickInterface {
            override fun onClick(position: Int, view: View) {
                classesAdapter.updateData(position)
            }

        })
        buttonNextAcademics.setOnClickListener {
            BaseApplication.instance.subjectSelectList.clear()
            if (BaseApplication.instance.classSelectList.size > 0) {
                Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    SubjectAcademicsFragment::class.java.name,
                    SubjectAcademicsFragment.newInstance(), BaseApplication.instance.classSelectList[0]
                )
            } else {
                Toast.makeText(activity!!,resources.getString(R.string.class_select_error_msg),Toast.LENGTH_SHORT).show()
            }
        }
    }
}