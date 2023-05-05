package co.ostorlab.insecure_app.bugs.calls;

import android.content.Intent;
import co.ostorlab.insecure_app.BugRule;

public class IntentCall extends BugRule {

    private static final String TAG = IntentCall.class.toString();

    @Override
    public void run() throws Exception {
        Intent intent = new Intent("co.ostorlab");
        intent.putExtra("token", "SuperSecretToken");
        getContext().sendBroadcast(intent);
    }

    @Override
    public String getDescription() {
        return "The application broadcasts data through an intent";
    }
}
