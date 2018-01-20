package co.ostorlab.insecure_app.bugs.calls;

import co.ostorlab.insecure_app.BugRule;
import dalvik.system.PathClassLoader;
import java.lang.ClassLoader;


public final class PathClassLoaderCall extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Use of insecure ECB Mode";
    }

    @Override
    public void run() throws Exception{
        PathClassLoader classLoader = new PathClassLoader("/sdcard/test.apk", ClassLoader.getSystemClassLoader());
        classLoader.loadClass("a.b.c");
    }
}
