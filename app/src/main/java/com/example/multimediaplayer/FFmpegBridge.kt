package com.example.multimediaplayer

object FFmpegBridge {
    init {
        System.loadLibrary("ffmpegbridge")
    }

    external fun stringFromJNI(): String
    external fun extractMetadata(filePath: String): String
}