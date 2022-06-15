package com.example.textdetector.fragments

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.textdetector.SharedViewModel
import com.example.textdetector.ViewModelFactory
import com.example.textdetector.databinding.FragmentScannerBinding
import com.example.textdetector.room.ImageModel
import com.example.textdetector.room.ImageRepository
import com.example.textdetector.room.PhotoDatabase
import java.io.File


class ScannerFragment : Fragment() {

    lateinit var binding: FragmentScannerBinding
    lateinit var imageUri : Uri
    lateinit var viewModel:SharedViewModel
    private val contract =registerForActivityResult(ActivityResultContracts.TakePicture()){
        binding.image.setImageURI(imageUri)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentScannerBinding.inflate(layoutInflater)
        val repo = ImageRepository(PhotoDatabase(requireContext()))
        val factory = ViewModelFactory(repo)
        viewModel = ViewModelProvider(this,factory).get(SharedViewModel::class.java)
        imageUri = takePicture()


        viewModel.imageUri = imageUri
        val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
        binding.btnShowText.setOnClickListener {
          viewModel.detectText(bitmap,binding.abstractedText)
        }
        binding.btnOpenCamera.setOnClickListener {
            contract.launch(imageUri)
        }
        return binding.root
    }

    fun takePicture() : Uri {
        val image = File(context?.applicationContext?.filesDir, "files.png")
        val uri = FileProvider.getUriForFile(requireContext(),"com.example.textdetector.provider",image)
        viewModel.insert(ImageModel(uri))
        return uri
    }


}