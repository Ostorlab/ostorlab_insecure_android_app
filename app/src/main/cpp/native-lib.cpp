#include <jni.h>
#include <string>

void crackaddrOverflow(const char* input, unsigned int length){
    unsigned int buffersize = 200;
    char c, localbuf[buffersize];
    unsigned int upperlimit = buffersize - 1;
    bool quotation = false;
    bool roundquote = false;
    unsigned int inputIndex = 0;
    unsigned int outputIndex = 0;
    while(inputIndex < length){
        c = input[inputIndex++];
        if ((c == '<') && (not quotation)){
            quotation = true;
            upperlimit--;
        }
        if ((c == '>') && (quotation)){
            quotation = false;
            upperlimit++;
        }
        if ((c == '(') and (not quotation) and (not roundquote)){
            roundquote = true;
            // upperlimit--;  // missing fix.
        }
        if ((c == '(') and (not quotation) and (roundquote)){
            roundquote = false;
            upperlimit++;
        }
        if ((outputIndex < upperlimit)){
            localbuf[outputIndex] = c;
            outputIndex++;
        }
    }
    if (roundquote){
        localbuf[outputIndex] = ')';
        outputIndex++;
    }
    if (quotation){
        localbuf[outputIndex] = '>';
        outputIndex++;
    }
}

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
    std::string strInput = jstringToString(env, input);
    crackaddrOverflow(strInput.c_str(), strInput.length());
    return input;
}