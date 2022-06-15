package com.example.textdetector.room

import android.content.Context
import androidx.room.*
import com.example.textdetector.UriConverters


@Database(entities = [ImageModel::class], version = 1)
@TypeConverters(UriConverters::class)
abstract class PhotoDatabase:RoomDatabase()
{
    abstract fun getImageDao():ImageDao


    companion object{
        @Volatile
        private var instance : PhotoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?:  createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            PhotoDatabase::class.java,
            "Photo.db")
                .build()
    }
}