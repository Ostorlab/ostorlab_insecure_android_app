package co.ostorlab.insecure_app.bugs.calls;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;
import android.content.Intent;

import co.ostorlab.insecure_app.BugRule;


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
            // Handle the received broadcast
        }
    }
}
