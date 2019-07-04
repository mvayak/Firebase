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


import kotlinx.android.synthetic.main.fragment_hobbies_experience.*

class HobbiesExperienceFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hobbies_experience, container, false)
    }

    // Make fragment instance
    companion object {
        fun newInstance(): HobbiesExperienceFragment {
            return HobbiesExperienceFragment()
        }
    }

    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonNext.setOnClickListener {
            when {
                BaseApplication.instance.userModel.hobbiesExperiance==null -> Toast.makeText(activity!!, resources.getString(R.string.experiandce_error), Toast.LENGTH_SHORT).show()
                BaseApplication.instance.userModel.hobbiesWeekhrs==null -> Toast.makeText(activity!!, resources.getString(R.string.week_hrs_error), Toast.LENGTH_SHORT).show()
                BaseApplication.instance.userModel.hobbiesService==null -> Toast.makeText(activity!!, resources.getString(R.string.service_provider_error), Toast.LENGTH_SHORT).show()
                else -> Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    PersonalVerificationFragment::class.java.name,
                    PersonalVerificationFragment(), ""
                )
            }


        }

        constraintLayoutStartingOut.setOnClickListener {
            if (imageViewStatingOut.rotation == 180F) {
                linearLayoutExperienceOption.visibility = View.GONE
                linearLayoutWeekOption.visibility = View.GONE
                imageViewStatingOut.rotation = 0F
                imageViewWeek.rotation = 0F
            } else {
                linearLayoutExperienceOption.visibility = View.VISIBLE
                linearLayoutWeekOption.visibility = View.GONE
                imageViewStatingOut.rotation = 180F
                imageViewWeek.rotation = 0F
            }
        }

        linearLayoutWeek.setOnClickListener {
            if (imageViewWeek.rotation == 180F) {
                linearLayoutExperienceOption.visibility = View.GONE
                linearLayoutWeekOption.visibility = View.GONE
                imageViewStatingOut.rotation = 0F
                imageViewWeek.rotation = 0F
            } else {
                linearLayoutExperienceOption.visibility = View.GONE
                linearLayoutWeekOption.visibility = View.VISIBLE
                imageViewStatingOut.rotation = 0F
                imageViewWeek.rotation = 180F
            }
        }

        linearLayoutIntermidate.setOnClickListener {
            linearLayoutIntermidate.setBackgroundResource(R.drawable.option_activate_bg)
            linearLayoutExpert.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewIntermidateTitle.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))
            textViewIntermidateSubTitle.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))

            textViewExpertTitle.setTextColor(ContextCompat.getColor(activity!!, R.color.colorText))
            textViewExpertSubTitle.setTextColor(ContextCompat.getColor(activity!!, R.color.colorStock))

            BaseApplication.instance.userModel.hobbiesExperiance =
                textViewIntermidateTitle.text.toString() + " " + textViewIntermidateSubTitle.text.toString()


        }


        linearLayoutExpert.setOnClickListener {
            linearLayoutExpert.setBackgroundResource(R.drawable.option_activate_bg)
            linearLayoutIntermidate.setBackgroundResource(R.drawable.rect_corner_bg)
            textViewExpertTitle.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))
            textViewExpertSubTitle.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))

            textViewIntermidateTitle.setTextColor(ContextCompat.getColor(activity!!, R.color.colorText))
            textViewIntermidateSubTitle.setTextColor(ContextCompat.getColor(activity!!, R.color.colorStock))

            BaseApplication.instance.userModel.hobbiesExperiance =
                textViewExpertTitle.text.toString() + " " + textViewExpertSubTitle.text.toString()

        }
        textViewLessHrs.setOnClickListener {
            textViewLessHrs.setBackgroundResource(R.drawable.option_activate_bg)
            textViewWhenNeed.setBackgroundResource(R.drawable.rect_corner_bg)

            textViewWhenNeed.setTextColor(ContextCompat.getColor(activity!!, android.R.color.background_dark))
            textViewLessHrs.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))

            BaseApplication.instance.userModel.hobbiesWeekhrs = textViewLessHrs.text.toString()
        }

        textViewWhenNeed.setOnClickListener {
            textViewWhenNeed.setBackgroundResource(R.drawable.option_activate_bg)
            textViewLessHrs.setBackgroundResource(R.drawable.rect_corner_bg)

            textViewWhenNeed.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))
            textViewLessHrs.setTextColor(ContextCompat.getColor(activity!!, android.R.color.black))
            BaseApplication.instance.userModel.hobbiesWeekhrs = textViewWhenNeed.text.toString()
        }

        textViewStudentHome.setOnClickListener {
            textViewStudentHome.setBackgroundResource(R.drawable.option_activate_bg)
            textViewMyHome.setBackgroundResource(R.drawable.rect_corner_bg)

            textViewMyHome.setTextColor(ContextCompat.getColor(activity!!, android.R.color.background_dark))
            textViewStudentHome.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))

            BaseApplication.instance.userModel.hobbiesService = textViewStudentHome.text.toString()
        }

        textViewMyHome.setOnClickListener {
            textViewMyHome.setBackgroundResource(R.drawable.option_activate_bg)
            textViewStudentHome.setBackgroundResource(R.drawable.rect_corner_bg)

            textViewStudentHome.setTextColor(ContextCompat.getColor(activity!!, android.R.color.background_dark))
            textViewMyHome.setTextColor(ContextCompat.getColor(activity!!, android.R.color.white))

            BaseApplication.instance.userModel.hobbiesService = textViewMyHome.text.toString()
        }
    }
}