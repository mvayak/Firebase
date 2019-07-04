package com.wizy.test.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.crawlapss.myapplication.R
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.fragment_hourly_rate.*


class HourlyRateFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hourly_rate,container,false)
    }

    // Make fragment instance
    companion object {
        fun newInstance(): HourlyRateFragment {
            return HourlyRateFragment()
        }
    }


    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            buttonNext.setOnClickListener {
                if(editTextHoursRate.text.toString().trim()==""){
                    Toast.makeText(activity!!,resources.getString(R.string.hourly_rate_error), Toast.LENGTH_SHORT).show()
                }else {
                Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    PaymentFragment::class.java.name,
                    PaymentFragment.newInstance(), ""
                )
            }
        }
    }
}