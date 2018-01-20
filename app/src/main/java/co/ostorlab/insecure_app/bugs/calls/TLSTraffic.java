package co.ostorlab.insecure_app.bugs.calls;

import android.util.Log;
import co.ostorlab.insecure_app.BugRule;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class TLSTraffic extends BugRule {

    private static final String TAG = TLSTraffic.class.toString();

    @Override
    public void run() throws Exception {
        new Thread(new Runnable() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                            .build();
                    Request request = new Request.Builder()
                            .url("https://www.google.com/")
                            .build();
                    Call okhttpCall = client.newCall(request);
                    Response response = okhttpCall.execute();
                }
                catch (Exception e) {
                    Log.e(TAG, "error initializing TLSTraffic thread", e);
                }
            }
        }).start();
    }

    @Override
    public String getDescription() {
        return "TLS/SSL HTTP traffic";
    }
}
