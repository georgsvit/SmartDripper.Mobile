package com.example.smartdrippermobile.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdrippermobile.data.network.dto.responses.AppointmentResponse
import com.example.smartdrippermobile.ui.appointments.AppointmentsGridAdapter

@BindingAdapter("appointmentsListData")
fun bindReportsRecyclerView(recyclerView: RecyclerView, data: List<AppointmentResponse>?) {
    val adapter = recyclerView.adapter as AppointmentsGridAdapter
    adapter.submitList(data)
}