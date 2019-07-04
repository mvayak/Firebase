package com.wizy.test.Fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.crawlapss.myapplication.R
import com.wizy.test.BaseApplication
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.fragment_signup.*


class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }
    // Make Fragment instance
    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }
    // Initialling and make click of views
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataAdapter = ArrayAdapter<String>(activity!!, R.layout.spinner_gender, resources.getStringArray(R.array.category_array))
        spinnerGender.adapter = dataAdapter
        spinnerGender.setPopupBackgroundDrawable(resources.getDrawable(R.drawable.spinner_gender))
        buttonNext.setOnClickListener {
            when {
                editTextFname.text.toString().trim().isEmpty() -> Toast.makeText(context, resources.getString(R.string.fname_error), Toast.LENGTH_LONG).show()
                editTextLName.text.toString().trim().isEmpty() -> Toast.makeText(context, resources.getString(R.string.lname_error), Toast.LENGTH_LONG).show()
                editTextPhone.text.toString().trim().isEmpty() -> Toast.makeText(context, resources.getString(R.string.phone_number_error), Toast.LENGTH_LONG).show()
                editTextPhone.text.toString().trim().length<10 -> Toast.makeText(context, resources.getString(R.string.phone_number_invalid_error), Toast.LENGTH_LONG).show()
                editTextPass.text.toString().trim().isEmpty() -> Toast.makeText(context, resources.getString(R.string.password_error), Toast.LENGTH_LONG).show()
                editTextPass.text.toString().length<6 -> Toast.makeText(context, resources.getString(R.string.password_invalid_error), Toast.LENGTH_LONG).show()
                else -> {
                    BaseApplication.instance.userModel.gender = spinnerGender.selectedItem.toString()
                    BaseApplication.instance.userModel.fname = editTextFname.text.toString().trim()
                    BaseApplication.instance.userModel.lname = editTextLName.text.toString().trim()
                    BaseApplication.instance.userModel.contact_number = editTextPhone.text.toString().trim()
                    BaseApplication.instance.userModel.password = Utility.md5(editTextPass.text.toString().trim())
                    Utility.addFragmentForBackStack(
                        fragmentManager!!,
                        OTPFragment::class.java.name,
                        OTPFragment(), editTextPhone.text.toString().trim()
                    )
                }
            }
        }

    }

}




