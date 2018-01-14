package co.ostorlab.insecure_app;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

final class BugRuleCaller {

    private static final String TAG = "BugRuleCaller";

    private List<BugRule> rules;

    BugRuleCaller(){
        rules = new ArrayList<>();
    }

    <T extends BugRule> void addRule(T rule){
        rules.add(rule);
    }

    void callRules(){
        for(BugRule rule: rules){
            Log.i(TAG, String.format("Calling rule %s", rule.getDescription()));
            try {
                rule.run();
            } catch (Exception e){
                Log.e(TAG, "Error calling rule", e);
            }
        }
    }

}
