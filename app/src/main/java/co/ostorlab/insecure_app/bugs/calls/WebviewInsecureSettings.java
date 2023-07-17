package co.ostorlab.insecure_app.bugs.calls;

import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import co.ostorlab.insecure_app.BugRule;


public final class WebviewInsecureSettings extends BugRule {
    private static final String TAG = WebviewInsecureSettings.class.toString();

    @Override
    public String getDescription() {
        return "Insecure WebviewInsecureSettings settings";
    }

    @Override
    public void run() throws Exception {
        ContextCompat.getMainExecutor(getContext()).execute(()  -> {
            WebView webView  = new WebView(getContext());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.setWebContentsDebuggingEnabled(true);
            webView.loadUrl("http://www.ostorlab.co");
                });
    }
}
