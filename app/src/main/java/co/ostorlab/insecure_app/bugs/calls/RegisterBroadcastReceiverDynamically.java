package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ComponentName;
import android.content.BroadcastReceiver;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;



public final class RegisterBroadcastReceiverDynamically extends BugRule {

    private static final String TAG = "RULE";
    private Context appContext;

    public RegisterBroadcastReceiverDynamically(Context context) {
        appContext = context.getApplicationContext();
    }

    @Override
    public String getDescription() {
        return "Register a broadcast receiver dynamically.";
    }

    @Override
    public void run(String user_input) throws Exception{
        BroadcastReceiver dynamicReceiver = new MyDynamicReceiver();
        IntentFilter filter = new IntentFilter();
    
        appContext.registerReceiver(dynamicReceiver, filter);
    }

    private class MyDynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
            }

        }
    }

    private void setReceiverExported(boolean exported, String receiverClassName) {
        try {
            PackageManager packageManager = appContext.getPackageManager();
            String packageName = appContext.getPackageName();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            ComponentInfo receiverInfo = new ComponentInfo();

            receiverInfo.name = receiverClassName;
            receiverInfo.applicationInfo = appInfo;
            receiverInfo.exported = exported;

            packageManager.setComponentEnabledSetting(
                    new ComponentName(packageName, receiverClassName),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
            );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
