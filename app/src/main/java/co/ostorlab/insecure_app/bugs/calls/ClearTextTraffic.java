package co.ostorlab.insecure_app.bugs.calls;

import android.util.Log;

import co.ostorlab.insecure_app.BugRule;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClearTextTraffic extends BugRule {

    private static final String TAG = ClearTextTraffic.class.toString();

    @Override
    public void run() throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url("http://linuxfr.org/")
                .build();
        Call okhttpCall = client.newCall(request);
        Response response = okhttpCall.execute();
    }

    @Override
    public String getDescription() {
        return "Use of cleartext HTTP traffic";
    }
}
