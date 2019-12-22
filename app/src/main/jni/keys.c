#include <jni.h>



JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getBaseUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://en.wikipedia.org/api/rest_v1/media/math/");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_getHostName(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "wikipedia.org");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_SSLKey1(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/aPvOEOW4dFQmDqYWzBfqxFjcj++kkeWNCBk7DEE7M5Q=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_SSLKey2(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_omiseassignment_AppConstant_SSLKey3(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=");
}

