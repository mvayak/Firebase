package com.wizy.test.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.crawlapss.myapplication.R
import com.wizy.test.BaseApplication
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.fragment_academics_overview.*

class AcademicsOverViewFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_academics_overview,container,false)
    }
    // Make Fragment Instance
    companion object {
        fun newInstance(): AcademicsOverViewFragment {
            return AcademicsOverViewFragment()
        }
    }
    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(BaseApplication.instance.userModel.hobbies!=null){
            textViewTitle.text=resources.getString(R.string.hobbies)
        }
        buttonNext.setOnClickListener {
            if(editTextOverView.text.toString().trim()==""){
                Toast.makeText(activity!!,resources.getString(R.string.overview_error_msg),Toast.LENGTH_SHORT).show()
            }else {
                BaseApplication.instance.userModel.overview = editTextOverView.text.toString().trim()
                Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    ConductClassesFragment::class.java.name,
                    ConductClassesFragment.newInstance(), ""
                )
            }
        }
    }
}