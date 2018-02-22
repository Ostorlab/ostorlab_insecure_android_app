package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;

import co.ostorlab.insecure_app.BugRule;


public final class InsecureSharedPreferences extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Call to shared preference method using insecure permission";
    }

    @Override
    public void run() throws Exception{
        String myPreference = "myPreference";
        getContext().getSharedPreferences("PrivateOnly", Context.MODE_PRIVATE);
        getContext().getSharedPreferences("WorldReadableOnly", Context.MODE_WORLD_READABLE);
        getContext().getSharedPreferences("WorldReadableAndWritable", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        getContext().getSharedPreferences("WorldReadableAndAppend", Context.MODE_WORLD_READABLE | Context.MODE_MULTI_PROCESS);
    }
}
