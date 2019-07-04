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
import kotlinx.android.synthetic.main.fragment_board.*

class BoardSelectFragment:Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_board,container,false)
    }

    // Make Fragment Instance
    companion object {
        fun newInstance(): BoardSelectFragment {
            return BoardSelectFragment()
        }
    }

    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutExperience.setOnClickListener {
            if(imageViewStatingOut.rotation==180F){
                linearLayoutExperienceOption.visibility=View.GONE
                imageViewStatingOut.rotation=0F
                imageViewWeek.rotation=0F
                linearLayoutWeekOption.visibility=View.GONE
            }else{
                linearLayoutExperienceOption.visibility=View.VISIBLE
                imageViewStatingOut.rotation=180F
                imageViewWeek.rotation=0F
                linearLayoutWeekOption.visibility=View.GONE
            }
        }

        linearLayoutWeek.setOnClickListener {
            if(imageViewWeek.rotation==180F){
                linearLayoutExperienceOption.visibility=View.GONE
                imageViewStatingOut.rotation=0F
                imageViewWeek.rotation=0F
                linearLayoutWeekOption.visibility=View.GONE
            }else {
                linearLayoutExperienceOption.visibility = View.GONE
                imageViewStatingOut.rotation = 0F
                imageViewWeek.rotation = 180F
                linearLayoutWeekOption.visibility = View.VISIBLE
            }
        }

        linearLayoutIntermidate.setOnClickListener {
            linearLayoutIntermidate.setBackgroundResource(R.drawable.option_activate_bg)
            linearLayoutExpert.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewIntermidateTitle.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewIntermidateSubTitle.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewExpertTitle.setTextColor(ContextCompat.getColor(activity!!,R.color.colorText))
            textViewExpertSubTitle.setTextColor(ContextCompat.getColor(activity!!,R.color.colorStock))
            BaseApplication.instance.userModel.experience=textViewIntermidateTitle.text.toString()+" "+textViewIntermidateSubTitle.text.toString()
        }

        linearLayoutExpert.setOnClickListener {
            linearLayoutExpert.setBackgroundResource(R.drawable.option_activate_bg)
            linearLayoutIntermidate.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewExpertTitle.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewExpertSubTitle.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewIntermidateTitle.setTextColor(ContextCompat.getColor(activity!!,R.color.colorText))
            textViewIntermidateSubTitle.setTextColor(ContextCompat.getColor(activity!!,R.color.colorStock))
            BaseApplication.instance.userModel.experience=textViewExpertTitle.text.toString()+" "+textViewExpertSubTitle.text.toString()
        }

        textViewLessHrs.setOnClickListener {
            textViewLessHrs.setBackgroundResource(R.drawable.option_activate_bg)
            textViewWhenNeed.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewWhenNeed.setTextColor(ContextCompat.getColor(activity!!,android.R.color.background_dark))
            textViewLessHrs.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            BaseApplication.instance.userModel.weekhrs=textViewLessHrs.text.toString()
        }

        textViewWhenNeed.setOnClickListener {
            textViewWhenNeed.setBackgroundResource(R.drawable.option_activate_bg)
            textViewLessHrs.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewWhenNeed.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewLessHrs.setTextColor(ContextCompat.getColor(activity!!,android.R.color.black))
            BaseApplication.instance.userModel.weekhrs=textViewWhenNeed.text.toString()
        }

        buttonNext.setOnClickListener {
            when {
                BaseApplication.instance.userModel.boards==null -> Toast.makeText(activity!!, resources.getString(R.string.select_board_error_msg), Toast.LENGTH_SHORT).show()
                BaseApplication.instance.userModel.experience==null -> Toast.makeText(activity!!, resources.getString(R.string.experiandce_error), Toast.LENGTH_SHORT).show()
                BaseApplication.instance.userModel.weekhrs==null -> Toast.makeText(activity!!, resources.getString(R.string.week_hrs_error), Toast.LENGTH_SHORT).show()
                else -> Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    AcademicsOverViewFragment::class.java.name,
                    AcademicsOverViewFragment.newInstance(), ""
                )
            }
        }

        textViewCBSE.setOnClickListener {
            textViewCBSE.setBackgroundResource(R.drawable.option_activate_bg)
            textViewICSE.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewStateBoard.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewCBSE.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewICSE.setTextColor(ContextCompat.getColor(activity!!,android.R.color.black))
            textViewStateBoard.setTextColor(ContextCompat.getColor(activity!!,android.R.color.black))
            BaseApplication.instance.userModel.boards=textViewCBSE.text.toString()
        }

        textViewICSE.setOnClickListener {
            textViewICSE.setBackgroundResource(R.drawable.option_activate_bg)
            textViewCBSE.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewStateBoard.setBackgroundResource(R.drawable.rect_corner_bg)

            textViewICSE.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewCBSE.setTextColor(ContextCompat.getColor(activity!!,android.R.color.black))
            textViewStateBoard.setTextColor(ContextCompat.getColor(activity!!,android.R.color.black))
            BaseApplication.instance.userModel.boards=textViewICSE.text.toString()
        }

        textViewStateBoard.setOnClickListener {
            textViewStateBoard.setBackgroundResource(R.drawable.option_activate_bg)
            textViewCBSE.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewICSE.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewStateBoard.setTextColor(ContextCompat.getColor(activity!!,android.R.color.white))
            textViewCBSE.setTextColor(ContextCompat.getColor(activity!!,android.R.color.black))
            textViewICSE.setTextColor(ContextCompat.getColor(activity!!,android.R.color.black))
            BaseApplication.instance.userModel.boards=textViewStateBoard.text.toString()
        }
    }
}