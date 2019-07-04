package com.wizy.test.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.crawlapss.myapplication.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.hw4c.thetam.Interface.ItemClickInterface
import com.shopifydemo.Adapter.Product.PaymentAdapter
import com.wizy.test.BaseApplication
import com.wizy.test.Util.Utility
import kotlinx.android.synthetic.main.common_recyclerview.*
import kotlinx.android.synthetic.main.fragment_last_step.*


class PaymentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_step, container, false)
    }

    // Make fragment instance
    companion object {
        fun newInstance(): PaymentFragment {
            return PaymentFragment()
        }
    }

    // Initialling and make click of views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commonRecyclerView.layoutManager = LinearLayoutManager(activity!!)
        val paymentAdapter = PaymentAdapter(activity!!, resources.getStringArray(R.array.payment_array))
        commonRecyclerView.adapter = paymentAdapter
        paymentAdapter.itemClickListener(object : ItemClickInterface {
            override fun onClick(position: Int, view: View) {
                paymentAdapter.updateData(position)
                BaseApplication.instance.userModel.payment_option =
                    resources.getStringArray(R.array.payment_array)[position]
            }

        })

        buttonNext.setOnClickListener {
            if (BaseApplication.instance.userModel.payment_option == null) {
                Toast.makeText(activity!!, resources.getString(R.string.payment_error_msg), Toast.LENGTH_SHORT).show()
            } else {
                Utility.showProgressLoaderDialog(activity!!)
                BaseApplication.instance.mSearchedLocationReference!!.child(BaseApplication.instance.userModel.contact_number)
                    .setValue(BaseApplication.instance.userModel)
                    .addOnCompleteListener { p0 ->
                        if (p0.isSuccessful) {
                            Toast.makeText(activity!!, "Store Data successfully", Toast.LENGTH_SHORT).show()
                            Utility.dismissProgressLoaderDialog()
                            activity!!.finish()
                        } else {
                            Utility.dismissProgressLoaderDialog()
                            Toast.makeText(activity!!, p0.exception!!.message, Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener(activity!!) {
                        Toast.makeText(activity!!, it.message, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}