package com.wizy.test.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.crawlapss.myapplication.R
import com.wizy.test.Util.Utility
import com.hw4c.thetam.Interface.ItemClickInterface
import com.shopifydemo.Adapter.Product.HobbiesAdapter
import com.wizy.test.BaseApplication
import kotlinx.android.synthetic.main.common_recyclerview.*
import kotlinx.android.synthetic.main.fragment_hobbies.*

class HobbiesListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hobbies, container, false)
    }

    // Make fragment instance
    companion object {
        fun newInstance(): HobbiesListFragment {
            return HobbiesListFragment()
        }
    }

    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commonRecyclerView.layoutManager = LinearLayoutManager(activity!!)
        val classesArray = resources.getStringArray(R.array.hobbies_array)
        val hobbiesAdapter = HobbiesAdapter(classesArray)
        commonRecyclerView.adapter = hobbiesAdapter
        hobbiesAdapter.itemClickListener(object : ItemClickInterface {
            override fun onClick(position: Int, view: View) {
                hobbiesAdapter.updateData(position)
            }
        })
        buttonNext.setOnClickListener {
            if (BaseApplication.instance.hobbiesSelectList.size > 0) {
                BaseApplication.instance.userModel.hobbies = BaseApplication.instance.hobbiesSelectList.toString()
                Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    HobbiesExperienceFragment::class.java.name,
                    HobbiesExperienceFragment.newInstance(), ""
                )

            } else {
                Toast.makeText(activity!!, resources.getString(R.string.hobbies_select_error), Toast.LENGTH_SHORT).show()
            }
        }


    }
}