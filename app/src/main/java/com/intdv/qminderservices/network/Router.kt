package com.intdv.qminderservices.network

import com.intdv.qminderservices.MainApplication
import com.intdv.qminderservices.model.LinesResponse
import com.intdv.qminderservices.model.TicketResponse
import retrofit2.Call

object Router {

    private lateinit var mainApplication: MainApplication

    fun initRouter(mainApplication: MainApplication) {
        Router.mainApplication = mainApplication
    }

    fun getLines(): Call<LinesResponse> {
        return ServiceBuilder.buildService(Endpoints::class.java).getLines("38035")
    }

    fun issueTicket(lineId: String): Call<TicketResponse> {
        return ServiceBuilder.buildService(Endpoints::class.java).issueTicket(lineId)
    }
}