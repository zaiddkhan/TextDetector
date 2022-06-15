package com.example.textdetector.room

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "image_data")
data class ImageModel(

    @ColumnInfo(name ="uri")
    val uri: Uri
){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}