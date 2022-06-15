package com.example.textdetector

import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.textdetector.room.ImageModel
import com.example.textdetector.room.ImageRepository
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SharedViewModel(private val repo:ImageRepository):ViewModel(){

    var imageUri:Uri? = null

    fun insert(image:ImageModel) = GlobalScope.launch {
        repo.insert(image)
    }

    fun detectText(bitmap: Bitmap,tv:TextView){
        var res = ""
        val image = InputImage.fromBitmap(bitmap,0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val resultText = visionText.text
                for (block in visionText.textBlocks) {
                    val blockText = block.text
                    val blockCornerPoints = block.cornerPoints
                    val blockFrame = block.boundingBox
                    for (line in block.lines) {
                        val lineText = line.text
                        val lineCornerPoints = line.cornerPoints
                        val lineFrame = line.boundingBox
                        for (element in line.elements) {
                            val elementText = element.text
                            val elementCornerPoints = element.cornerPoints
                            val elementFrame = element.boundingBox
                        }
                    if(resultText.length > blockText.length){
                        tv.text = resultText
                    }else{
                        tv.text = blockText
                    }

                    }

                }

            }
            .addOnFailureListener { e ->
               e.printStackTrace()
            }
    }

}