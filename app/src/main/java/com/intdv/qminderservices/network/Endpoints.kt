package com.intdv.qminderservices.network

import com.intdv.qminderservices.model.LinesResponse
import com.intdv.qminderservices.model.TicketResponse
import retrofit2.Call
import retrofit2.http.*

interface Endpoints {

    @Headers("X-Qminder-REST-API-Key: <add your API key here>")
    @GET("/v1/locations/{id}/lines/")
    fun getLines(@Path(value = "id", encoded = true) id: String): Call<LinesResponse>

    @Headers("X-Qminder-REST-API-Key: <add your API key here>")
    @POST("/v1/lines/{id}/ticket/")
    fun issueTicket(@Path(value = "id", encoded = true) id: String): Call<TicketResponse>

}