package co.ostorlab.insecure_app;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

final class BugRuleCaller {

    private static final String TAG = "BugRuleCaller";

    private List<BugRule> rules;
    private Context context;

    BugRuleCaller(Context context){
        this.context = context;
        this.rules = new ArrayList<>();
    }

    public List<BugRule> getRules(){
        return rules;
    }

    <T extends BugRule> void addRule(T rule){
        rule.setContext(context);
        rules.add(rule);
    }

    void callRules() throws Exception{
        for(final BugRule rule: rules){
            runInThread(rule);
        }
    }

    String listBugRules() throws Exception{
        StringBuilder buffer = new StringBuilder();
        for(BugRule rule: rules) {
            buffer.append(rule.toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private void runInThread(final BugRule rule) throws Exception {
        new Thread(new Runnable() {
            public void run() {
                try {
                    rule.run();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
