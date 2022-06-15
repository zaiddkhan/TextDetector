package com.example.textdetector.fragments

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.example.textdetector.BuildConfig
import com.example.textdetector.R
import com.example.textdetector.databinding.FragmentIntroBinding
import java.io.File


class IntroFrag : Fragment() {
   lateinit var binding: FragmentIntroBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroBinding.inflate(layoutInflater)
        binding.btnGoToScanner.setOnClickListener {
       findNavController().navigate(R.id.action_introFrag_to_scannerFragment)
        }
        return binding.root
    }


}