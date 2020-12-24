package com.example.smartdrippermobile.ui.appointments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartdrippermobile.data.network.dto.responses.AppointmentResponse
import com.example.smartdrippermobile.databinding.AppointmentsViewItemBinding

class AppointmentsGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<AppointmentResponse, AppointmentsGridAdapter.AppointmentsViewHolder>(DiffCallback) {
    class AppointmentsViewHolder(private var binding: AppointmentsViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(appointment: AppointmentResponse) {
            binding.appointment = appointment
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (appointment: AppointmentResponse) -> Unit) {
        fun onClick(report: AppointmentResponse) = clickListener(report)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AppointmentResponse>() {
        override fun areItemsTheSame(oldItem: AppointmentResponse, newItem: AppointmentResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AppointmentResponse, newItem: AppointmentResponse): Boolean {
            return oldItem.Id == newItem.Id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        return AppointmentsViewHolder(AppointmentsViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        val appointment = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(appointment)
        }
        holder.bind(appointment)
    }
}