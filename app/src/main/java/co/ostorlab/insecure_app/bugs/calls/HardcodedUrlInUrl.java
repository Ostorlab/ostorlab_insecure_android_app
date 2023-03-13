package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;


import android.annotation.SuppressLint;
import android.util.Log;


public final class HardcodedUrlInUrl extends BugRule {

    private static final String TAG = "RULE";

    @SuppressLint("AuthLeak")
    public String get_url() {
        return "https://ostora:07b8a0abfx53p98f7ae5@ostora.ostorlab.com/faq/?country=morocco";
    }

    @Override
    public void run() throws Exception {
        Log.i(TAG, String.format("Message: %s", get_url()));
    }

    @Override
    public String getDescription() {
        return "Use hardcoded password in URL";
    }
}
