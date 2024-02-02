package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;

import android.content.Context;
import android.net.wifi.WifiManager;

public final class InsecureWifiApi extends BugRule {

    private static final String TAG = "RULE";
    private Context appContext;

    public InsecureWifiApi(Context context){
        appContext = context.getApplicationContext();
    }

    @Override
    public String getDescription() {
        return "Insecure wifi api.";
    }

    @Override
    public void run(String user_input) throws Exception {
        WifiManager wifiManager = (WifiManager) appContext.getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null && wifiManager.isWifiEnabled()){
            wifiManager.getConnectionInfo();
            wifiManager.getConfiguredNetworks();
            wifiManager.getDhcpInfo();
        }
    }
}
