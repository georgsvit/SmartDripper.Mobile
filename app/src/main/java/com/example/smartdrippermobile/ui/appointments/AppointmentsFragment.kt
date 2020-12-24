package com.example.smartdrippermobile.ui.appointments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.smartdrippermobile.R
import com.example.smartdrippermobile.databinding.FragmentAppointmentsBinding
import com.example.smartdrippermobile.ui.detail.DetailActivity

class AppointmentsFragment : Fragment() {
    private lateinit var viewModel: AppointmentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAppointmentsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointments, container, false)

        viewModel = AppointmentsViewModel(requireActivity().application)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.reportsGrid.adapter = AppointmentsGridAdapter(AppointmentsGridAdapter.OnClickListener {
            if (!it.IsDone) {
                viewModel.saveAppointmentIdToSP(it.Id)
                startActivity(Intent(context, DetailActivity::class.java))
            } else {
                Toast.makeText(context, getString(R.string.is_done_label), Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

    override fun onResume() {
        viewModel.globalGetAppointments()
        super.onResume()
    }
}