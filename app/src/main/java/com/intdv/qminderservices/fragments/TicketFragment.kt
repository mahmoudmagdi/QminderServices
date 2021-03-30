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
import com.intdv.qminderservices.R
import com.intdv.qminderservices.model.TicketResponse
import com.intdv.qminderservices.network.Router
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_ticket.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TicketFragment(private val lineId: String) : Fragment() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {

        progressDialog = ProgressDialog(activity)
        progressDialog!!.setMessage(resources.getString(R.string.loading))

        issueTicket()
    }

    private fun issueTicket(
    ) {
        showProgress()
        Router.issueTicket(lineId).enqueue(object : Callback<TicketResponse> {
            override fun onResponse(
                call: Call<TicketResponse>,
                response: Response<TicketResponse>
            ) {
                if (response.isSuccessful) {
                    hideProgress()
                    response.body()?.let { body ->
                        if (body.statusCode == 200) {
                            ticket_number.text = body.id
                        }
                    }
                }
            }

            override fun onFailure(call: Call<TicketResponse>, t: Throwable) {
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