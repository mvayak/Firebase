package com.wizy.test.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crawlapss.myapplication.R
import com.wizy.test.BaseApplication
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }
    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAcademics.setOnClickListener {
            BaseApplication.instance.userModel.hobbies=""
            BaseApplication.instance.userModel.hobbiesExperiance=""
            BaseApplication.instance.userModel.hobbiesService=""
            BaseApplication.instance.userModel.hobbiesWeekhrs=""
            BaseApplication.instance.hobbiesSelectList.clear()
            Utility.addFragmentForBackStack(fragmentManager!!,
                ClassesAcademicsFragment::class.java.name,
                ClassesAcademicsFragment.newInstance(),"")
        }

        buttonHobbies.setOnClickListener {
            BaseApplication.instance.classSelectList.clear()
            BaseApplication.instance.subjectSelectList.clear()
            BaseApplication.instance.userModel.academics=""
            Utility.addFragmentForBackStack(fragmentManager!!,
                HobbiesListFragment::class.java.name,
                HobbiesListFragment.newInstance(),"")
        }

    }
}