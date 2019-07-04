package com.wizy.test.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.crawlapss.myapplication.R
import com.hw4c.thetam.Interface.ItemClickInterface
import com.wizy.test.Util.Utility
import com.shopifydemo.Adapter.Product.SubjectAdapter
import com.wizy.test.BaseApplication
import kotlinx.android.synthetic.main.common_recyclerview.*
import kotlinx.android.synthetic.main.fragment_classes_academics.*

class SubjectAcademicsFragment : Fragment() {
    private val hashMap: HashMap<String, MutableList<String>> = HashMap()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_classes_academics, container, false)
    }
    // Make fragment instance
    companion object {
        fun newInstance(): SubjectAcademicsFragment {
            return SubjectAcademicsFragment()
        }
    }
    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewAcademicsSubTitle.text = resources.getString(R.string.select_your_subject)
        commonRecyclerView.layoutManager = LinearLayoutManager(activity!!)

        val classList = if (arguments!!.getString("result")?.toString() == "class 11" || arguments!!.getString("result")?.toString() == "class 12") {
                resources.getStringArray(R.array.subject_12_array)
            } else {
                resources.getStringArray(R.array.subject_array)
            }

        val subjectAdapter = SubjectAdapter(classList as Array<String>)
        commonRecyclerView.adapter = subjectAdapter
        subjectAdapter.itemClickListener(object : ItemClickInterface {
            override fun onClick(position: Int, view: View) {

                subjectAdapter.updateData(position)


            }

        })
        buttonNextAcademics.setOnClickListener {
            if (BaseApplication.instance.subjectSelectList.size > 0) {
                for (i in BaseApplication.instance.classSelectList.indices) {
                    hashMap[BaseApplication.instance.classSelectList[i]] = BaseApplication.instance.subjectSelectList
                }

                BaseApplication.instance.userModel.academics = hashMap.toString()
                Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    PersonalVerificationFragment::class.java.name,
                    PersonalVerificationFragment.newInstance(), ""
                )
            } else {
                Toast.makeText(activity!!, resources.getString(R.string.subject_select_error), Toast.LENGTH_SHORT).show()
            }

        }
    }
}