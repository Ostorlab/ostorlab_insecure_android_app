package co.ostorlab.insecure_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import co.ostorlab.insecure_app.bugs.calls.ECBModeCall;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executeAllRules();
    }

    private void executeAllRules() {
        BugRuleCaller caller = new BugRuleCaller();

        caller.addRule(new ECBModeCall());

        caller.callRules();
    }
}
