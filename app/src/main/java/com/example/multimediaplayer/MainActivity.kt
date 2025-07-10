package com.example.multimediaplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.multimediaplayer.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
//        binding.sampleText.text = FFmpegBridge.stringFromJNI()
        val nativeOutput = FFmpegBridge.stringFromJNI()
        Log.d("JNI", "Native says: ${nativeOutput}")
        // Copy asset file to internal storage so FFmpeg can read it
//        val inputFile = File(filesDir, "sample.mp4")
//        assets.open("sample.mp4").use { input ->
//            FileOutputStream(inputFile).use { output ->
//                input.copyTo(output)
//            }
//        }
//
//        Log.d("FFmpeg", "Before metadata!!!")
//        val metadata = FFmpegBridge.extractMetadata(inputFile.absolutePath)
//        Log.d("FFmpeg", "Metadata: $metadata")
    }

    /**
     * A native method that is implemented by the 'multimediaplayer' native library,
     * which is packaged with this application.
     */

//    companion object {
//        // Used to load the 'multimediaplayer' library on application startup.
//        init {
//            System.loadLibrary("multimediaplayer")
//        }
//    }

}
