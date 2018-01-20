#include <jni.h>
#include <string>

std::string jstringToString(JNIEnv *env, jstring str) {
    if (!str)
        return "";

    const jclass stringClass = env->GetObjectClass(str);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(str, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_co_ostorlab_insecure_1app_bugs_calls_MemoryCorruption_triggerStackOverflow(
        JNIEnv *env,
        jobject ,
        jstring input) {
    std::string hello = jstringToString(env, input);
    return env->NewStringUTF(hello.c_str());
}
