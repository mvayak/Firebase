package com.wizy.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crawlapss.myapplication.R
import kotlinx.android.synthetic.main.login_signup.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_signup)


        buttonLogin.setOnClickListener {
            val intentLogin= Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }

        buttonSignUp.setOnClickListener {
            val intentSignUp= Intent(this, SignUpActivity::class.java)
            startActivity(intentSignUp)
        }
    }

    override fun onDestroy() {
        BaseApplication.instance.classSelectList.clear()
        BaseApplication.instance.subjectSelectList.clear()
        BaseApplication.instance.hobbiesSelectList.clear()
        super.onDestroy()
    }



}
