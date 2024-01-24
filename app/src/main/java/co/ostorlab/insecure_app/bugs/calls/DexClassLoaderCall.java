package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import co.ostorlab.insecure_app.BugRule;
import dalvik.system.DexClassLoader;

import java.io.File;
import java.lang.ClassLoader;


public final class DexClassLoaderCall extends BugRule {

    private static final String TAG = DexClassLoaderCall.class.toString();

    @Override
    public String getDescription() {
        return "Use of dex class load";
    }

    @Override
    public void run(String user_input) throws Exception {
        /*
            Dex class loading from user input
        */
        if (user_input.length() != 0){
            String apkFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "user_input";
            DexClassLoader classLoader1 = new DexClassLoader(
                apkFile,
                apkFile,
                apkFile,
                ClassLoader.getSystemClassLoader());
            classLoader1.loadClass("a.b.c");
        }

        /*
            Dex class loading from external storage
         */
        String apkFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.apk";
        DexClassLoader classLoader1 = new DexClassLoader(
                apkFile,
                apkFile,
                apkFile,
                ClassLoader.getSystemClassLoader());
        classLoader1.loadClass("a.b.c");

        /*
            Dex class loading from hard-coded sdcard path
         */
        DexClassLoader classLoader2 = new DexClassLoader(
                "/sdcard/test.apk",
                "/sdcard/test.apk",
                "/sdcard/test.apk",
                ClassLoader.getSystemClassLoader());
        classLoader2.loadClass("a.b.c");

    }
}
