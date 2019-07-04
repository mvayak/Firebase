package com.wizy.test.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.crawlapss.myapplication.R
import com.wizy.test.BaseApplication
import com.wizy.test.Util.Utility

import kotlinx.android.synthetic.main.fragment_conducat_classes.*


class ConductClassesFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_conducat_classes,container,false)
    }
    // Make fragment instance
    companion object {
        fun newInstance(): ConductClassesFragment {
            return ConductClassesFragment()
        }
    }
    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNext.setOnClickListener {
            if(BaseApplication.instance.userModel.conduct_class==null){
                Toast.makeText(activity!!,resources.getString(R.string.conduct_class_error),Toast.LENGTH_SHORT).show()
            }else {
                Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    HourlyRateFragment::class.java.name,
                    HourlyRateFragment.newInstance(), ""
                )
            }
        }

        textViewAtHome.setOnClickListener {
            textViewAtHome.setBackgroundResource(R.drawable.option_activate_bg)
            textViewStudentHome.setBackgroundResource(R.drawable.rect_corner_bg)

            textViewAtHome.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewStudentHome.setTextColor(ContextCompat.getColor(activity!!,R.color.colorStock))

            BaseApplication.instance.userModel.conduct_class=textViewAtHome.text.toString()
        }

        textViewStudentHome.setOnClickListener {
            BaseApplication.instance.userModel.conduct_class=textViewStudentHome.text.toString()
            textViewStudentHome.setBackgroundResource(R.drawable.option_activate_bg)
            textViewAtHome.setBackgroundResource(R.drawable.rect_corner_bg)

            textViewStudentHome.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewAtHome.setTextColor(ContextCompat.getColor(activity!!,R.color.colorStock))
        }
    }
}