package co.ostorlab.insecure_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.ostorlab.insecure_app.bugs.calls.ClearTextTraffic;
import co.ostorlab.insecure_app.bugs.calls.ECBModeCipher;
import co.ostorlab.insecure_app.bugs.calls.TLSTraffic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button runAllButton = findViewById(R.id.runAllId);
        final TextView outputView = findViewById(R.id.runOutputId);
        runAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputView.append("Running");
                executeAllRules();
            }
        });

        // executeAllRules();
    }

    private void executeAllRules() {
        BugRuleCaller caller = new BugRuleCaller();

        caller.addRule(new ECBModeCipher());
        caller.addRule(new ClearTextTraffic());
        caller.addRule(new TLSTraffic());

        caller.callRules();
    }
}
