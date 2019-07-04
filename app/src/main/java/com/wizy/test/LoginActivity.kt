package com.wizy.test

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crawlapss.myapplication.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.crawlapss.myapplication.R.layout.activity_login)

        buttonLogin.setOnClickListener {
            when {
                editTextContact.text.toString().trim().isEmpty() -> Toast.makeText(this, resources.getString(R.string.phone_number_error), Toast.LENGTH_LONG).show()
                editTextContact.text.toString().trim().length<10 -> Toast.makeText(this, resources.getString(R.string.phone_number_invalid_error), Toast.LENGTH_LONG).show()
                editTextPassword.text.toString().trim().isEmpty() -> Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.password_error),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    Utility.showProgressLoaderDialog(this)
                    BaseApplication.instance.mSearchedLocationReference!!.child(editTextContact.text.toString())
                        .addListenerForSingleValueEvent(object :
                            ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                Utility.dismissProgressLoaderDialog()
                                Toast.makeText(this@LoginActivity, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                Utility.dismissProgressLoaderDialog()
                                if (p0.value == null) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        resources.getString(R.string.unauthenticated_msg),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    if (p0.child("password").value != null && p0.child("password").value == Utility.md5(
                                            editTextPassword.text.toString().trim()
                                        )
                                    ) {
                                        Toast.makeText(
                                            this@LoginActivity,
                                            resources.getString(R.string.login_success_msg),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this@LoginActivity,
                                            resources.getString(R.string.unauthenticated_msg),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }

                        })
                }
            }
        }
    }

}