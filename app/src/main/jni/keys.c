#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_tahir_robartassignment_Configurations_AppConstant_getBaseUrl(JNIEnv *env,
                                                                      jobject instance) {
    return (*env)->NewStringUTF(env, "https://en.wikipedia.org/api/rest_v1/media/math/");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_robartassignment_Configurations_AppConstant_getHostName(JNIEnv *env,
                                                                       jobject instance) {
    return (*env)->NewStringUTF(env, "wikipedia.org");
}

JNIEXPORT jstring JNICALL
Java_com_tahir_robartassignment_Configurations_AppConstant_SSLKey1(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "sha256/JHe5YmL7TxiUEKaa5/DhP5dVQPOjHrIOw13WP/11ZLE=");
}



