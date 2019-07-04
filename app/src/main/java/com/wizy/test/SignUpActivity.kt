package com.wizy.test

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.crawlapss.myapplication.R
import com.wizy.test.Fragment.CategoryFragment
import com.wizy.test.Fragment.PersonalVerificationFragment
import com.wizy.test.Fragment.SignUpFragment
import com.wizy.test.Util.Utility


class SignUpActivity:AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        Utility.addFragmentForBackStack(supportFragmentManager, SignUpFragment::class.java.name, SignUpFragment.newInstance(),"")
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1){
            supportFragmentManager.popBackStack()
        }else{
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (f in fragments) {
                if (f is PersonalVerificationFragment) {
                    f.onActivityResult(requestCode, resultCode, data)
                }
            }
        }
    }
}