package co.ostorlab.insecure_app.bugs.calls;

import android.net.SSLCertificateSocketFactory;

import co.ostorlab.insecure_app.BugRule;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

public final class TLSTraffic extends BugRule {

    private static final String TAG = TLSTraffic.class.toString();

    @Override
    public void run() throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .hostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER)
                .build();
        Request request = new Request.Builder()
                .url("https://ostorlab.co/")
                .build();
        Call okhttpCall = client.newCall(request);
        Response response = okhttpCall.execute();
        SSLCertificateSocketFactory.getInsecure(0,null);
    }

    @Override
    public String getDescription() {
        return "TLS/SSL HTTP traffic";
    }
}
