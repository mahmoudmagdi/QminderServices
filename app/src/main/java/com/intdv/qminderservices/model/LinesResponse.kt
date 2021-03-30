package com.intdv.qminderservices.model

data class LinesResponse(
    val statusCode: Int,
    val data: List<Line>
)

data class Line(
    val id: Int,
    val name: String,
    val location: Int,
    val color: String,
    val disabled: Boolean
)