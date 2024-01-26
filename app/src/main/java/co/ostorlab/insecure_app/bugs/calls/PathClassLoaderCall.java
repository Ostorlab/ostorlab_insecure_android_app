package co.ostorlab.insecure_app.bugs.calls;

import android.os.Environment;

import co.ostorlab.insecure_app.BugRule;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.lang.ClassLoader;


public final class PathClassLoaderCall extends BugRule {

    private static final String TAG = PathClassLoaderCall.class.toString();

    @Override
    public String getDescription() {
        return "Use of path class load";
    }

    @Override
    public void run(String user_input) throws Exception{
        /*
            Path class loading from external storage
        */
        if (user_input.isEmpty() == false){
            String apkFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + user_input;
            PathClassLoader classLoader1 = new PathClassLoader(apkFile, ClassLoader.getSystemClassLoader());
            classLoader1.loadClass("a.b.c");
        }
        /*
            Path class loading from external storage
         */
        String apkFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.apk";
        PathClassLoader classLoader1 = new PathClassLoader(apkFile, ClassLoader.getSystemClassLoader());
        classLoader1.loadClass("a.b.c");

        /*
            Path class loading from hard-coded sdcard path
         */
        PathClassLoader classLoader2 = new PathClassLoader("/sdcard/test.apk", ClassLoader.getSystemClassLoader());
        classLoader2.loadClass("a.b.c");
    }
}
