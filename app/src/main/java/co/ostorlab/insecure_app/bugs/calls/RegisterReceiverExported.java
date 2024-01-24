package co.ostorlab.insecure_app.bugs.calls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import co.ostorlab.insecure_app.BugRule;

class WifiStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

        if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
            Log.d("WifiStateReceiver", "WIFI Disabled.");
        }else {
            Log.d("WifiStateReceiver", "WIFI enabled.");

        }
    }
}

public class RegisterReceiverExported extends BugRule {

    private final FragmentActivity mActivity;

    public RegisterReceiverExported(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void run(String user_input) throws Exception {
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        mActivity.registerReceiver(new WifiStateReceiver(), intentFilter, Context.RECEIVER_EXPORTED);
    }

    @Override
    public String getDescription() {
        return "Register receiver with `RECEIVER_EXPORTED` flag";
    }
}


