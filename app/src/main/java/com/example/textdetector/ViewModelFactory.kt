package com.example.textdetector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.textdetector.room.ImageRepository

class ViewModelFactory(private val repo:ImageRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repo) as T
    }
}
