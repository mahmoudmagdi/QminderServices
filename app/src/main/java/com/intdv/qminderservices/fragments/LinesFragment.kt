package com.intdv.qminderservices.fragments

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.intdv.qminderservices.LinesAdapter
import com.intdv.qminderservices.R
import com.intdv.qminderservices.model.LinesResponse
import com.intdv.qminderservices.network.Router
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_lines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinesFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lines, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {

        progressDialog = ProgressDialog(activity)
        progressDialog!!.setMessage(resources.getString(R.string.loading))

        getLines()
    }

    private fun getLines(
    ) {
        showProgress()
        Router.getLines().enqueue(object : Callback<LinesResponse> {
            override fun onResponse(
                call: Call<LinesResponse>,
                response: Response<LinesResponse>
            ) {
                if (response.isSuccessful) {
                    hideProgress()
                    response.body()?.let { body ->
                        if (!body.data.isNullOrEmpty()) {
                            lines_recycler_view.layoutManager = LinearLayoutManager(context)
                            lines_recycler_view.adapter = LinesAdapter(activity!!, body.data)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LinesResponse>, t: Throwable) {
                hideProgress()
                t.message?.let { showErrorMessage(activity!!, it) }
            }
        })
    }

    private fun showProgress() {
        progressDialog!!.show()
    }

    private fun hideProgress() {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    private fun showErrorMessage(context: Context, message: String) {
        Toasty.error(context, message, Toast.LENGTH_LONG).show()
    }
}