package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;


import android.annotation.SuppressLint;
import android.util.Log;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;


public final class HardcodedUrlInUrl extends BugRule {

    private static final String TAG = "RULE";

    @SuppressLint("AuthLeak")
    public String get_url() {
        return "https://ostora:07b8a0abfx53p98f7ae5@ostora.ostorlab.com/faq/?country=mars";
    }

    @Override
    public void run(String user_input) throws Exception {
        if (user_input.length() != 0){
            ContextCompat.getMainExecutor(getContext()).execute(()  -> {
                Log.i(TAG, String.format("Message: %s", user_input));
                WebView webView = new WebView(getContext());
                webView.loadUrl(user_input);
            });
        }

        ContextCompat.getMainExecutor(getContext()).execute(()  -> {
            Log.i(TAG, String.format("Message: %s", get_url()));
            WebView webView = new WebView(getContext());
            webView.loadUrl(this.get_url());
        });
    }

    @Override
    public String getDescription() {
        return "Use hardcoded password in URL";
    }
}
