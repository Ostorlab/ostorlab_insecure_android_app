package co.ostorlab.insecure_app.bugs.calls;

import co.ostorlab.insecure_app.BugRule;
import dalvik.system.DexClassLoader;

import java.lang.ClassLoader;


public final class DexClassLoaderCall extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Use of dex class load";
    }

    @Override
    public void run() throws Exception {
        DexClassLoader classLoader = new DexClassLoader(
                "/sdcard/test.apk",
                "/sdcard/test.apk",
                "/sdcard/test.apk",
                ClassLoader.getSystemClassLoader());
        classLoader.loadClass("a.b.c");
    }
}
