package co.ostorlab.insecure_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.ostorlab.insecure_app.bugs.calls.AESCipher;
import co.ostorlab.insecure_app.bugs.calls.ClearTextTraffic;
import co.ostorlab.insecure_app.bugs.calls.CommandExec;
import co.ostorlab.insecure_app.bugs.calls.HashCall;
import co.ostorlab.insecure_app.bugs.calls.ImplicitPendingIntentVulnerability;
import co.ostorlab.insecure_app.bugs.calls.InsecureCommands;
import co.ostorlab.insecure_app.bugs.calls.InsecureFilePermissions;
import co.ostorlab.insecure_app.bugs.calls.DexClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.ECBModeCipher;
import co.ostorlab.insecure_app.bugs.calls.InsecureRandom;
import co.ostorlab.insecure_app.bugs.calls.InsecureSharedPreferences;
import co.ostorlab.insecure_app.bugs.calls.IntentCall;
import co.ostorlab.insecure_app.bugs.calls.MobileOnlyDownloadManager;
import co.ostorlab.insecure_app.bugs.calls.ParcelableMemoryCorruption;
import co.ostorlab.insecure_app.bugs.calls.PathClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.PathTraversalVulnerability;
import co.ostorlab.insecure_app.bugs.calls.SerializableMemoryCorruption;
import co.ostorlab.insecure_app.bugs.calls.StaticIV;
import co.ostorlab.insecure_app.bugs.calls.TLSTraffic;
import co.ostorlab.insecure_app.bugs.calls.WebviewInsecureSettings;
import co.ostorlab.insecure_app.bugs.calls.ArrayCall;
import co.ostorlab.insecure_app.bugs.calls.SQLiteDatabaseCall;

public class MainActivity extends AppCompatActivity {
    private TextView outputView;
    private Button runAllButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("native-lib");
        outputView = findViewById(R.id.runOutputId);
        runAllButton = findViewById(R.id.runAllId);


        final Button runAllButton = findViewById(R.id.runAllId);
        runAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputView.setText("Running \n");
                executeAllRules();
            }
        });
    }

    private void executeAllRules() {
        BugRuleCaller caller = new BugRuleCaller(getApplicationContext());
        outputView.append("Adding rules ...\n");
        caller.addRule(new ECBModeCipher());
        caller.addRule(new ClearTextTraffic());
        caller.addRule(new TLSTraffic());
        caller.addRule(new AESCipher());
        caller.addRule(new StaticIV());
        caller.addRule(new PathClassLoaderCall());
        caller.addRule(new DexClassLoaderCall());
        caller.addRule(new InsecureFilePermissions());
        caller.addRule(new InsecureSharedPreferences());
        caller.addRule(new InsecureCommands());
        caller.addRule(new CommandExec());
        caller.addRule(new IntentCall());
        caller.addRule(new HashCall());
        caller.addRule(new WebviewInsecureSettings());
        caller.addRule(new MobileOnlyDownloadManager());
        caller.addRule(new InsecureRandom());
        caller.addRule(new ArrayCall());
        caller.addRule(new SQLiteDatabaseCall());
        // caller.addRule(new MemoryCorruption());
        caller.addRule(new ParcelableMemoryCorruption());
        caller.addRule(new SerializableMemoryCorruption());
        caller.addRule(new PathTraversalVulnerability());
        caller.addRule(new ImplicitPendingIntentVulnerability());
        try {
            caller.callRules();
            outputView.append(caller.listBugRules());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
