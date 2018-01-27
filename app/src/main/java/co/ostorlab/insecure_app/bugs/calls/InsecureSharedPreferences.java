package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import co.ostorlab.insecure_app.BugRule;


public final class InsecureSharedPreferences extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Call to shared preference method using insecure permission";
    }

    @Override
    public void run() throws Exception{
        String mypreference = "mypref";
        //
        SharedPreferences secureGetSharedPreferences = getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences InsecureWorldRedableSharedPreferences = getContext().getSharedPreferences(mypreference, Context.MODE_WORLD_READABLE);
        SharedPreferences InsecureCombinationSharedPreferences = getContext().getSharedPreferences(mypreference,
                Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
    }
}
