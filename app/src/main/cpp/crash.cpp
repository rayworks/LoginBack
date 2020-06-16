//
// Created by Sean Zhou on 2020/6/16.
//

#include <stdio.h>
#include <jni.h>

//#include "../../../../breakpad-build/src/client/linux/handler/exception_handler.h"
//#include "../../../../breakpad-build/src/client/linux/handler/minidump_descriptor.h"

/**
 * 引起 crash
 */
void Crash() {
    volatile int *a = (int *) (NULL);
    *a = 1;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_rayworks_example_login_utils_NativeCrashesListener_testNow(JNIEnv
                                                                    *env,
                                                                    jobject thiz
) {
    Crash();
}