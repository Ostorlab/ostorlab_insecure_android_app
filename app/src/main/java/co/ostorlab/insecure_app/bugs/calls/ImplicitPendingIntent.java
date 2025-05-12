package co.ostorlab.insecure_app.bugs.calls;

import android.app.PendingIntent;
import android.content.Intent;

import co.ostorlab.insecure_app.BugRule;

public class ImplicitPendingIntentVulnerability extends BugRule {
    @Override
    public void run(String user_input) throws Exception {
        // Create an implicit base Intent and wrap it in a PendingIntent

        Intent base = new Intent("ACTION_FOO");

        base.setPackage("some_package");

        PendingIntent pi = PendingIntent.getService(getContext(), 0, base, 0);
    }

    @Override
    public String getDescription() {
        return "Creating an implicit intent";
    }
}
