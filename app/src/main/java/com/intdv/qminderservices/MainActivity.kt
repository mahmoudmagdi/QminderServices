package com.intdv.qminderservices

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.intdv.qminderservices.fragments.LinesFragment
import com.intdv.qminderservices.fragments.TicketFragment

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private var currentFragment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(LinesFragment())
    }

    fun setFragment(fragment: Fragment) {
        currentFragment = fragment::class.java.simpleName
        Log.d(TAG, "Transaction for fragment : " + fragment::class.java.simpleName)
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (currentFragment == TicketFragment::class.java.simpleName) {
            setFragment(LinesFragment())
        } else {
            super.onBackPressed()
        }
    }
}