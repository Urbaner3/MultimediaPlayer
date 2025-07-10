#include <jni.h>
#include <string>
#include <sstream>
#include <android/log.h>

// FFmpeg core headers
extern "C" {
#include <libavformat/avformat.h>
#include <libavutil/avutil.h>
}

extern "C" JNIEXPORT
jstring JNICALL
Java_com_example_multimediaplayer_FFmpegBridge_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "FFmpeg bridge ready";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_multimediaplayer_FFmpegBridge_extractMetadata(
        JNIEnv *env, jobject, jstring filePath) {

    const char *path = env->GetStringUTFChars(filePath, nullptr);

    AVFormatContext *fmt_ctx = avformat_alloc_context();
    if (avformat_open_input(&fmt_ctx, path, nullptr, nullptr) != 0) {
        env->ReleaseStringUTFChars(filePath, path);
        return env->NewStringUTF("Failed to open file");
    }

    if (avformat_find_stream_info(fmt_ctx, nullptr) < 0) {
        avformat_close_input(&fmt_ctx);
        env->ReleaseStringUTFChars(filePath, path);
        return env->NewStringUTF("Failed to retrieve stream info");
    }

    std::stringstream metadata;
    metadata << "Format: " << fmt_ctx->iformat->long_name << "\\n";
    metadata << "Duration: " << (fmt_ctx->duration / AV_TIME_BASE) << " sec\\n";
    metadata << "Streams: " << fmt_ctx->nb_streams;

    avformat_close_input(&fmt_ctx);
    env->ReleaseStringUTFChars(filePath, path);
    return env->NewStringUTF(metadata.str().c_str());
}