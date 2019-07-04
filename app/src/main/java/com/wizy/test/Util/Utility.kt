package com.wizy.test.Util

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*


class Utility {
    companion object {
        private var progressDialogLoader: Dialog? = null

        // Add Fragment for manage Back Stack
        fun addFragmentForBackStack(
            fragmentManager: FragmentManager,
            name: String,
            loadedFragment: Fragment,
            data: String
        ) {
            var fragment = FragmentUtil.getFragmentByTagName(fragmentManager, name)

            // Because fragment two has been popup from the back stack, so it must be null.
            if (fragment == null) {
                fragment = loadedFragment
            }
            if (data != "") {
                val args = Bundle()
                args.putString("result", data)
                fragment.arguments = args
            }
            val fragmentTransaction = fragmentManager.beginTransaction()
            // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
            // This action will remove Fragment one and add Fragment two.
            fragmentTransaction.replace(com.crawlapss.myapplication.R.id.content_frame, fragment, name)

            // Add fragment one in back stack.So it will not be destroyed. Press back menu can pop it up from the stack.
            fragmentTransaction.addToBackStack(name)

            fragmentTransaction.commit()

            FragmentUtil.printActivityFragmentList(fragmentManager)
        }


        // For Show Progress Loader Dialog
        fun showProgressLoaderDialog(context: Context) {
            if (progressDialogLoader != null && progressDialogLoader!!.isShowing) {
                progressDialogLoader!!.dismiss()
            }
            progressDialogLoader = Dialog(context)
            progressDialogLoader!!.setContentView(com.crawlapss.myapplication.R.layout.common_progress_dialog)
            progressDialogLoader!!.setCancelable(false)
            progressDialogLoader!!.window.setBackgroundDrawable(ColorDrawable(0))

            if (!(context as Activity).isFinishing) {
                //show progressDialog
                progressDialogLoader!!.dismiss()
                progressDialogLoader!!.show()
            }
        }

        // For Check and Dismiss Progress Dialog
        fun dismissProgressLoaderDialog() {
            if (progressDialogLoader != null && progressDialogLoader!!.isShowing) {
                progressDialogLoader!!.dismiss()
            }
        }


        // Convert Password to MD5 String
        fun md5(input: String): String? {
            try {
                val md = MessageDigest.getInstance("MD5")

                val hexString = StringBuilder()
                for (digestByte in md.digest(input.toByteArray()))
                    hexString.append(String.format("%02X", digestByte))

                return hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                return null
            }

        }

        // For get Today Date
        fun getTodayDate(inputFormat: String): String {
            val simpleDateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
            val dateToday = Calendar.getInstance().time
            return simpleDateFormat.format(dateToday)
        }

        // For Make Custom DatePicker dialog
        fun makeCustomDatePickerDialog(
            context: Context,
            date: String,
            disableDate: String,
            regex: String,
            isDateDisable: Boolean,
            reason: String,
            onDateSetListener: DatePickerDialog.OnDateSetListener
        ) {
            try {

                val strToDate = date.split(regex.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val intMonthFrom = Integer.parseInt(strToDate[1]) - 1
                val intDayFrom = Integer.parseInt(strToDate[0])
                val intYearFrom = Integer.parseInt(strToDate[2])

                val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val dateDisable = sdf.parse(disableDate)

                val startDate = dateDisable.time

                val datePickerDialog =
                    DatePickerDialog(context, onDateSetListener, intYearFrom, intMonthFrom, intDayFrom)



                if(isDateDisable) {
                    if (reason == "past") {
                        datePickerDialog.datePicker.maxDate = startDate
                    } else {
                        datePickerDialog.datePicker.minDate = startDate
                    }
                }

                datePickerDialog.show()
            } catch (e: Exception) {

            }

        }

    }
}
