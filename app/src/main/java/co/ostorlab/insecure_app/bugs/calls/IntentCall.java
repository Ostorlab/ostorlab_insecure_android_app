package co.ostorlab.insecure_app.bugs.calls;

import android.content.Intent;
import co.ostorlab.insecure_app.BugRule;

public class IntentCall extends BugRule {

    private static final String TAG = IntentCall.class.toString();

    @Override
    public void run() throws Exception {
        Intent intent1 = new Intent("co.ostorlab");
        Intent intent2 = new Intent("com.google.android.view");
        Intent intent3 = new Intent("android.test.VIEW");
        getContext().sendBroadcast(intent1);
        getContext().sendBroadcast(intent2);
        getContext().sendBroadcast(intent3);
    }

    @Override
    public String getDescription() {
        return "The application uses a string value to construct an Intent";
    }
}
