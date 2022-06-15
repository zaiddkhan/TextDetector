package com.example.textdetector.room

class ImageRepository(val db:PhotoDatabase) {
    suspend fun insert(photo:ImageModel) = db.getImageDao().insert(photo)
}