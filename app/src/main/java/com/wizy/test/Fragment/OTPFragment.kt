package com.wizy.test.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.crawlapss.myapplication.R
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.fragment_otp.*
import java.util.concurrent.TimeUnit

class OTPFragment : Fragment() {
    lateinit var mAuth: FirebaseAuth
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var isValidCode = false
    private var mResendToken: PhoneAuthProvider.ForceResendingToken? = null
    private var mVerificationId: String? = "default"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mAuth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    // Make fragment instance
    companion object {
        fun newInstance(): OTPFragment {
            return OTPFragment()
        }
    }

    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextPhoneNumber.setText(arguments!!.getString("result"))

        buttonNext.setOnClickListener {
            if (isValidCode) {
                Utility.addFragmentForBackStack(
                    fragmentManager!!,
                    CategoryFragment::class.java.name,
                    CategoryFragment(), ""
                )
            } else {
                if (otpView.text.toString() != "") {
                    verifyVerificationCode(otpView.text.toString())
                    Toast.makeText(activity!!, "Please wait", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity!!, resources.getString(R.string.valid_otp_error), Toast.LENGTH_LONG).show()
                }
            }
        }


        buttonResend.setOnClickListener {
            Toast.makeText(activity!!, resources.getString(R.string.resend_error_msg), Toast.LENGTH_LONG).show()
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + editTextPhoneNumber.text.toString().trim(),
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks, mResendToken
            )

        }




        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                //Getting the code sent by SMS
                val code = phoneAuthCredential.smsCode

                if (code != null) {
                    if (otpView != null) {
                        otpView.setText(code)
                        //verifying the code
                        verifyVerificationCode(code)
                    }
                }
            }


            override fun onVerificationFailed(e: FirebaseException) {

                Toast.makeText(activity!!, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(s: String?, forceResendingToken: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(s, forceResendingToken)
                mVerificationId = s
                mResendToken = forceResendingToken
            }
        }

        sendVerificationCode(editTextPhoneNumber.text.toString())

    }

    // For verify verification code
    private fun verifyVerificationCode(otp: String) {
        //creating the credential
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, otp)

        //signing the user
        signInWithPhoneAuthCredential(credential)
    }

    // For verify contact number
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity!!) { task ->
                if (task.isSuccessful) {
                    isValidCode = true
                    Utility.addFragmentForBackStack(
                        fragmentManager!!,
                        CategoryFragment::class.java.name,
                        CategoryFragment(), ""

                    )

                } else {

                    //verification unsuccessful.. display an error message

                    var message = "Somthing is wrong, we will fix it soon..."

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered..."
                    }

                    Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    // For Make send verification code
    private fun sendVerificationCode(mobile: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$mobile",
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallbacks
        )
    }

}