#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
JNICALL
Java_com_joblogic_todo_data_cache_Keys_baseUrl(JNIEnv *env, jobject object) {
    std::string key = "https://my-json-server.typicode.com/imkhan334/";
    return env->NewStringUTF(key.c_str());
}