package com.wizy.test.Util


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


object FragmentUtil {
    val TAG_NAME_FRAGMENT = "ACTIVITY_FRAGMENT"

    // Get exist Fragment by it's tag name.
    fun getFragmentByTagName(fragmentManager: FragmentManager, fragmentTagName: String): Fragment? {
        var ret: Fragment? = null

        // Get all Fragment list.
        val fragmentList = fragmentManager.fragments

        if (fragmentList != null) {
            val size = fragmentList.size
            for (i in 0 until size) {
                val fragment = fragmentList[i]

                if (fragment != null) {
                    val fragmentTag = fragment.tag

                    // If Fragment tag name is equal then return it.
                    if (fragmentTag == fragmentTagName) {
                        ret = fragment
                    }
                }
            }
        }

        return ret
    }

    // Print fragment manager managed fragment in debug log.
    fun printActivityFragmentList(fragmentManager: FragmentManager) {
        // Get all Fragment list.
        val fragmentList = fragmentManager.fragments

        if (fragmentList != null) {
            val size = fragmentList.size
            for (i in 0 until size) {
                val fragment = fragmentList[i]

                if (fragment != null) {
                    val fragmentTag = fragment.tag
                    Log.d(TAG_NAME_FRAGMENT, fragmentTag)
                }
            }

            Log.d(TAG_NAME_FRAGMENT, "***********************************")
        }
    }


    fun removeAllFragmentList(fragmentManager: FragmentManager?) {
        if (fragmentManager != null && fragmentManager.fragments != null) {
            val fragmentList = fragmentManager.fragments
            fragmentList.clear()
        }
    }
}