package co.ostorlab.insecure_app.bugs.calls;


import co.ostorlab.insecure_app.BugRule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClearTextTraffic extends BugRule {

    @Override
    public void run() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                             .url("http://www.google.com/")
                             .build();
        Response response = client.newCall(request).execute();

    }

    @Override
    public String getDescription() {
        return "Use of cleartext HTTP traffic";
    }
}
