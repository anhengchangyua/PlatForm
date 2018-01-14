// IRemoteService.aidl
package com.zhy.uutils.aidl_study;

import com.zhy.uutils.aidl_study.MyProcess;
// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     int getPid();

     MyProcess getProcess(in MyProcess clientProcess);

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
