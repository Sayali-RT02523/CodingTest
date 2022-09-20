package com.example.codingtest.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidmvvmdatabindingrecyclerviewkotlin.viewmodel.MainViewModel
import com.example.codingtest.Utils.Validation
import com.example.codingtest.databinding.FragmentListBinding
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.PetsAdapter


class ListFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private val adapter =  PetsAdapter()
    lateinit var binding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(requireActivity()).
        get(MainViewModel::class.java)


        viewModel.petList.observe(requireActivity()) {
            adapter.setPets(it)
        }

        viewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.getConfig().observe(requireActivity()) {
            val working_hours = it.split(",")
            val time = working_hours[1].split("-")
            val startTime = time[0]
            val endTime = time[1]

            if (Validation.timeChecker(startTime, endTime))
                viewModel.getPets()
            else
                showDialog()
        }
        return binding.root
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Alert")
        builder.setMessage("Beyond working hours !!!")
        builder.setPositiveButton("Ok") { dialog, which ->
          dialog.dismiss()
        }
        builder.show()
    }

}