package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

import co.ostorlab.insecure_app.BugRule;




public final class PackageContextCall extends BugRule {

    private static final String TAG = PackageContextCall.class.toString();


    @Override
    public String getDescription() {
        return "Use of third-party packages";
    }

    @Override
    public void run() {
        Context context = getContext();
        PackageManager packageManager = context.getPackageManager();

        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);

        for (PackageInfo info : installedPackages) {
            String packageName = info.packageName;

            if (packageName.startsWith("co.ostorlab.plugins.")) {
                try {
                    Context packageContext = context.createPackageContext(packageName,
                            Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);

                    Class<?> loaderClass = packageContext.getClassLoader().loadClass("co.ostorlab.plugins.camera.Loader");
                    Method updateMethod = loaderClass.getMethod("Update", Context.class);
                    updateMethod.invoke(null, context);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
